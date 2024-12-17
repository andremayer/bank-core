package com.mayer.bank.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mayer.bank.dto.AccountRequestDTO;
import com.mayer.bank.dto.AccountResponseDTO;
import com.mayer.bank.dto.TransactionRequestDTO;
import com.mayer.bank.dto.TransactionResponseDTO;
import com.mayer.bank.model.Account;
import com.mayer.bank.service.BankingService;

@RestController
@RequestMapping("/api")
public class BankingController {
    private final BankingService bankingService;

    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO) {
    	Account acc = bankingService.createAccount(accountRequestDTO.getAccountNumber());
    	AccountResponseDTO accDTO = new AccountResponseDTO();
    	accDTO.setAccountNumber(acc.getAccountNumber());
    	accDTO.setBalance(acc.getBalance().doubleValue());
		return ResponseEntity.status(HttpStatus.CREATED).body(accDTO);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponseDTO> deposit(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        BigDecimal newBalance = bankingService.deposit(transactionRequestDTO.getAccountNumber(), BigDecimal.valueOf(transactionRequestDTO.getAmount()));
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setAccountNumber(transactionRequestDTO.getAccountNumber());
        transactionResponseDTO.setUpdatedBalance(newBalance.doubleValue());
        return ResponseEntity.ok(transactionResponseDTO);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponseDTO> withdraw(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        BigDecimal newBalance = bankingService.withdraw(transactionRequestDTO.getAccountNumber(), BigDecimal.valueOf(transactionRequestDTO.getAmount()));
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setAccountNumber(transactionRequestDTO.getAccountNumber());
        transactionResponseDTO.setUpdatedBalance(newBalance.doubleValue());
        return ResponseEntity.ok(transactionResponseDTO);
    }

    @GetMapping("/balance")
    public ResponseEntity<AccountResponseDTO> checkBalance(@RequestParam String accountNumber) {
        BigDecimal balance = bankingService.checkBalance(accountNumber);
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setAccountNumber(accountNumber);
        accountResponseDTO.setBalance(balance.doubleValue());
        return ResponseEntity.ok(accountResponseDTO);
    }
    
}