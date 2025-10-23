package cc.g1cloud.captureagent.job.domain;

import java.util.Objects;

public record CaptureSource(
        String type,
        String uri
) {
    public CaptureSource {
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(uri, "uri must not be null");
    }
}
