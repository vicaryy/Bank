package org.vicary.service;

import org.springframework.stereotype.Component;
import org.vicary.dto.AccountRequest;
import org.vicary.dto.AccountResponse;
import org.vicary.model.Account;
import org.vicary.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

    public AccountResponse map(Account account) {
        return AccountResponse
                .builder()
                .account_id(account.getAccount_id())
                .currency(account.getCurrency())
                .amount(account.getAmount())
                .user_id(account.getUser().getId())
                .build();
    }

    public List<AccountResponse> map(List<Account> accounts) {
        List<AccountResponse> accountResponses = new ArrayList<>();
        for(Account account : accounts)
            accountResponses.add(map(account));
        return accountResponses;
    }

    public Account map(AccountRequest accountRequest, User user) {
        return Account
                .builder()
                .currency(accountRequest.getCurrency())
                .amount(accountRequest.getAmount())
                .user(user)
                .build();
    }
}
