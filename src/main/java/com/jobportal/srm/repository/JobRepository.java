package com.jobportal.srm.repository;

import com.jobportal.srm.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
    // JpaRepository gives CRUD methods automatically

}
