package com.jobportal.srm.service;

import com.jobportal.srm.entity.Company; // Company entity
import com.jobportal.srm.entity.Job; // Job entity
import com.jobportal.srm.repository.CompanyRepository; // Company repository
import com.jobportal.srm.repository.JobRepository; // Job repository
import org.springframework.stereotype.Service;

import java.time.LocalDateTime; // For createdAt timestamp
import java.util.List; // For returning list of jobs

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    //Jo bhi inject krna ho usko constructor use krke idhr ek saath krte hein



    // Single constructor injection
    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    // Create a new job
    public Job createJob(Job job) {

        Company company = job.getCompany();
        // Get company object from request

        if (company == null || company.getId() == null) {
            throw new RuntimeException("Company must be provided");
        }

        Company existingCompany = companyRepository.findById(company.getId())
                .orElseThrow(() -> new RuntimeException("Company not found"));
        // Fetch company from DB

        if (!existingCompany.getApproved()) {
            throw new RuntimeException("Company is not approved");
        }

        job.setCompany(existingCompany);
        // Attach managed company entity

        job.setCreatedAt(LocalDateTime.now());
        // Set creation timestamp

        return jobRepository.save(job);
        // Save job
    }

    // Fetch all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Fetch job by ID
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    // Delete job by ID
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}