package cc.g1cloud.captureagent.job.infrastructure;

import cc.g1cloud.captureagent.job.domain.Job;
import cc.g1cloud.captureagent.job.domain.JobRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryJobRepository implements JobRepository {

    private final ConcurrentMap<UUID, Job> storage = new ConcurrentHashMap<>();

    @Override
    public Job save(Job job) {
        storage.put(job.id(), job);
        return job;
    }

    @Override
    public Optional<Job> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }
}
