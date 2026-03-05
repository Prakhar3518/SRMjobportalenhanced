package com.jobportal.srm.repository;

import com.jobportal.srm.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentId(Long studentId); // get applications of a student

    List<Application> findByJobId(Long jobId); // get applicants for a job

    boolean existsByStudentIdAndJobId(Long studentId, Long jobId);
    // prevent duplicate application
}
