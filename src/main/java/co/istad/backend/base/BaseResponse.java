package co.istad.backend.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseResponse<T>(
        LocalDateTime timestamp,
        int status,
        T data,
        String message

) {
}
