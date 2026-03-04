package com.jobportal.srm.service;

import com.jobportal.srm.dto.CompanyRequest; // DTO for incoming request
import com.jobportal.srm.dto.CompanyResponse; // DTO for outgoing response
import com.jobportal.srm.entity.Company; // Entity
import com.jobportal.srm.repository.CompanyRepository; // Repository
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository; // Inject repository
    }

    // Create company
    public CompanyResponse createCompany(CompanyRequest request) {

        Company company = new Company(); // Create entity object

        company.setCompanyName(request.getCompanyName()); // Set name from DTO
        company.setDescription(request.getDescription()); // Set description
        company.setApproved(false); // Default approval false
        company.setCreatedAt(LocalDateTime.now()); // Set creation timestamp

        Company saved = companyRepository.save(company); // Save in DB

        return mapToResponse(saved); // Convert entity → response DTO
    }

    // Get all companies
    public List<CompanyResponse> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(this::mapToResponse) // Convert each entity → DTO
                .collect(Collectors.toList());
    }

    // Get company by ID
    public CompanyResponse getCompanyById(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return mapToResponse(company);
    }

    // Approve company
    public CompanyResponse approveCompany(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setApproved(true); // Mark approved

        Company saved = companyRepository.save(company);

        return mapToResponse(saved);
    }

    // Helper method: Entity → DTO
    private CompanyResponse mapToResponse(Company company) {

        return new CompanyResponse(
                company.getId(),
                company.getCompanyName(),
                company.getDescription(),
                company.getApproved(),
                company.getCreatedAt()
        );
    }
}