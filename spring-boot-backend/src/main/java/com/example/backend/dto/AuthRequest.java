package com.example.backend.dto;

public class AuthRequest {
    private String email;
    private String password;
    private String twoFactorCode;
    private String mobileNumber;
private String otp;
    public AuthRequest() {
    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTwoFactorCode() {
        return twoFactorCode;
    }

    public void setTwoFactorCode(String twoFactorCode) {
        this.twoFactorCode = twoFactorCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getOtp() {
        return otp;
    }
}