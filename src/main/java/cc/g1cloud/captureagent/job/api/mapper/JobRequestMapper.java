package cc.g1cloud.captureagent.job.api.mapper;

import cc.g1cloud.captureagent.job.api.dto.CreateJobRequest;
import cc.g1cloud.captureagent.job.application.command.CreateJobCommand;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JobRequestMapper {

    public CreateJobCommand toCommand(CreateJobRequest request) {
        var sourceDto = request.source();
        var outputDto = request.output();
        Map<String, String> metadata = request.metadata() == null
                ? Map.of()
                : request.metadata().entrySet().stream()
                .filter(entry -> entry.getKey() != null && entry.getValue() != null)
                .collect(Collectors.toMap(
                        entry -> trim(entry.getKey()),
                        entry -> trim(entry.getValue()),
                        (left, right) -> right,
                        LinkedHashMap::new
                ));

        return new CreateJobCommand(
                trim(request.name()),
                new CreateJobCommand.Source(
                        trim(sourceDto.type()),
                        trim(sourceDto.uri())
                ),
                new CreateJobCommand.Output(
                        trim(outputDto.location()),
                        trim(outputDto.fileTemplate()),
                        outputDto.segmentTimeSec()
                ),
                trim(request.mode()),
                metadata
        );
    }

    private String trim(String value) {
        return value == null ? null : StringUtils.trimWhitespace(value);
    }
}
