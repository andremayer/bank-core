package com.mayer.bank.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.mayer.bank.model.Account;
import com.mayer.bank.model.Transaction;
import com.mayer.bank.repository.AccountRepository;
import com.mayer.bank.repository.TransactionRepository;
import jakarta.transaction.Transactional;

@Service
public class BankingService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankingService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Account createAccount(String accountNumber) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        return accountRepository.save(account);
    }

    @Transactional
    public BigDecimal deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");
        transactionRepository.save(transaction);

        return account.getBalance();
    }

    @Transactional
    public BigDecimal withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount.negate());
        transaction.setType("WITHDRAWAL");
        transactionRepository.save(transaction);

        return account.getBalance();
    }

    public BigDecimal checkBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        return account.getBalance();
    }
    
}