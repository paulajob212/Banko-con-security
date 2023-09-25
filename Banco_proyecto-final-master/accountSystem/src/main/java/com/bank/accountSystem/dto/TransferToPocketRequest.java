package com.bank.accountSystem.dto;

public class TransferToPocketRequest {
    private String accountNumber;
    private String pocketNumber;
    private double amount;

    public TransferToPocketRequest() {
    }

    public TransferToPocketRequest(String accountNumber, String pocketNumber, double amount) {
        this.accountNumber = accountNumber;
        this.pocketNumber = pocketNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPocketNumber() {
        return pocketNumber;
    }

    public void setPocketNumber(String pocketNumber) {
        this.pocketNumber = pocketNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
