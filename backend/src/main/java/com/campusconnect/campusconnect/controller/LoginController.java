package com.campusconnect.campusconnect.controller;

import com.campusconnect.campusconnect.dto.LoginRequest;
import com.campusconnect.campusconnect.entity.Student;
import com.campusconnect.campusconnect.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(StudentRepository studentRepository,
                           PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (student == null) {
            return ResponseEntity.status(401)
                    .body("Student not found");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                student.getPassword())) {

            return ResponseEntity.status(401)
                    .body("Invalid password");
        }

        return ResponseEntity.ok("Login successful");
    }
}