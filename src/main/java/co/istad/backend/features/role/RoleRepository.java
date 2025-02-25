package co.istad.backend.features.role;


import co.istad.backend.domain.role.EnumRole;
import co.istad.backend.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByRoleName(EnumRole roleName);

    Optional<Role> findRoleByUuid(String uuid);



}
