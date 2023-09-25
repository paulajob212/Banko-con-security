package com.bank.accountSystem.service;

import com.bank.accountSystem.dto.TransferRequest;
import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.model.Pocket;
import com.bank.accountSystem.repository.iAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {
    @Autowired
    private iAccountRepository accountRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }

    public String deposit(String accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            return "Account doesn´t exist";
        }
        double new_balance = account.getInitial_balance() + amount;
        account.setInitial_balance(new_balance);
        accountRepository.save(account);
        return "Successful deposit";
    }

    public String makeTransfer(TransferRequest transferRequest) {
        Account sourceAccountNumber = accountRepository.findByAccountNumber(transferRequest.getSourceAccountNumber());
        Account targetAccountNumber = accountRepository.findByAccountNumber(transferRequest.getTargetAccountNumber());
        if (sourceAccountNumber == null || targetAccountNumber == null) {
            return "One or both accounts do not exist";
        }
        double amount = transferRequest.getAmount();
        if (sourceAccountNumber.getInitial_balance() < amount) {
            return "Insufficient balance in the source account";
        }
        sourceAccountNumber.setInitial_balance(sourceAccountNumber.getInitial_balance() - amount);
        targetAccountNumber.setInitial_balance(targetAccountNumber.getInitial_balance() + amount);
        accountRepository.save(sourceAccountNumber);
        accountRepository.save(targetAccountNumber);
        return "transfer completed successfully";
    }

    public List<Pocket> getPocketsByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("No se encontró la cuenta");
        }
        Set<Pocket> pocketSet = account.getPockets();
        List<Pocket> pockets = new ArrayList<>(pocketSet);
        return pockets;
    }

    public void deleteAccountByAccNumber(String accountNumber){
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        accountRepository.delete(account);
    }
}
