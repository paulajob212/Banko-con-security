package com.bank.accountSystem.service;

import com.bank.accountSystem.dto.PocketRequest;
import com.bank.accountSystem.dto.TransferToPocketRequest;
import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.model.Pocket;
import com.bank.accountSystem.repository.iAccountRepository;
import com.bank.accountSystem.repository.iPocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PocketService {
    @Autowired
    private iAccountRepository accountRepository;
    @Autowired
    private iPocketRepository pocketRepository;
    public void createPocket(PocketRequest pocketRequest) {
        Account account = accountRepository.findByAccountNumber(pocketRequest.getAccountNumber());

        if (account == null) {
            throw new RuntimeException("Main account not found");
        }

        // Crear el bolsillo y asociarlo a la cuenta principal
        Pocket pocket = new Pocket();
        pocket.setPocketName(pocketRequest.getName());
        pocket.setPocketInitialBalance(pocketRequest.getPocketInitialBalance());
        pocket.setPocketNumber(pocketRequest.getPocketNumber());
        pocket.setAccount(account);

        //si el saldo inicial del bolsillo es mayor al saldo de la cuenta que no deje crear un bolsillo
        if(pocketRequest.getPocketInitialBalance() > account.getInitial_balance()){
            throw new RuntimeException("Initial pocket balance cannot be greater than the account balance");
        }

        // Actualizar el saldo de la cuenta principal
        double newBalance = account.getInitial_balance() - pocketRequest.getPocketInitialBalance();
        account.setInitial_balance(newBalance);

        // Guardar el bolsillo y la cuenta principal actualizada en la base de datos
        pocketRepository.save(pocket);
        accountRepository.save(account);
    }
    public void transferToPocket(TransferToPocketRequest transferToPocketRequest) {
        // Obtener la cuenta principal asociada al número de cuenta proporcionado
        Account account = accountRepository.findByAccountNumber(transferToPocketRequest.getAccountNumber());

        if (account == null) {
            throw new RuntimeException("Main account not found");
        }

        // Obtener el bolsillo asociado al número de bolsillo proporcionado
        Pocket pocket = pocketRepository.findByPocketNumber(transferToPocketRequest.getPocketNumber());

        if (pocket == null) {
            throw new RuntimeException("The pocket was not found");
        }

        // Verificar si el saldo de la cuenta principal es suficiente para la transferencia
        if (account.getInitial_balance() < transferToPocketRequest.getAmount()) {
            throw new RuntimeException("Insufficient balance in main account");
        }

        // Realizar la transferencia
        double newAccountBalance = account.getInitial_balance() - transferToPocketRequest.getAmount();
        double newPocketBalance = pocket.getPocketInitialBalance() + transferToPocketRequest.getAmount();

        account.setInitial_balance(newAccountBalance);
        pocket.setPocketInitialBalance(newPocketBalance);

        // Guardar los cambios en la base de datos
        accountRepository.save(account);
        pocketRepository.save(pocket);
    }
}
