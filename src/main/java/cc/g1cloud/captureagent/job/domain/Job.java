package cc.g1cloud.captureagent.job.domain;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public record Job(
        UUID id,
        String name,
        CaptureSource source,
        CaptureOutput output,
        JobMode mode,
        Map<String, String> metadata,
        Instant createdAt
) {
    public Job {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(source, "source must not be null");
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(mode, "mode must not be null");
        Objects.requireNonNull(createdAt, "createdAt must not be null");
        metadata = metadata == null ? Map.of() : Map.copyOf(metadata);
    }
}
