package co.istad.backend.features.user.dto;

import co.istad.backend.features.role.dto.RoleResponse;
import lombok.Builder;

import java.util.Set;

@Builder
public record ResponseUserDto(
        String uuid,
        String username,
        String email,
        String profile,
        String createdAt,
        String lastModifiedAt,
        Boolean isVerified,
        Boolean isEnabled,
        Boolean isDeleted,
        Set<RoleResponse> roles
) {
}
