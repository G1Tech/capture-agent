package cc.g1cloud.captureagent.job.api;

import cc.g1cloud.captureagent.job.api.dto.CreateJobRequest;
import cc.g1cloud.captureagent.job.api.dto.JobResponse;
import cc.g1cloud.captureagent.job.api.mapper.JobRequestMapper;
import cc.g1cloud.captureagent.job.api.mapper.JobResponseMapper;
import cc.g1cloud.captureagent.job.application.JobService;
import cc.g1cloud.captureagent.job.application.command.CreateJobCommand;
import cc.g1cloud.captureagent.job.domain.Job;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private final JobRequestMapper jobRequestMapper;
    private final JobResponseMapper jobResponseMapper;

    @PostMapping
    public ResponseEntity<JobResponse> create(@Valid @RequestBody CreateJobRequest request) {
        CreateJobCommand command = jobRequestMapper.toCommand(request);
        Job job = jobService.createJob(command);
        JobResponse response = jobResponseMapper.toResponse(job);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(job.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }
}
