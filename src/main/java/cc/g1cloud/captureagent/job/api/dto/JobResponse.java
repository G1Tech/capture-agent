package cc.g1cloud.captureagent.job.api.dto;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record JobResponse(
        UUID id,
        String name,
        SourceResponse source,
        OutputResponse output,
        String mode,
        Map<String, String> metadata,
        Instant createdAt
) {

    public record SourceResponse(
            String type,
            String uri
    ) {
    }

    public record OutputResponse(
            String location,
            String fileTemplate,
            int segmentTimeSec
    ) {
    }
}
