package com.jobportal.srm.service;

import com.jobportal.srm.dto.JobRequest;
import com.jobportal.srm.dto.JobResponse;
import com.jobportal.srm.entity.Company;
import com.jobportal.srm.entity.Job;
import com.jobportal.srm.repository.CompanyRepository;
import com.jobportal.srm.repository.JobRepository;
import com.jobportal.srm.specification.JobSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
            throw new RuntimeException("Company is not approved yet");
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

        return mapToResponse(jobRepository.save(job));
    }

    // Get all jobs
    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get job by ID
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return mapToResponse(job);
    }

    // Update job
    public JobResponse updateJob(Long id, JobRequest request) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        if (!company.getApproved()) {
            throw new RuntimeException("Company is not approved yet");
        }

        job.setCompany(company);
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setMinCgpa(request.getMinCgpa());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setLocation(request.getLocation());
        job.setDeadline(request.getDeadline());

        return mapToResponse(jobRepository.save(job));
    }

    // Delete job
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found");
        }
        jobRepository.deleteById(id);
    }

    // Paginated jobs
    public Page<JobResponse> getJobsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jobRepository.findAll(pageable).map(this::mapToResponse);
    }

    // Jobs by company
    public List<JobResponse> getJobsByCompany(Long companyId) {
        return jobRepository.findByCompanyId(companyId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Search & filter
    public List<JobResponse> searchJobs(String location, String skill, Double cgpa) {
        Specification<Job> spec = Specification
                .where(JobSpecification.hasLocation(location))
                .and(JobSpecification.hasSkill(skill))
                .and(JobSpecification.hasCgpa(cgpa));

        return jobRepository.findAll(spec).stream()
                .map(this::mapToResponse)
                .toList();
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
