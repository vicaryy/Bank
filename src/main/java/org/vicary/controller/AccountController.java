package org.vicary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vicary.dto.AccountRequest;
import org.vicary.dto.AccountResponse;
import org.vicary.model.Account;
import org.vicary.service.AccountService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping(path = "api/account")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody AccountRequest accountRequest) {
        service.createAccount(accountRequest);
    }

    @DeleteMapping(path = "api/account")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@RequestParam Long id) {
        service.deleteAccount(id);
    }

    @GetMapping(path = "api/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse findAccountById(@RequestParam Long id) {
        return service.findResponseById(id);
    }

    @GetMapping(path = "api/account/")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountResponse> showAccounts() {
        return service.showAccounts();
    }

    @PutMapping(path = "api/transfer")
    @ResponseStatus(HttpStatus.OK)
    public void transferFunds(Long accountIdFrom, Long accountIdTo, double amount) {
        service.transferFunds(accountIdFrom, accountIdTo, amount);
    }
}
