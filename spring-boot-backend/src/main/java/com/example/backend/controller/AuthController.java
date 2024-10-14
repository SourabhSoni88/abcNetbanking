package com.example.backend.controller;

import com.example.backend.dto.AuthRequest;
import com.example.backend.dto.AuthResponse;
import com.example.backend.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Api(value = "Authentication Controller", description = "Operations related to user authentication")

public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ApiOperation(value = "Register a new user")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequest authRequest) {
        authService.registerUser(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.loginUser(authRequest.getEmail(), authRequest.getPassword(), authRequest.getTwoFactorCode());
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody AuthRequest authRequest) {
        authService.resetPassword(authRequest);
        return ResponseEntity.ok("Password reset successful");
    }
}