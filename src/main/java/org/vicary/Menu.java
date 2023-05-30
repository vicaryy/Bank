package org.vicary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Menu implements CommandLineRunner {
    private BankService bankService;

    @Autowired
    Menu(BankService bankService) {
        this.bankService = bankService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Menu.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner sc = new Scanner(System.in);
        boolean menu = true;
        while (menu) {
            System.out.println("BANK");
            System.out.println("1. Add user");
            System.out.println("2. Show users");
            System.out.println("3. Find user by email");
            System.out.println("4. Delete user");
            System.out.println("5. Transfer funds");
            System.out.println("6. Add account to user");
            System.out.println("7. Show accounts");
            System.out.println("8. Find account by currency and email");
            System.out.println("9. Delete account");
            System.out.println("0. Exit");

            System.out.print("Choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 0 -> menu = false;
                case 1 -> bankService.addUser(sc);
                case 2 -> bankService.showUsers();
                case 3 -> bankService.findUserByEmail(sc);
                case 4 -> bankService.deleteUser(sc);
                case 5 -> bankService.transferFunds(sc);
                case 6 -> bankService.addAccountToUser(sc);
                case 7 -> bankService.showAccounts();
                case 8 -> bankService.findAccountByCurrencyAndEmail(sc);
                case 9 -> bankService.deleteAccount(sc);
            }
        }
    }

    void addUser(Scanner sc) {

    }

    void showUsers() {

    }

    void findUserByEmail(Scanner sc) {

    }

    void deleteUser(Scanner sc) {

    }

    void transferFunds(Scanner sc) {

    }
}
