package com.bank.accountSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pocket")
public class Pocket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String pocketName;
    @Column
    private String pocketNumber;
    @Column
    private double pocketInitialBalance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    public Pocket() {
    }

    public Pocket(Integer id, String pocketName, String pocketNumber, double pocketInitialBalance, Account account) {
        this.id = id;
        this.pocketName = pocketName;
        this.pocketNumber = pocketNumber;
        this.pocketInitialBalance = pocketInitialBalance;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPocketName() {
        return pocketName;
    }

    public void setPocketName(String pocketName) {
        this.pocketName = pocketName;
    }

    public double getPocketInitialBalance() {
        return pocketInitialBalance;
    }

    public void setPocketInitialBalance(double pocketInitialBalance) {
        this.pocketInitialBalance = pocketInitialBalance;
    }

    public Account getAccountNumber() {
        return account;
    }

    public void setAccountNumber(Account accountNumber) {
        this.account = accountNumber;
    }

    public String getPocketNumber() {
        return pocketNumber;
    }

    public void setPocketNumber(String pocketNumber) {
        this.pocketNumber = pocketNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
