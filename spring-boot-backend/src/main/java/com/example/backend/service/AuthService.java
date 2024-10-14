package com.example.backend.service;

import com.example.backend.dto.AuthRequest;
import com.example.backend.dto.AuthResponse;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void registerUser(AuthRequest userDTO) {
        // Check if user already exists
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        // Create new user
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setTwoFactorCode(userDTO.getTwoFactorCode()); // Assuming you have a field for 2FA code

        // Save user to the database
        userRepository.save(user);
    }

        public AuthResponse loginUser(String email, String password, String twoFactorCode) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            } catch (Exception e) {
                throw new RuntimeException("Invalid email or password");
            }

            // Load user details
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            // Validate 2FA code
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (!user.getTwoFactorCode().equals(twoFactorCode)) {
                    throw new RuntimeException("Invalid 2FA code");
                }
            } else {
                throw new RuntimeException("User not found");
            }

            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails);
            return new AuthResponse(token);
        }


    public String resetPassword(AuthRequest authRequest) {
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Validate OTP
            if (!user.getOtp().equals(authRequest.getOtp())) {
                throw new RuntimeException("Invalid OTP");
            }
            // Update password
            user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            userRepository.save(user);
            return "Success";
        } else {
            throw new RuntimeException("User not found");
        }
    }
}