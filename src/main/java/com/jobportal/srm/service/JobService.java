package com.jobportal.srm.service;

import com.jobportal.srm.dto.JobRequest;
import com.jobportal.srm.dto.JobResponse;
import com.jobportal.srm.entity.Company;
import com.jobportal.srm.entity.Job;
import com.jobportal.srm.repository.CompanyRepository;
import com.jobportal.srm.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    // Create job
    public JobResponse createJob(JobRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        if (!company.getApproved()) {
            throw new RuntimeException("Company is not approved");
        }

        Job job = new Job();

        job.setCompany(company);
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setMinCgpa(request.getMinCgpa());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setLocation(request.getLocation());
        job.setDeadline(request.getDeadline());
        job.setCreatedAt(LocalDateTime.now());

        Job saved = jobRepository.save(job);

        return mapToResponse(saved);
    }

    // Get all jobs
    public List<JobResponse> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //Update Job

    public JobResponse updateJob(Long id, JobRequest request) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        // Find job or throw error

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));
        // Validate company exists

        if (!company.getApproved()) {
            throw new RuntimeException("Company is not approved");
        }
        // Only approved companies can update jobs

        job.setCompany(company); // update company
        job.setTitle(request.getTitle()); // update title
        job.setDescription(request.getDescription()); // update description
        job.setMinCgpa(request.getMinCgpa()); // update cgpa
        job.setRequiredSkills(request.getRequiredSkills()); // update skills
        job.setLocation(request.getLocation()); // update location
        job.setDeadline(request.getDeadline()); // update deadline

        Job updated = jobRepository.save(job); // save updated job

        return mapToResponse(updated); // return DTO response
    }






    // Entity → DTO
    private JobResponse mapToResponse(Job job) {

        return new JobResponse(
                job.getId(),
                job.getCompany().getCompanyName(),
                job.getTitle(),
                job.getDescription(),
                job.getMinCgpa(),
                job.getRequiredSkills(),
                job.getLocation(),
                job.getDeadline(),
                job.getCreatedAt()
        );
    }
}