package org.vicary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vicary.entity.User;
import org.vicary.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByCurrencyAndUser(String currency, User user);
    //Account findById(Long id);
}
