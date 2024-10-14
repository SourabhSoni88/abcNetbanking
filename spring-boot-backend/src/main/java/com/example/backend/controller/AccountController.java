package com.example.backend.controller;

import com.example.backend.dto.AccountDTO;
import com.example.backend.dto.BeneficiaryDTO;
import com.example.backend.dto.FundTransferDTO;
import com.example.backend.dto.TransactionDTO;
import com.example.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{userId}")
    public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable Long userId) {
        AccountDTO accountDTO = accountService.getAccountDetails(userId);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/{userId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<TransactionDTO> transactions = accountService.getTransactionHistory(userId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/transfer/intra")
    public ResponseEntity<String> intraBankTransfer(@RequestBody FundTransferDTO fundTransferDTO) {
        accountService.intraBankTransfer(fundTransferDTO);
        return ResponseEntity.ok("Intra-bank transfer successful");
    }

    @PostMapping("/transfer/inter")
    public ResponseEntity<String> interBankTransfer(@RequestBody FundTransferDTO fundTransferDTO) {
        accountService.interBankTransfer(fundTransferDTO);
        return ResponseEntity.ok("Inter-bank transfer successful");
    }

    @PostMapping("/beneficiaries")
    public ResponseEntity<String> addBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO) {
        accountService.addBeneficiary(beneficiaryDTO);
        return ResponseEntity.ok("Beneficiary added successfully");
    }

    @PutMapping("/beneficiaries/{beneficiaryId}")
    public ResponseEntity<String> updateBeneficiary(
            @PathVariable Long beneficiaryId,
            @RequestBody BeneficiaryDTO beneficiaryDTO) {
        accountService.updateBeneficiary(beneficiaryId, beneficiaryDTO);
        return ResponseEntity.ok("Beneficiary updated successfully");
    }

    @DeleteMapping("/beneficiaries/{beneficiaryId}")
    public ResponseEntity<String> deleteBeneficiary(@PathVariable Long beneficiaryId) {
        accountService.deleteBeneficiary(beneficiaryId);
        return ResponseEntity.ok("Beneficiary deleted successfully");
    }

    @GetMapping("/{userId}/statement")
    public ResponseEntity<byte[]> downloadStatement(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String format) {
        byte[] statement = accountService.downloadStatement(userId, startDate, endDate, format);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=statement." + format)
                .body(statement);
    }
}