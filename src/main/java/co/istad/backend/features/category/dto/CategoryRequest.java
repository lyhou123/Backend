package co.istad.backend.features.category.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotBlank(message = "Name is required")
        String name
) {
}
