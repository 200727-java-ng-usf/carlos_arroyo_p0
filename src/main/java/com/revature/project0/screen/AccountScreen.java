package com.revature.project0.screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class AccountScreen extends Screen{
    public AccountScreen() {
        super("BalanceScreen", "/balance");

    }

    @Override
    public void render() {
        Scanner console = new Scanner(System.in);
        int userChoice;
        boolean quit = false;
        double balance = 0f;

        do {
            System.out.println("1) Deposit money");
            System.out.println("2) Withdraw money");
            System.out.println("3) Check Balance");
            System.out.println("0) Quit");
            userChoice = console.nextInt();

            switch (userChoice) {
                case 1:
                    double amount;
                    System.out.print("Amount to deposit: ");
                    amount = console.nextDouble();
                    if (amount <= 0) {
                        System.out.println("Can't deposit a negative amount.");
                    } else {
                        balance += amount;
                        System.out.println("$" + amount + "has been deposited");
                    }
                    break;
                case 2:
                    System.out.print("Amount to withdraw: ");
                    amount = console.nextDouble();
                    if (amount <= 0 || amount > balance) {
                        System.out.println("Not enough funds.");
                    } else {
                        balance -= amount;
                        System.out.println("$" + amount + "has been withdrawn.");
                    }
                    break;
                case 3:
                    System.out.println("Your balance: $" + balance);
                    break;
                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        } while (!quit);
        System.out.println("Bye!");
    }
}
