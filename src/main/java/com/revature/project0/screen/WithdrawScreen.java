package com.revature.project0.screen;

import com.revature.project0.models.Account;
import com.revature.project0.services.AccountServices;

import java.io.IOException;

import static com.revature.project0.AppDriver.app;

public class WithdrawScreen extends Screen{

    private AccountServices accountServices;
    public WithdrawScreen(AccountServices accountServices) {
        super("WithdrawScreen", "/withdraw");
        this.accountServices = accountServices;
    }

    @Override
    public void render() {

        try {

            Account currentAccount = app.getCurrentAccount();

            // double.parseDouble useful to cast readline to double
            double withdraw = Double.parseDouble(app.getConsole().readLine());

            accountServices.moneyHandler(withdraw);
            System.out.println("Your balance: $" + currentAccount.getBalance());

        } catch (IOException ioe) {
            System.out.println("Invalid number");;
            app.getRouter().navigate("/withdraw");
        }
    }
}
