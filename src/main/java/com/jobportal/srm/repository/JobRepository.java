package com.jobportal.srm.repository;

import com.jobportal.srm.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

//Search feature
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> ,JpaSpecificationExecutor<Job>{
    // JpaRepository gives CRUD methods automatically
    List<Job> findByCompanyId(Long companyId);

}
