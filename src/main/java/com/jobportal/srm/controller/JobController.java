package com.jobportal.srm.controller;


import com.jobportal.srm.entity.Job;
import com.jobportal.srm.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    // Constructor injection of JobService
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    //Create job
    @PostMapping
    public Job createJob(@RequestBody Job job){
        return jobService.createJob(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
        // Returns list of all jobs
    }


    // GET JOB BY ID

       @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
        // Fetch specific job by id
    }


    // DELETE JOB

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        // Deletes job by id
    }
}
