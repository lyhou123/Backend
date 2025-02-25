package co.istad.backend.features.user.dto;


import co.istad.backend.features.role.dto.RoleResponse;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserDetailsResponse(
        String uuid,
        String username,
        String email,
        String profile,
        String createdAt,

        Boolean isActive,

        String lastModifiedAt,
        Boolean isVerified,
        Boolean isDeleted,
        Set<RoleResponse> roles
) {
}
