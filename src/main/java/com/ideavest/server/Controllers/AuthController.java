package com.ideavest.server.Controllers;

import com.ideavest.server.Repositories.UserRepository;
import com.ideavest.server.Security.JwtUtil;
import com.ideavest.server.dtos.AuthResponse;
import com.ideavest.server.dtos.LoginRequest;
import com.ideavest.server.dtos.RegisterRequest;
import com.ideavest.server.models.User;
import com.ideavest.server.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // Allow only "IDEA_HOLDER" and "INVESTOR" roles
        if (request.getRole() != UserRole.IDEA_HOLDER && request.getRole() != UserRole.INVESTOR) {
            return ResponseEntity.badRequest().body("Only Idea Holders and Investors can register.");
        }

        // Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Email is already registered.");
        }

        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash password
        user.setRole(request.getRole());
        user.setAvatarUrl(request.getAvatarUrl());

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty() || !passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password.");
        }

        String token = jwtUtil.generateToken(user.get().getEmail(), user.get().getRole());
        return ResponseEntity.ok(new AuthResponse(token, "Login successful.", user.get()));
    }
}
