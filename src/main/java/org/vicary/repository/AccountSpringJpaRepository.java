package org.vicary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vicary.model.User;
import org.vicary.model.Account;

public interface AccountSpringJpaRepository extends JpaRepository<Account, Long> {
    Account findByCurrencyAndUser(String currency, User user);
}
