package com.example.backend.service;

import com.example.backend.dto.UserDTO;
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
import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService {

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

    public User registerUser(String email, String mobileNumber, String password, String twoFactorCode) {
        // Check if user already exists
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        // Create new user
        User user = new User();
        user.setEmail(email);
        user.setMobileNumber(mobileNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.setTwoFactorCode(twoFactorCode); // Assuming you have a field for 2FA code

        // Save user to the database
        return userRepository.save(user);
    }

    public String loginUser(String email, String password, String twoFactorCode) {
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
        return jwtUtil.generateToken(userDetails);
    }

    public User registerUser(UserDTO userDTO) {
        this.registerUser(userDTO.getEmail(), userDTO.getMobileNumber(), userDTO.getPassword(), userDTO.getTwoFactorCode());
        return null;
    }
    public User updateUserDetails(Long userId, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmail(userDTO.getEmail());
            user.setMobileNumber(userDTO.getMobileNumber());
            user.setTwoFactorCode(userDTO.getTwoFactorCode());
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            userRepository.save(user);
            return user;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public UserDTO getUserDetails(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(user.getEmail());
            userDTO.setMobileNumber(user.getMobileNumber());
            userDTO.setTwoFactorCode(user.getTwoFactorCode());
            // Do not include password in the response
            return userDTO;
        } else {
            throw new RuntimeException("User not found");
        }
    }
}

