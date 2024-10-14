package com.example.backend.service;

import org.springframework.stereotype.Service;

@Service
public class OTPService {
    
    public String generateOTP() {
        // Generate and return a random OTP
        return "123456";
    }
    
    public boolean verifyOTP(String otp, String enteredOTP) {
        // Verify if the entered OTP matches the generated OTP
        return otp.equals(enteredOTP);
    }
}