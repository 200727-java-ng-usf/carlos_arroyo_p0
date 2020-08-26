package com.revature.project0.screen;

import com.revature.project0.models.Account;
import com.revature.project0.services.AccountServices;

import java.io.IOException;

import static com.revature.project0.AppDriver.app;

public class BalanceScreen extends Screen {


    public BalanceScreen() {
        super("BalanceScreen", "/balance");
    }

    @Override
    public void render() {


        Account currentAccount = app.getCurrentAccount();

        System.out.println("Your balance: $" + currentAccount.getBalance());
        app.getRouter().navigate("/dashboard");


    }
}
