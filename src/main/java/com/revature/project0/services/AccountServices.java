package com.revature.project0.services;

import com.revature.project0.exceptions.AuthenticationException;
import com.revature.project0.models.Account;

import com.revature.project0.models.User;
import com.revature.project0.repo.AccountRepository;


import java.util.Optional;

import static com.revature.project0.AppDriver.app;

public class AccountServices {

    public AccountRepository accountRepo;

    public AccountServices() {

    }

    public AccountServices(AccountRepository repo) {
//        System.out.println("Instantiating " + this.getClass().getName());
        accountRepo = repo;
    }

    public void register(Account newAccount) {
        if (newAccount.equals(0)) {
            throw new RuntimeException("Invalid credentials given for registration.");
        }
        //
        Optional<Account> _existingAccount = accountRepo.findAccountById(newAccount.getId());

        if (_existingAccount.isPresent()) {
            throw new AuthenticationException("Provided account id is already in use!");
        }

        accountRepo.save(newAccount);
        app.setCurrentAccount(newAccount);
    }

    public double moneyHandler(Double amount) {

        Account currentAccount = app.getCurrentAccount();
        double currentBalance = currentAccount.getBalance();
        if (amount <= 0) {
            throw new RuntimeException("Amounts less than 0 are not allowed.");
//            System.out.println("Amount less than 0");
        } else if (amount > 0){
            currentBalance = amount + currentBalance;
        } else if (amount > currentBalance) {
            throw new RuntimeException("Not enough funds!");
        } else {
            currentBalance = currentBalance - amount;
        }

        accountRepo.update(currentBalance);
        currentAccount.setBalance(currentBalance);
        return currentBalance;
    }

}
