package cc.g1cloud.captureagent.job.application.command;

import java.util.Map;
import java.util.Objects;

public record CreateJobCommand(
        String name,
        Source source,
        Output output,
        String mode,
        Map<String, String> metadata
) {

    public CreateJobCommand {
        Objects.requireNonNull(source, "source must not be null");
        Objects.requireNonNull(output, "output must not be null");
        metadata = metadata == null ? Map.of() : Map.copyOf(metadata);
    }

    public record Source(
            String type,
            String uri
    ) {
    }

    public record Output(
            String location,
            String fileTemplate,
            Integer segmentTimeSec
    ) {
    }
}
