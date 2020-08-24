package com.revature.project0.screen;

import com.revature.project0.services.UserService;
import com.sun.jdi.request.InvalidRequestStateException;

import javax.security.sasl.AuthenticationException;

import static com.revature.project0.AppDriver.app;

public class LoginScreen extends Screen{


    private UserService userService;

    public LoginScreen(UserService userService) {
        super("LoginScreen", "/login");
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

        //// loosely coupled, because this class is not responsible for instantiation of a UserService
        this.userService = userService;
    }

    @Override
    public void render() {
        String username;
        String password;

        try {
            System.out.println("Please provide your login credentials.");
            System.out.println("Username: ");
            username = app.getConsole().readLine();
            System.out.println("Password: ");
            password = app.getConsole().readLine();

            userService.authenticate(username, password);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/dashboard");
            }
        } catch (InvalidRequestStateException | AuthenticationException e) {
            System.err.println("Invalid login credentials provided!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[ERRO} = An unexpected exception occurred: " + e.getMessage());
            System.out.println("[LOG} - Shutting down application");
            app.setAppRunning(false);
        }
    }
}
