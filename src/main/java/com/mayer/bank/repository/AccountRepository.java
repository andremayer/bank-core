package com.mayer.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mayer.bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}

