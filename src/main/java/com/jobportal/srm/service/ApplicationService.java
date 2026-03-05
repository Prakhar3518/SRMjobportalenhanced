package com.jobportal.srm.service;

import com.jobportal.srm.dto.ApplicationRequest;
import com.jobportal.srm.dto.ApplicationResponse;
import com.jobportal.srm.entity.Application;
import com.jobportal.srm.entity.Job;
import com.jobportal.srm.entity.User;
import com.jobportal.srm.enums.ApplicationStatus;
import com.jobportal.srm.repository.ApplicationRepository;
import com.jobportal.srm.repository.JobRepository;
import com.jobportal.srm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    // constructor injection
    public ApplicationService(ApplicationRepository applicationRepository,
                              UserRepository userRepository,
                              JobRepository jobRepository) {

        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }


    //Apply
    public ApplicationResponse applyToJob(ApplicationRequest request) {

        // fetch student from database
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // fetch job from database
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // check if student already applied
        if (applicationRepository.existsByStudentIdAndJobId(student.getId(), job.getId())) {
            throw new RuntimeException("You already applied to this job");
        }

        // create new application
        Application application = new Application();

        application.setStudent(student); // set student
        application.setJob(job); // set job
        application.setStatus(ApplicationStatus.APPLIED); // default status
        application.setAppliedAt(LocalDateTime.now()); // set timestamp

        // save to database
        Application saved = applicationRepository.save(application);

        // convert entity → response DTO
        return new ApplicationResponse(
                saved.getId(),
                saved.getStudent().getId(),
                saved.getJob().getId(),
                saved.getStatus(),
                saved.getAppliedAt()
        );
    }

    //get applications of a student
    public List<ApplicationResponse> getApplicationsByStudent(Long studentId) {

        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(app -> new ApplicationResponse(
                        app.getId(),
                        app.getStudent().getId(),
                        app.getJob().getId(),
                        app.getStatus(),
                        app.getAppliedAt()
                ))
                .collect(Collectors.toList());
    }

    //get applications for a job
    public List<ApplicationResponse> getApplicationsByJob(Long jobId) {

        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(app -> new ApplicationResponse(
                        app.getId(),
                        app.getStudent().getId(),
                        app.getJob().getId(),
                        app.getStatus(),
                        app.getAppliedAt()
                ))
                .collect(Collectors.toList());
    }



    //Selection or rejection status
    public ApplicationResponse updateApplicationStatus(
            Long applicationId,
            ApplicationStatus status
    ) {

        // find application
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // update status
        application.setStatus(status);

        // save updated application
        Application updated = applicationRepository.save(application);

        // convert entity → DTO
        return new ApplicationResponse(
                updated.getId(),
                updated.getStudent().getId(),
                updated.getJob().getId(),
                updated.getStatus(),
                updated.getAppliedAt()
        );
    }
}