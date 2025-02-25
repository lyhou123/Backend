package co.istad.backend.security;

import co.istad.backend.domain.User;
import co.istad.backend.features.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component

public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private final UserRepository userRepository;


    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {

        User user = userRepository.findUsersByEmail(source.getSubject()).orElseThrow(()-> new BadCredentialsException("Invalid Token"));

        CustomUserDetails userDetail = new CustomUserDetails();

        userDetail.setUser(user);

        // Log the authorities for debugging purposes
        userDetail.getAuthorities().forEach(authority -> {
            System.out.println("Authority: " + authority.getAuthority());
        });

            return new UsernamePasswordAuthenticationToken(userDetail,"",userDetail.getAuthorities());
    }
}
