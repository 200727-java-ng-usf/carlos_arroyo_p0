package com.revature.project0.screen;

import static com.revature.project0.AppDriver.app;

public class DashBoardScreen extends Screen {

    public DashBoardScreen() {
        super("DashBoardScreen", "/dashboard");
        System.out.println("Instantiating " + super.getName());
    }

    @Override
    public void render() {

        String userSelection;
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Dashboard");

        while (app.isSessionValid()) {
            System.out.println("\n\n-----------------------");
            System.out.println("1) View Account Balance");
            System.out.println("2) Sign Out");
        }

        try {

            System.out.println("Selection: ");
            userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "1":
                    app.getRouter().navigate("/balance");
                    break;
                case "2":
                    app.getRouter().navigate("/home");
                default:
                    System.out.println("Invalid Selection");
            }

        } catch (Exception e) {
            System.err.println("ERROR - " + e.getMessage());
            System.out.println("Shutting down application!");
            app.setAppRunning(false);
        }
    }
}
