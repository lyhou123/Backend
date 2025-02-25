package co.istad.backend.features.auth;

import co.istad.backend.domain.User;
import co.istad.backend.domain.role.EnumRole;
import co.istad.backend.domain.role.Role;
import co.istad.backend.features.auth.dto.AuthRequest;
import co.istad.backend.features.auth.dto.AuthResponse;
import co.istad.backend.features.auth.dto.RefreshTokenRequest;
import co.istad.backend.features.role.RoleRepository;
import co.istad.backend.features.user.UserRepository;
import co.istad.backend.features.user.dto.ResponseUserDto;
import co.istad.backend.features.user.dto.UserRegisterDto;
import co.istad.backend.mapper.UserMapper;
import co.istad.backend.security.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {

    private final TokenGenerator tokenGenerator;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest authRequest) {

        User user = userRepository.findUsersByEmail(authRequest.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));


        Authentication authentication = daoAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password())
        );
        return tokenGenerator.generateTokens(authentication);
    }



    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest)
    {
       Authentication authentication = jwtAuthenticationProvider.authenticate(
               new BearerTokenAuthenticationToken(refreshTokenRequest.refreshToken())
       );
       return tokenGenerator.generateTokens(authentication);
    }



    @Override
    public ResponseUserDto createUser(UserRegisterDto userRegisterDto){

        if(userRepository.existsByEmail(userRegisterDto.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User with email " + userRegisterDto.email() + " already existed");
        }

        if(userRepository.existsByUserName(userRegisterDto.userName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User with username " + userRegisterDto.userName() + " already existed");
        }

        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUserName(userRegisterDto.userName());
        user.setEmail(userRegisterDto.email());
        user.setIsVerified(true);
        user.setIsDeleted(false);

        user.setIsAccountNonExpired(true);
        user.setIsAccountNonLocked(true);
        user.setIsCredentialsNonExpired(true);
        user.setIsEnabled(false);

//        set role for user
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByRoleName(EnumRole.ROLE_USER).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found.")));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userRegisterDto.password()));


        userRepository.save(user);

        return userMapper.mapFromUserToUserResponseDto(user);
    }



}
