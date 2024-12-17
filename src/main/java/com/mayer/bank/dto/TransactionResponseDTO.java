package com.mayer.bank.dto;
public class TransactionResponseDTO {

    private String transactionId;
    private String accountNumber;
    private Double updatedBalance;
    private String status; 

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(Double updatedBalance) {
        this.updatedBalance = updatedBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
