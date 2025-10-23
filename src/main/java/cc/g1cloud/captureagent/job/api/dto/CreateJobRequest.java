package cc.g1cloud.captureagent.job.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Map;

public record CreateJobRequest(
        @NotBlank(message = "name must not be blank")
        String name,
        @NotNull(message = "source must be provided") @Valid
        SourceDto source,
        @NotNull(message = "output must be provided") @Valid
        OutputDto output,
        @NotBlank(message = "mode must not be blank")
        String mode,
        Map<String, String> metadata
) {

    public record SourceDto(
            @NotBlank(message = "source.type must not be blank")
            String type,
            @NotBlank(message = "source.uri must not be blank")
            String uri
    ) {
    }

    public record OutputDto(
            @NotBlank(message = "output.location must not be blank")
            String location,
            @NotBlank(message = "output.fileTemplate must not be blank")
            String fileTemplate,
            @NotNull(message = "output.segmentTimeSec must be provided")
            @Positive(message = "output.segmentTimeSec must be a positive number")
            Integer segmentTimeSec
    ) {
    }
}
