package com.jobportal.srm.service;

import com.jobportal.srm.entity.Company; // Import Company entity
import com.jobportal.srm.repository.CompanyRepository; // Import repository
import org.springframework.stereotype.Service; // Marks as service layer

import java.time.LocalDateTime; // For created_at
import java.util.List; // For list return

@Service // Spring manages this class
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository; // Constructor injection
    }

    // Create new company
    public Company createCompany(Company company) {

        company.setApproved(false);
        // Default: company is not approved initially

        company.setCreatedAt(LocalDateTime.now());
        // Set creation timestamp

        return companyRepository.save(company);
        // Save company to database
    }

    // Get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
        // Fetch all companies
    }

    // Get company by ID
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        // Return company or throw error
    }

    // Approve company (admin action later)
    public Company approveCompany(Long id) {

        Company company = getCompanyById(id);
        // Fetch company first

        company.setApproved(true);
        // Mark as approved

        return companyRepository.save(company);
        // Save updated company
    }
}