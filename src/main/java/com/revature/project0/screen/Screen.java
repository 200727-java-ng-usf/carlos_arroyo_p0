package com.revature.project0.screen;

/*
    Displays menus
 */

public abstract class Screen {

    private String name;
    private String route;

    protected Screen(String name, String route) {
        this.name = name;
        this. route = route;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }



    public abstract void render();
}
