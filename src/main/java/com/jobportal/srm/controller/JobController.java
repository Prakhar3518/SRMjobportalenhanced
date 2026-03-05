package com.jobportal.srm.controller;

import com.jobportal.srm.dto.JobRequest;
import com.jobportal.srm.dto.JobResponse;
import com.jobportal.srm.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Create job
    @PostMapping
    @PreAuthorize("hasRole('COMPANY')") // only company can post job
    public JobResponse createJob(@RequestBody JobRequest request) {

        return jobService.createJob(request);
    }

    // Get all jobs
    @GetMapping
    public List<JobResponse> getAllJobs() {

        return jobService.getAllJobs();
    }


    //Update job
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public JobResponse updateJob(
            @PathVariable Long id,
            @RequestBody JobRequest request
    ) {
        return jobService.updateJob(id, request);
    }



    //Pagination
    @GetMapping("/paged")
    public Page<JobResponse> getJobsPaginated(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return jobService.getJobsPaginated(page, size);
    }

    //Searching job
    @GetMapping("/search")
    public List<JobResponse> searchJobs(

            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) Double cgpa

    ) {

        return jobService.searchJobs(location, skill, cgpa);
    }
}