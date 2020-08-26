package com.revature.project0.screen;

import com.revature.project0.models.Account;
import com.revature.project0.services.AccountServices;

import java.io.IOException;

import static com.revature.project0.AppDriver.app;

public class DepositScreen extends Screen {

    private AccountServices accountServices;

    public DepositScreen(AccountServices accountServices) {
        super("DepositScreen", "/deposit");
        this.accountServices = accountServices;
    }

    @Override
    public void render() {

        try {


            Account currentAccount = app.getCurrentAccount();

            System.out.println("How much do you want to deposit: ");
            // double.parseDouble useful to cast readline to double
            double deposit = Double.parseDouble(app.getConsole().readLine());

            accountServices.moneyHandler(deposit);
            System.out.println("Your balance: $" + currentAccount.getBalance());
            app.getRouter().navigate("/Dashboard");


        } catch (IOException ioe) {
            System.out.println("Invalid number");;
            app.getRouter().navigate("/deposit");
        }

    }
}
