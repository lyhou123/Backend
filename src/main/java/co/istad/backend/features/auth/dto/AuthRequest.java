package co.istad.backend.features.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AuthRequest(

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is not correct!")
    String email,

    @NotBlank(message = "Password is required")
    String password
            )
{
}
