package cc.g1cloud.captureagent.shared.web;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String code,
        String message,
        List<ApiErrorDetail> errors
) {
    public ApiError {
        Objects.requireNonNull(timestamp, "timestamp must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(message, "message must not be null");
        errors = errors == null ? List.of() : List.copyOf(errors);
    }
}
