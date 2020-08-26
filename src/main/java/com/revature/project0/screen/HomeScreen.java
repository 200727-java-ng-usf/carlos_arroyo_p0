package com.revature.project0.screen;

import static com.revature.project0.AppDriver.app;

public class HomeScreen extends Screen {


    public HomeScreen() {
        super("HomeScreen", "/home");
//        System.out.println("instantiating " +super.getName());
    }

    @Override
    public void render() {
        System.out.println("Welcome to the bank! \n");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit");

        try {
            System.out.println("> ");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "1":
                    app.getRouter().navigate("/login");
                    break;
                case "2":
                    app.getRouter().navigate("/register");
                    break;
                case "3":
                    app.setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid Selection");
                    app.getRouter().navigate("/home");
            }
        } catch (Exception e) {
            System.out.println("Invalid Selection");
            app.getRouter().navigate("/home");

        }
    }
}
