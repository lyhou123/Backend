package co.istad.backend.features.role.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RoleRequest(

        @NotBlank(message = "Role name is required")
        String roleName
) {
}
