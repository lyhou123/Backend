package co.istad.backend.init;

import co.istad.backend.domain.User;
import co.istad.backend.domain.role.EnumRole;
import co.istad.backend.domain.role.Role;
import co.istad.backend.features.role.RoleRepository;
import co.istad.backend.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor

public class DataInit {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    void init()
    {
        initRole();
        initUser();
    }

    void initRole() {
        // auto generate role (USER,ADMIN)
        if (roleRepository.count() < 2) {
            List<String> roleNames = List.of(
                    "USER",
                    "ADMIN"
            );

            List<Role> roles = roleNames.stream()
                    .map(this::createRole)
                    .toList();

            roleRepository.saveAll(roles);
        }
    }

    private Role createRole(String roleName) {

        Role role = new Role();

        role.setRoleName(EnumRole.valueOf("ROLE_" + roleName));

        role.setUuid(UUID.randomUUID().toString());

        return roleRepository.save(role);
    }


    void initUser() {
        if (userRepository.count() < 2) {
            User user = new User();

            user.setEmail("lyhou282@gmail.com");
            user.setPassword(passwordEncoder.encode("lyhou123"));
            user.setUserName("lyhou");
            user.setUuid(UUID.randomUUID().toString());

            // Fetching and assigning the Role
            Role role = roleRepository.findRoleByRoleName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found."));

            user.setRoles(Set.of(role));

            user.setIsActive(true);
            user.setIsDeleted(false);
            user.setIsVerified(true);
            user.setIsEnabled(false);
            user.setIsAccountNonExpired(true);
            user.setIsAccountNonLocked(true);
            user.setIsCredentialsNonExpired(true);

            userRepository.save(user);


            //init user admin
            User user1 = new User();

            user1.setEmail("admin@gmail.com");
            user1.setPassword(passwordEncoder.encode("lyhou123"));
            user1.setUserName("admin");
            user1.setUuid(UUID.randomUUID().toString());

            // Fetching and assigning the Role
            Role role1 = roleRepository.findRoleByRoleName(EnumRole.ROLE_ADMIN)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found."));

            user1.setRoles(Set.of(role1));

            user1.setIsActive(true);
            user1.setIsDeleted(false);
            user1.setIsVerified(true);
            user1.setIsEnabled(false);
            user1.setIsAccountNonExpired(true);
            user1.setIsAccountNonLocked(true);
            user1.setIsCredentialsNonExpired(true);


            userRepository.save(user1);


        }

    }

}
