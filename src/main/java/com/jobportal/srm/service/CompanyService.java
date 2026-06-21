package com.jobportal.srm.service;

import com.jobportal.srm.dto.CompanyRequest;
import com.jobportal.srm.dto.CompanyResponse;
import com.jobportal.srm.entity.Company;
import com.jobportal.srm.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Create company
    public CompanyResponse createCompany(CompanyRequest request) {
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setDescription(request.getDescription());
        company.setApproved(false); // pending admin approval
        company.setCreatedAt(LocalDateTime.now());
        return mapToResponse(companyRepository.save(company));
    }

    // Get all companies
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get company by ID
    public CompanyResponse getCompanyById(Long id) {
        return mapToResponse(companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found")));
    }

    // Approve company (Admin action)
    public CompanyResponse approveCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        company.setApproved(true);
        return mapToResponse(companyRepository.save(company));
    }

    // Update company info
    public CompanyResponse updateCompany(Long id, CompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        company.setCompanyName(request.getCompanyName());
        company.setDescription(request.getDescription());
        return mapToResponse(companyRepository.save(company));
    }

    // Delete company
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found");
        }
        companyRepository.deleteById(id);
    }

    // Entity → DTO
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
