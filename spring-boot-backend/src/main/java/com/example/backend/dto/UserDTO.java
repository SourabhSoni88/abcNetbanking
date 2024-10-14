package com.example.backend.dto;

public class UserDTO {
    private String email;
    private String mobileNumber;
    private String password;
    private String otp;
    private String twoFactorCode;

    public UserDTO() {
    }

    public UserDTO(String email, String mobileNumber, String password, String otp, String twoFactorCode) {
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.otp = otp;
        this.twoFactorCode = twoFactorCode;
    }

    // Getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getTwoFactorCode() {
        return twoFactorCode;
    }

    public void setTwoFactorCode(String twoFactorCode) {
        this.twoFactorCode = twoFactorCode;
    }
}