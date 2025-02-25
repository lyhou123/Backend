package co.istad.backend.features.category.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(
        String uuid,
        String name
) {
}
