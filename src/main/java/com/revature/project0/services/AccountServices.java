package com.revature.project0.services;

import com.revature.project0.repo.AccountRepository;

import java.math.BigDecimal;

import static com.revature.project0.AppDriver.app;

public class AccountServices {

    public AccountRepository accountRepo;

    public AccountServices(AccountRepository repo) {
        System.out.println("Instantiating " + this.getClass().getName());
        accountRepo = repo;
    }

    public void deposit() {


    }
}
