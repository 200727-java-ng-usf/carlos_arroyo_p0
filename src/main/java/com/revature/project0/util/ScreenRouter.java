package com.revature.project0.util;

import com.revature.project0.screen.Screen;

import java.util.HashSet;
import java.util.Set;

public class ScreenRouter {

    private Set<Screen> screens = new HashSet<>();

    // add screens
    public ScreenRouter addScreen(Screen screen) {
//        System.out.println("[log] - Loading " + screen.getName() + " into the router!");
        screens.add(screen);
        return this;
    }

    // navigate through the screens
    public void navigate(String route) {
        screens.stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No screen found with that route."))
                .render();
    }
}
