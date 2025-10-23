package cc.g1cloud.captureagent.job.api.mapper;

import cc.g1cloud.captureagent.job.api.dto.JobResponse;
import cc.g1cloud.captureagent.job.domain.Job;
import org.springframework.stereotype.Component;

@Component
public class JobResponseMapper {

    public JobResponse toResponse(Job job) {
        return new JobResponse(
                job.id(),
                job.name(),
                new JobResponse.SourceResponse(job.source().type(), job.source().uri()),
                new JobResponse.OutputResponse(
                        job.output().location(),
                        job.output().fileTemplate(),
                        job.output().segmentTimeSec()
                ),
                job.mode().asToken(),
                job.metadata(),
                job.createdAt()
        );
    }
}
