package org.vicary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vicary.service.dto.AccountRequest;
import org.vicary.service.dto.AccountResponse;
import org.vicary.entity.Account;
import org.vicary.entity.User;
import org.vicary.repository.AccountRepository;
import org.vicary.service.dto.CurrencyRateResponse;
import org.vicary.service.map.AccountMapper;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final UserService userService;
    private final ExchangeService exchangeService;
    private final AccountMapper mapper;

    public Account findById(Long id) {
        return repository.findById(id).get();
    }

    public AccountResponse findResponseById(Long id) {
        Account account = findById(id);
        return mapper.map(account);
    }

    public void createAccount(AccountRequest accountRequest) {
        if(accountRequest.getAmount() < 0) throw new IllegalArgumentException("Amount has to be more than 0.");
        if(accountRequest.getCurrency() == null) throw new IllegalArgumentException("Currency cannot be null.");
        if(accountRequest.getCurrency().length() != 3) throw new IllegalArgumentException("Wrong currency.");
        if(accountRequest.getUser_id() == null) throw new IllegalArgumentException("User_id cannot be null.");

        User user = userService.findUserById(accountRequest.getUser_id());
        if(user == null) throw new NoSuchElementException("User does not exist.");
        Account account = mapper.map(accountRequest, user);
        repository.save(account);
    }

    public List<AccountResponse> showAccounts() {
        List<Account> accounts = repository.findAll();
        return mapper.map(accounts);
    }

    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }

    public void transferFunds(long accountIdFrom, long accountIdTo, double amount) {
        if(amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        if(accountIdFrom == accountIdTo) throw new IllegalArgumentException("Accounts has to be different.");
        Account accountFrom = findById(accountIdFrom);
        Account accountTo = findById(accountIdTo);
        if(accountFrom.getAmount() - amount < 0) throw new IllegalArgumentException("Not enough funds.");

        CurrencyRateResponse currencyRateResponse = exchangeService.getCurrencyRate(accountFrom.getCurrency());
        double rate = currencyRateResponse.getConversionRates().get(accountTo.getCurrency());

        // USD 1.0 -> PLN 4.1898
        // AMOUNT - $1000
        // ACCOUNT1 = $2000 - $1000
        // ACCOUNT2 = PLN5000 + $1000 * 4.1898


        accountFrom.setAmount(accountFrom.getAmount() - amount);
        accountTo.setAmount(accountTo.getAmount() + (amount * rate));

        repository.save(accountFrom);
        repository.save(accountTo);
    }
}
























