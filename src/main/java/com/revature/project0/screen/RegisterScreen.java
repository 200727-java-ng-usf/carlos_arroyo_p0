package com.revature.project0.screen;

import com.revature.project0.models.User;
import com.revature.project0.services.UserService;
import com.sun.jdi.request.InvalidRequestStateException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.project0.AppDriver.app;

public class RegisterScreen extends Screen {

    private UserService userService;

    public RegisterScreen(UserService userService) {
        super("RegisterScreen", "/register");
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        this.userService = userService;
    }

    @Override
    public void render() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String firstName, lastName, username, email, password;

        try {
            System.out.println("Sign up for a new account!");
            System.out.println("First name: ");
            firstName = console.readLine();
            System.out.println("Lastname: ");
            lastName = console.readLine();
            System.out.println("Username: ");
            username = console.readLine();
            System.out.println("Email: ");
            email = console.readLine();
            System.out.println("Password");
            password = console.readLine();

            User newUser = new User(firstName, lastName, username, email, password);
            userService.register(newUser);

            if(app.isSessionValid()) {
                app.getRouter().navigate("/dashboard");
            }
        } catch (InvalidRequestStateException e) {
            System.err.println("Registration unsuccessful, invalid values provided.");

        }catch (Exception e) {
            System.err.println("Registration unsuccessful, invalid values provided");
            System.out.println("[LOG] - Shutting down application");
            app.setAppRunning(false);
        }
    }
}
