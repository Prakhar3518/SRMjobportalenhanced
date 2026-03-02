package com.jobportal.srm.controller;

import com.jobportal.srm.entity.Company; // Import entity
import com.jobportal.srm.service.CompanyService; // Import service
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks as REST controller
@RequestMapping("/companies") // Base URL
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService; // Inject service
    }


    // Create company
    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
        // Calls service to save company
    }

    // Get all companies
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
        // Returns list of companies
    }

    // Get company by id
    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
        // Fetch specific company
    }

    // Approve company
    @PutMapping("/{id}/approve")
    public Company approveCompany(@PathVariable Long id) {
        return companyService.approveCompany(id);
        // Marks company as approved
    }
}