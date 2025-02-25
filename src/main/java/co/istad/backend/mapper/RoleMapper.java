package co.istad.backend.mapper;


import co.istad.backend.domain.role.Role;
import co.istad.backend.features.role.dto.RoleRequest;
import co.istad.backend.features.role.dto.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);


}
