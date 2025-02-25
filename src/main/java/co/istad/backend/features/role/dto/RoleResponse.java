package co.istad.backend.features.role.dto;

import lombok.Builder;

@Builder
public record RoleResponse
        (
                String uuid,
                String roleName
        )
{
}
