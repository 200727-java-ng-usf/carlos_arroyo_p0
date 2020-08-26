package com.revature.project0.screen;

import com.revature.project0.models.User;
import com.revature.project0.services.UserService;
import com.sun.jdi.request.InvalidRequestStateException;

import javax.security.sasl.AuthenticationException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.project0.AppDriver.app;

public class RegisterScreen extends Screen {

    private UserService userService;

    public RegisterScreen(UserService userService) {
        super("RegisterScreen", "/register");
//        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        this.userService = userService;
    }

    @Override
    public void render() {

        String firstName, lastName, username, email, password;

        try {
            System.out.println("Sign up for a new account!");
            System.out.println("First name: ");
            firstName = app.getConsole().readLine();
            System.out.println("Lastname: ");
            lastName = app.getConsole().readLine();
            System.out.println("Username: ");
            username = app.getConsole().readLine();
            System.out.println("Email: ");
            email = app.getConsole().readLine();
            System.out.println("Password");
            password = app.getConsole().readLine();

            User newUser = new User(firstName, lastName, username, email, password);
            userService.register(newUser);

            if(app.isSessionValid()) {
                app.getRouter().navigate("/dashboard");
            }
        } catch (AuthenticationException ae) {
            System.err.println("User already exists.");
        } catch (InvalidRequestStateException e) {
            System.err.println("Registration unsuccessful, invalid values provided.");
            app.getRouter().navigate("/register");

        }catch (Exception e) {
            System.err.println("Registration unsuccessful, invalid values provided");
            app.getRouter().navigate("/register");
//            System.out.println("[LOG] - Shutting down application");
            app.setAppRunning(false);
        }
    }
}
