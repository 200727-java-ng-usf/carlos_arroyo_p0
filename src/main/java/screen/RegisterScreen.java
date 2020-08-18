package screen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegisterScreen {

    private UserService userService;

    public RegisterScreen(UserService userService) {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        this.userService = userService;
    }

    @Override
    public void render() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String firstName, lastName, password;

        try {
            System.out.println("Sign up for a new account!");
            System.out.println("First name: ");
            firstName = console.readLine();
            System.out.println("Lastname: ");
            lastName = console.readLine();
            System.out.println("Password");
            password = console.readLine();

            AppUser newUser = new AppUser(firstName, lastName, password);
            AppUser registeredUser = userService.register(newUser);
            System.out.println(registeredUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
