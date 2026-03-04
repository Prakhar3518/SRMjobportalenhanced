package com.jobportal.srm.controller;

import com.jobportal.srm.dto.CompanyRequest;
import com.jobportal.srm.dto.CompanyResponse;
import com.jobportal.srm.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService; // Inject service
    }

    // Create company
    @PostMapping
    public CompanyResponse createCompany(@RequestBody CompanyRequest request) {

        return companyService.createCompany(request); // Call service
    }

    // Get all companies
    @GetMapping
    public List<CompanyResponse> getAllCompanies() {

        return companyService.getAllCompanies();
    }

    // Get company by ID
    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id) {

        return companyService.getCompanyById(id);
    }

    // Approve company
    @PutMapping("/{id}/approve")
    public CompanyResponse approveCompany(@PathVariable Long id) {

        return companyService.approveCompany(id);
    }
}