package co.istad.backend.features.user.dto;

import lombok.Builder;

@Builder
public record UpdateUserDto(
        String username,
        String profile
) {
}
