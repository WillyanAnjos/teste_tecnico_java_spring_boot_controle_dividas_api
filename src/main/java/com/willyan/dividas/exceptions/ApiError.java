package com.willyan.dividas.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
        List<FieldError> errors
) {
    public ApiError(int status, String error, String message, String path, LocalDateTime timestamp) {
        this(status, error, message, path, timestamp, List.of());
    }

    public record FieldError(String field, String message) {
    }
}
