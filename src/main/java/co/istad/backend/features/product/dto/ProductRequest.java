package co.istad.backend.features.product.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductRequest(
        @NotBlank(message = "Title is required")
        @Max(value = 255, message = "Title must be less than 255 characters")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotBlank(message = "Price is required")
        @Positive(message = "Price must be a positive number")
        String price,

        @NotBlank(message = "Thumbnail is required")
        String thumbnail
) {
}
