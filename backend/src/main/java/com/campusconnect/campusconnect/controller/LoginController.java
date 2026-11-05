package com.campusconnect.campusconnect.controller;

import com.campusconnect.campusconnect.dto.LoginRequest;
import com.campusconnect.campusconnect.entity.Student;
import com.campusconnect.campusconnect.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

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
    public ResponseEntity<String> login(@RequestBody(required = false) LoginRequest request) {

        if (request == null) {
            return ResponseEntity.badRequest()
                    .body("Request body cannot be empty");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Email is required");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Password is required");
        }

        String normalizedEmail = request.getEmail().trim().toLowerCase(Locale.ROOT);

        Student student = studentRepository.findByEmail(normalizedEmail)
                .orElse(null);

        if (student == null) {
            return ResponseEntity.status(401)
                    .body("Invalid email or password");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                student.getPassword())) {

            return ResponseEntity.status(401)
                    .body("Invalid email or password");
        }

        return ResponseEntity.ok("Login successful");
    }
}
