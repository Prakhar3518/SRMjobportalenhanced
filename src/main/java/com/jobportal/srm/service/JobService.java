package com.jobportal.srm.service;

import com.jobportal.srm.entity.Job;
import com.jobportal.srm.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime; // For createdAt timestamp
import java.util.List; // For returning list of jobs



@Service
public class JobService {

    private final JobRepository jobRepository;

    // Constructor injection
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    // Create a new job
    public Job createJob(Job job) {

        job.setCreatedAt(LocalDateTime.now());
        // Automatically set created_at before saving

        return jobRepository.save(job);
        // Save job in database
    }



    // Fetch all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
        // JpaRepository gives this method automatically
    }



    // Fetch job by ID
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        // If job not found, throw error
    }



    // Delete job by ID
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
        // Deletes job with given id
    }
}