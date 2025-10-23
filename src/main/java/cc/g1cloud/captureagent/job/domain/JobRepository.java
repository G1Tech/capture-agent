package cc.g1cloud.captureagent.job.domain;

import java.util.Optional;
import java.util.UUID;

public interface JobRepository {

    Job save(Job job);

    Optional<Job> findById(UUID id);
}
