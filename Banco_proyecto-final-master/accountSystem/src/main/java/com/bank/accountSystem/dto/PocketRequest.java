package com.bank.accountSystem.dto;

public class PocketRequest {
    private String accountNumber;
    private String name;
    private String pocketNumber;
    private double pocketInitialBalance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPocketInitialBalance() {
        return pocketInitialBalance;
    }

    public void setPocketInitialBalance(double pocketInitialBalance) {
        this.pocketInitialBalance = pocketInitialBalance;
    }

    public String getPocketNumber() {
        return pocketNumber;
    }

    public void setPocketNumber(String pocketNumber) {
        this.pocketNumber = pocketNumber;
    }
}
