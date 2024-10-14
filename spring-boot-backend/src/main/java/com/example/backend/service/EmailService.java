package com.example.backend.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    public void sendEmail(String recipient, String subject, String message) {
        // Logic to send email notification
        System.out.println("Sending email to: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}