package com.revature.project0.util;

import com.revature.project0.models.User;
import com.revature.project0.repo.UserRepository;
import com.revature.project0.screen.*;
import com.revature.project0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader console;
    private User currentUser;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        System.out.println("[LOG] - Initializing application....");

        appRunning = true;
        console = new BufferedReader(new InputStreamReader(System.in));

        final UserRepository userRepo = new UserRepository();
        final UserService userService = new UserService(userRepo);

        router = new ScreenRouter();
        router.addScreen(new HomeScreen())
                .addScreen(new RegisterScreen(userService))
                .addScreen(new LoginScreen(userService))
                .addScreen(new DashBoardScreen())
                .addScreen(new AccountScreen())
                .addScreen(new WithdrawScreen());



        System.out.println("[LOG - Application initialization complete.");
    }

    public  BufferedReader getConsole() {
        return console;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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
