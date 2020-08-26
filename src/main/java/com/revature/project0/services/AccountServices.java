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
//TODO implement deposit withdraw and balance methods


}
