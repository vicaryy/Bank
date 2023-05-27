package org.vicary;

import java.util.Scanner;

public class Menu {
    void start(){
        Scanner sc = new Scanner(System.in);
        boolean menu = true;
        while(menu) {
            System.out.println("BANK");
            System.out.println("1. Add user");
            System.out.println("2. Find user by email");
            System.out.println("3. Delete user");
            System.out.println("4. Transfer funds");
            System.out.println("5. Add account to user");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 0 -> menu = false;
                case 1 ->
            }
        }
    }
}
