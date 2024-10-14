# Spring Boot Backend

This is a Spring Boot backend project that provides the following functionality:

## User Management
- User registration with email, mobile number, password, and 2FA
- User login with JWT
- Password reset with OTP verification

## Account Management
- View account details (balance, available credit, last transactions)
- Manage beneficiaries (add, modify, delete)

## Fund Transfers
- Intra Bank fund transfers
- Inter Bank fund transfers

## Transaction History and Statements
- View transaction history for a user-defined period
- Download statements in PDF or CSV format

## Notifications
- Mobile notifications
- Email and SMS alerts for important events

## Technical Requirements
- Front End: Pure JavaScript Framework
- Back End: Spring Boot
- Database: MySQL to store customer and transactional data
- Security: JWT authentication and HTTPS encryption

## Project Structure

The project has the following structure:

```
spring-boot-backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── backend
│   │   │               ├── BackendApplication.java
│   │   │               ├── config
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller
│   │   │               │   ├── AccountController.java
│   │   │               │   ├── AuthController.java
│   │   │               │   └── UserController.java
│   │   │               ├── dto
│   │   │               │   ├── AccountDTO.java
│   │   │               │   ├── AuthRequest.java
│   │   │               │   ├── AuthResponse.java
│   │   │               │   ├── FundTransferDTO.java
│   │   │               │   └── UserDTO.java
│   │   │               ├── model
│   │   │               │   ├── Account.java
│   │   │               │   ├── Beneficiary.java
│   │   │               │   ├── Transaction.java
│   │   │               │   └── User.java
│   │   │               ├── repository
│   │   │               │   ├── AccountRepository.java
│   │   │               │   ├── BeneficiaryRepository.java
│   │   │               │   ├── TransactionRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── service
│   │   │               │   ├── AccountService.java
│   │   │               │   ├── AuthService.java
│   │   │               │   ├── EmailService.java
│   │   │               │   ├── FundTransferService.java
│   │   │               │   ├── OTPService.java
│   │   │               │   └── UserService.java
│   │   │               └── util
│   │   │                   ├── JwtUtil.java
│   │   │                   └── PdfGenerator.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── backend
│                       └── BackendApplicationTests.java
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

Please refer to the individual files for more details on their implementation.
```

Let me know if you need any further assistance!