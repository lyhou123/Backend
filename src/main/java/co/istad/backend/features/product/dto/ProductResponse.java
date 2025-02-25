package co.istad.backend.features.product.dto;

import lombok.Builder;

@Builder
public record ProductResponse(
        String uuid,
        String title,
        String description,
        String price,
        String thumbnail,
        String createdAt
) {
}
