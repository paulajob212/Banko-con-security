package com.bank.accountSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {
    @Id
    private Long id;
    @Column(unique = true)
    private String accountNumber;
    @Column
    private String owner;
    @Column
    private double initial_balance;
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Pocket> pockets = new HashSet<>();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public double getInitial_balance() {
        return initial_balance;
    }
    public void setInitial_balance(double initial_balance) {
        this.initial_balance = initial_balance;
    }
    public Set<Pocket> getPockets() {
        return pockets;
    }
    public void setPockets(Set<Pocket> pockets) {
        this.pockets = pockets;
        for(Pocket pocket : pockets) {
            pocket.setAccount(this);
        }
    }
}
