package com.revature.project0;

import com.revature.project0.util.AppState;

public class AppDriver {

    public static AppState app = new AppState();

    public static void main(String[] args) {

        while(app.isAppRunning()) {
            System.out.println(app.isAppRunning());
            System.out.println("beginning of while loop");
            app.getRouter().navigate("/home");
            System.out.println("end of while loop");
            System.out.println(app.isAppRunning());
        }
    }
}
