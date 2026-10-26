package com.campusconnect.campusconnect.controller;

import com.campusconnect.campusconnect.entity.Student;
import com.campusconnect.campusconnect.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RegisterController {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(StudentRepository studentRepository,
                              PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Student student) {

        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body("Email already registered");
        }

        student.setPassword(
                passwordEncoder.encode(student.getPassword())
        );

        studentRepository.save(student);

        return ResponseEntity.ok("Registration successful");
    }
}