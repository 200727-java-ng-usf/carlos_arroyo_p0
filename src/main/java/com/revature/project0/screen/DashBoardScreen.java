package com.revature.project0.screen;

import static com.revature.project0.AppDriver.app;

public class DashBoardScreen extends Screen {

    public DashBoardScreen() {
        super("DashBoardScreen", "/dashboard");
//        System.out.println("Instantiating " + super.getName());
    }

    @Override
    public void render() {
        System.out.println("Welcome to your Dashboard.\n");
        System.out.println("1) Balance");
        System.out.println("2) Deposit");
        System.out.println("3) Withdraw");
        System.out.println("4) Sign out");

        try {
            System.out.println("> ");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "1":
                    app.getRouter().navigate("/balance");
                    break;
                case "2":
                    app.getRouter().navigate("/deposit");
                    break;
                case "3":
                    app.getRouter().navigate("/withdraw");
                    break;
                case "4":
                    app.getRouter().navigate("/home");
                    break;
                default:
                    System.err.println("Invalid Selection");
                    app.getRouter().navigate("/dashboard");
            }

        } catch (Exception e) {
            System.out.println("Invalid Selection");
            app.getRouter().navigate("/dashboard");

        }
    }

}
