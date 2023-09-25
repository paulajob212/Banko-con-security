package com.bank.accountSystem.dto;

public class TransferRequest {
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private double amount;

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
