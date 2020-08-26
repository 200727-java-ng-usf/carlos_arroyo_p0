package com.revature.project0.screen;

import com.revature.project0.services.AccountServices;

public class RegisterAccountScreen extends Screen{

    private AccountServices accountServices;

    public RegisterAccountScreen(AccountServices accountServices) {
        super("RegisterAccountScreen", "accountServices");
        this.accountServices = accountServices;
    }

    @Override
    public void render() {


    }
}
