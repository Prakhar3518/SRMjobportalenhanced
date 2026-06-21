package com.jobportal.srm.controller;

import com.jobportal.srm.dto.ApplicationRequest;
import com.jobportal.srm.dto.ApplicationResponse;
import com.jobportal.srm.dto.ApplicationStatusUpdateRequest;
import com.jobportal.srm.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // STUDENT applies to a job
    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApplicationResponse> apply(@RequestBody ApplicationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.applyToJob(request));
    }

    // Get applications of a student (student views own, admin views any)
    @GetMapping("/student/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public ResponseEntity<List<ApplicationResponse>> getByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationsByStudent(id));
    }

    // Recruiter/Company views applicants for a job
    @GetMapping("/job/{id}")
    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    public ResponseEntity<List<ApplicationResponse>> getByJob(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(id));
    }

    // Company/Admin updates application status (shortlist / reject)
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @PathVariable Long id,
            @RequestBody ApplicationStatusUpdateRequest request
    ) {
        return ResponseEntity.ok(
                applicationService.updateApplicationStatus(id, request.getStatus())
        );
    }
}
