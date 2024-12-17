package com.mayer.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mayer.bank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}