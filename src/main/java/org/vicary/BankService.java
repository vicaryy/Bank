package org.vicary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vicary.model.Account;
import org.vicary.model.User;
import org.vicary.repository.AccountSpringJpaRepository;
import org.vicary.repository.UserSpringJpaRepository;

import java.util.List;
import java.util.Scanner;
@Service                    // Oznaczanie tej klasy do wstrzykiwania zależności - dla @Autowired
public class BankService {
    UserSpringJpaRepository userRepository;
    AccountSpringJpaRepository accountRepository;
    @Autowired            // Wstrzykiwanie zależności
    BankService(
            UserSpringJpaRepository userRepository,
            AccountSpringJpaRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public void addUser(Scanner sc) {
        System.out.print("Name: ");
        final String name = sc.next();
        System.out.print("Lastname: ");
        final String lastname = sc.next();
        System.out.print("Email: ");
        final String email = sc.next();
        userRepository.save(new User(name, lastname, email));
    }

    public void showUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) System.out.println("Nothing to show");
        else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    public void findUserByEmail(Scanner sc) {
        System.out.print("User email: ");
        final String email = sc.next();
        User user = userRepository.findByEmail(email);
        System.out.println(user);
    }

    public void deleteUser(Scanner sc) {
        System.out.print("User email to delete: ");
        final String email = sc.next();
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }

    public void transferFunds(Scanner sc) {
//        System.out.print("Currency to transfer: ");
//        String currency = sc.next();
//        System.out.print("Transfer funds from user email: ");
//        final String emailFrom = sc.next();
//        System.out.print("To user email: ");
//        final String emailTo = sc.next();
//
//        Account accFrom = accountRepository.findAccountByCurrencyAndEmail(currency, userRepository.findByEmail(emailFrom));
//        Account accTo = accountRepository.findAccountByCurrencyAndEmail(currency, userRepository.findByEmail(emailTo));
//
//        System.out.printf("%s balance: %.2f \n", accFrom.getUser().getEmail(), accFrom.getAmount());
//        System.out.print("Amount to transfer: ");
//        final double amount = sc.nextDouble();
//        accountRepository.transferFunds(accFrom, accTo, amount);
    }

    public void addAccountToUser(Scanner sc) {
        System.out.print("New account to user email: ");
        final String email = sc.next();
        User user = userRepository.findByEmail(email);
        System.out.print("Currency: ");
        String currency = sc.next();
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        accountRepository.save(new Account(currency, amount, user));
//        accountRepository.addAccount(new Account(currency, amount, user));
    }

    public void showAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if(accounts.isEmpty()) System.out.println("Nothing to show.");
        for(Account account : accounts) {
            System.out.println(account);
        }
    }

    public void findAccountByCurrencyAndEmail(Scanner sc) {
        System.out.print("User email: ");
        final String email = sc.next();
        System.out.print("Currency: ");
        final String currency = sc.next();
        User user = userRepository.findByEmail(email);
        System.out.println(accountRepository.findByCurrencyAndUser(currency, user));
//        System.out.println(accountRepository.findBy(currency, user));
    }

    public void deleteAccount(Scanner sc) {
        System.out.print("User email to delete: ");
        final String email = sc.next();
        System.out.print("Currency to delete: ");
        final String currency = sc.next();
        User user = userRepository.findByEmail(email);
        Account account = accountRepository.findByCurrencyAndUser(currency, user);
        accountRepository.delete(account);
    }
}








