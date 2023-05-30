package org.vicary.repository;

import org.vicary.model.Account;
import org.vicary.model.User;

import java.util.List;

public interface AccountRepository {
    void addAccount(Account account);
    List<Account> showAccounts();
    Account findAccountByCurrencyAndEmail(String currency, User user);
    void deleteAccount(Account account);
    void transferFunds(Account accFrom, Account accTo, double amount);
}
