package com.campusconnect.campusconnect.controller;

import com.campusconnect.campusconnect.dto.StudentResponse;
import com.campusconnect.campusconnect.entity.Student;
import com.campusconnect.campusconnect.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentController(StudentRepository studentRepository,
                             PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public StudentResponse addStudent(@RequestBody Student student) {

        student.setPassword(
                passwordEncoder.encode(student.getPassword())
        );

        Student savedStudent = studentRepository.save(student);

        return new StudentResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getEmail()
        );
    }
}