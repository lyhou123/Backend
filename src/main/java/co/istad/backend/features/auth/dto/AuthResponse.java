package co.istad.backend.features.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
        String accessToken,
        String refreshToken,
        String uuid
) {
}
