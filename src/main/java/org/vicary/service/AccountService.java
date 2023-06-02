package org.vicary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vicary.dto.AccountRequest;
import org.vicary.dto.AccountResponse;
import org.vicary.model.Account;
import org.vicary.model.User;
import org.vicary.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;
    private final AccountMapper mapper;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          UserService userService,
                          AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.mapper = mapper;
    }



    public Account findById(Long id) {
        return accountRepository.findById(id).get();
    }

    public AccountResponse findResponseById(Long id) {
        Account account = findById(id);
        return mapper.map(account);
    }

    public void createAccount(AccountRequest accountRequest) {
        User user = userService.findUserById(accountRequest.getUser_id());
        Account account = mapper.map(accountRequest, user);
        accountRepository.save(account);
    }

    public List<AccountResponse> showAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return mapper.map(accounts);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public void transferFunds(Long accountIdFrom, Long accountIdTo, double amount) {
        if(amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        if(accountIdFrom == accountIdTo) throw new IllegalArgumentException("Accounts has to be different.");
        Account accountFrom = findById(accountIdFrom);
        Account accountTo = findById(accountIdTo);

        if(accountFrom.getAmount() - amount < 0) throw new IllegalArgumentException("Not enough funds.");

        accountFrom.setAmount(accountFrom.getAmount() - amount);
        accountTo.setAmount(accountTo.getAmount() + amount);

        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
    }
}
























