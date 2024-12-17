package com.mayer.bank.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransactionRequestDTO {
	
	@NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotNull(message = "Transaction type is required")
    private String transactionType;
    
    public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
