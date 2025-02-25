package co.istad.backend.features.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
public record UserRegisterDto(

        @NotBlank(message = "Username is required")
        @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Username should be alphanumeric,contain no spaces and between 3 to 20 characters")
        String userName,

        @Email(message = "Email format is not correct!")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must be at least 8 characters long, include at least one uppercase letter, one lowercase letter, one number, and one special character (@, $, !, %, *, ?, &)"
        )
        String password,

        @NotBlank(message = "Confirm Password is required")
        String confirmPassword
)
{ }
