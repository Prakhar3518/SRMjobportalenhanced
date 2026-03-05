package com.jobportal.srm.controller;

import com.jobportal.srm.dto.ApplicationRequest;
import com.jobportal.srm.dto.ApplicationResponse;
import com.jobportal.srm.dto.ApplicationStatusUpdateRequest;
import com.jobportal.srm.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    // student applies to job
    @PostMapping
    public ApplicationResponse apply(@RequestBody ApplicationRequest request) {
        return applicationService.applyToJob(request);
    }

    // get applications of a student
    @GetMapping("/student/{id}")
    public List<ApplicationResponse> getByStudent(@PathVariable Long id) {
        return applicationService.getApplicationsByStudent(id);
    }

    // get applicants for a job
    @GetMapping("/job/{id}")
    public List<ApplicationResponse> getByJob(@PathVariable Long id) {
        return applicationService.getApplicationsByJob(id);
    }

    //Status update (selection or rejection)
    @PutMapping("/{id}/status")
    public ApplicationResponse updateStatus(
            @PathVariable Long id,
            @RequestBody ApplicationStatusUpdateRequest request
    ) {

        return applicationService.updateApplicationStatus(
                id,
                request.getStatus()
        );
    }
}