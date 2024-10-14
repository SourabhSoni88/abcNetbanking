package com.example.backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String description;
    private double amount;
    private String accountNumber;
    private String beneficiaryAccountNumber;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    // Constructors, getters, and setters

    public Transaction() {
    }

    public Transaction(LocalDateTime timestamp, String description, double amount, String accountNumber, String beneficiaryAccountNumber) {
        this.timestamp = timestamp;
        this.description = description;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }
}