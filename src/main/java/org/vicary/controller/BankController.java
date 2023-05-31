package org.vicary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vicary.BankService;
import org.vicary.model.Account;
import org.vicary.model.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BankController {
    private final BankService service;

    @PostMapping(path = "api/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        service.createUser(user);
    }

    @GetMapping(path = "api/user")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
        User user = service.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "api/user/")
    public List<User> showUsers() {
        List<User> users = service.showUsers();
        return users;
    }

    @GetMapping(path = "api/account")
    public Optional<Account> findAccountById(@RequestParam Long id) {
        return service.findById(id);
    }

    @PostMapping(path = "api/account")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody Account account) {
        service.createAccount(account);
    }
    @GetMapping(path = "api/account/")
    public List<Account> showAccounts() {
        return service.showAccounts();
    }
}
