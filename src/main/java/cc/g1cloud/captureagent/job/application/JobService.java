package cc.g1cloud.captureagent.job.application;

import cc.g1cloud.captureagent.job.application.command.CreateJobCommand;
import cc.g1cloud.captureagent.job.domain.CaptureOutput;
import cc.g1cloud.captureagent.job.domain.CaptureSource;
import cc.g1cloud.captureagent.job.domain.Job;
import cc.g1cloud.captureagent.job.domain.JobMode;
import cc.g1cloud.captureagent.job.domain.JobRepository;
import cc.g1cloud.captureagent.job.domain.JobValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobService {

    private static final int MIN_SEGMENT_DURATION_SEC = 5;

    private final JobRepository jobRepository;

    public Job createJob(CreateJobCommand command) {
        validateCommand(command);
        var jobMode = JobMode.fromString(command.mode())
                .orElseThrow(() -> new JobValidationException("INVALID_MODE", "mode must be either 'copy' or 'transcode'"));

        int segmentTimeSec = command.output().segmentTimeSec();
        if (segmentTimeSec < MIN_SEGMENT_DURATION_SEC) {
            throw new JobValidationException(
                    "SEGMENT_TIME_TOO_SMALL",
                    "output.segmentTimeSec must be greater than or equal to " + MIN_SEGMENT_DURATION_SEC
            );
        }

        var job = new Job(
                UUID.randomUUID(),
                command.name(),
                new CaptureSource(command.source().type(), command.source().uri()),
                new CaptureOutput(command.output().location(), command.output().fileTemplate(), segmentTimeSec),
                jobMode,
                command.metadata(),
                Instant.now()
        );

        return jobRepository.save(job);
    }

    private void validateCommand(CreateJobCommand command) {
        if (!StringUtils.hasText(command.name())) {
            throw new JobValidationException("INVALID_NAME", "name must not be blank");
        }
        if (command.source() == null || !StringUtils.hasText(command.source().type()) || !StringUtils.hasText(command.source().uri())) {
            throw new JobValidationException("INVALID_SOURCE", "source.type and source.uri must be provided");
        }
        if (command.output() == null
                || !StringUtils.hasText(command.output().location())
                || !StringUtils.hasText(command.output().fileTemplate())
                || command.output().segmentTimeSec() == null) {
            throw new JobValidationException("INVALID_OUTPUT", "output.location, output.fileTemplate and output.segmentTimeSec must be provided");
        }
        if (!StringUtils.hasText(command.mode())) {
            throw new JobValidationException("INVALID_MODE", "mode must not be blank");
        }
    }
}
