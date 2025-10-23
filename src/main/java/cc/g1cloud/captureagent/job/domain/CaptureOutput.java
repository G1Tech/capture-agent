package cc.g1cloud.captureagent.job.domain;

import java.util.Objects;

public record CaptureOutput(
        String location,
        String fileTemplate,
        int segmentTimeSec
) {
    public CaptureOutput {
        Objects.requireNonNull(location, "location must not be null");
        Objects.requireNonNull(fileTemplate, "fileTemplate must not be null");
    }
}
