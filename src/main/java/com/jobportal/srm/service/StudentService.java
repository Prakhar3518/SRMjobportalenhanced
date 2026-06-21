package com.jobportal.srm.service;

import com.jobportal.srm.dto.StudentRequest;
import com.jobportal.srm.dto.StudentResponse;
import com.jobportal.srm.entity.Student;
import com.jobportal.srm.entity.User;
import com.jobportal.srm.repository.StudentRepository;
import com.jobportal.srm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    // Create student profile
    public StudentResponse createProfile(StudentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (studentRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("Student profile already exists for this user");
        }

        Student student = new Student();
        student.setUser(user);
        student.setBranch(request.getBranch());
        student.setCgpa(request.getCgpa());
        student.setSkills(request.getSkills());
        student.setResumeUrl(request.getResumeUrl());

        return mapToResponse(studentRepository.save(student));
    }

    // Get profile by user ID
    public StudentResponse getProfileByUserId(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));
        return mapToResponse(student);
    }

    // Get profile by student ID
    public StudentResponse getProfileById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return mapToResponse(student);
    }

    // Get all student profiles (Admin use)
    public List<StudentResponse> getAllProfiles() {
        return studentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Update student profile
    public StudentResponse updateProfile(Long userId, StudentRequest request) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        student.setBranch(request.getBranch());
        student.setCgpa(request.getCgpa());
        student.setSkills(request.getSkills());
        student.setResumeUrl(request.getResumeUrl());

        return mapToResponse(studentRepository.save(student));
    }

    // Entity → DTO
    private StudentResponse mapToResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getUser().getId(),
                student.getUser().getName(),
                student.getUser().getEmail(),
                student.getBranch(),
                student.getCgpa(),
                student.getSkills(),
                student.getResumeUrl()
        );
    }
}
