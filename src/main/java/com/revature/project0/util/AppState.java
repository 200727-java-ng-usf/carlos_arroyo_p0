package com.revature.project0.util;

import com.revature.project0.models.Account;
import com.revature.project0.models.User;
import com.revature.project0.repo.AccountRepository;
import com.revature.project0.repo.UserRepository;
import com.revature.project0.screen.*;
import com.revature.project0.services.AccountServices;
import com.revature.project0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader console;
    private User currentUser;
    private Account currentAccount;
    private ScreenRouter router;
    private boolean appRunning;


    public AppState() {
//        System.out.println("[LOG] - Initializing application....");

        appRunning = true;
        console = new BufferedReader(new InputStreamReader(System.in));

        final UserRepository userRepo = new UserRepository();
        final UserService userService = new UserService(userRepo);
        final AccountRepository accountRepo = new AccountRepository();
        final AccountServices accountServices = new AccountServices(accountRepo);

        router = new ScreenRouter();
        router.addScreen(new HomeScreen())
                .addScreen(new RegisterScreen(userService))
                .addScreen(new LoginScreen(userService))
                .addScreen(new DashBoardScreen())
                .addScreen(new DepositScreen(accountServices))
                .addScreen(new WithdrawScreen(accountServices))
                .addScreen(new BalanceScreen());

//        System.out.println("[LOG - Application initialization complete.");
    }

    public BufferedReader getConsole() {
        return console;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

    public void invalidateCurrentSession() {
        currentUser = null;
    }

    public boolean isSessionValid() {
        return (this.currentUser != null);
    }
}
