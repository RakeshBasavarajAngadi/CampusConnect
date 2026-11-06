package com.campusconnect.campusconnect.controller;

import com.campusconnect.campusconnect.entity.Student;
import com.campusconnect.campusconnect.repository.StudentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RegisterController {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(StudentRepository studentRepository,
                              PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody(required = false) Student student) {

        if (student == null) {
            return ResponseEntity.badRequest()
                    .body("Request body cannot be empty");
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Name is required");
        }

        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Email is required");
        }

        String normalizedEmail = student.getEmail().trim().toLowerCase(Locale.ROOT);

        if (!EMAIL_PATTERN.matcher(normalizedEmail).matches()) {
            return ResponseEntity.badRequest()
                    .body("Invalid email format");
        }

        if (student.getPassword() == null || student.getPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Password is required");
        }

        if (student.getPassword().length() < 6) {
            return ResponseEntity.badRequest()
                    .body("Password must be at least 6 characters");
        }

        if (studentRepository.findByEmail(normalizedEmail).isPresent()) {
            return ResponseEntity.badRequest()
                    .body("Email already registered");
        }

        student.setId(null);
        student.setName(student.getName().trim());
        student.setEmail(normalizedEmail);
        student.setPassword(
                passwordEncoder.encode(student.getPassword())
        );

        try {
            studentRepository.saveAndFlush(student);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body("Email already registered");
        }

        return ResponseEntity.ok("Registration successful");
    }
}
