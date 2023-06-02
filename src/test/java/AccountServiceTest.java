import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vicary.dto.AccountRequest;
import org.vicary.model.Account;
import org.vicary.model.User;
import org.vicary.repository.AccountRepository;
import org.vicary.service.AccountMapper;
import org.vicary.service.AccountService;
import org.vicary.service.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {
    AccountService service;
    AccountRepository repository;
    UserService userService;
    AccountMapper mapper;
    @BeforeEach
    public void setup() {
        repository = mock(AccountRepository.class);
        userService = mock(UserService.class);
        mapper = mock(AccountMapper.class);
        service = new AccountService(repository, userService, mapper);
    }

    @Test
    public void creatingAccount_ForValidAccount() {
        //given
        Long user_id = 1L;
        AccountRequest accountRequest = new AccountRequest("PLN", 1000, user_id);
        User user = new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        Account account = new Account(1L, "PLN", 1000, user);
        //when
        when(userService.findUserById(user_id)).thenReturn(user);
        when(mapper.map(accountRequest, user)).thenReturn(account);
        //then
        service.createAccount(accountRequest);

        //verify
        verify(userService).findUserById(user_id);
        verify(repository).save(new Account(1L, "PLN", 1000, user));
    }

    @Test
    public void findById_ReturnsAccount_ForValidId() {
        //given
        Long account_id = 1L;
        Account expectedAccount = new Account(account_id, "PLN", 1000, null);
        when(repository.findById(account_id)).thenReturn(Optional.of(expectedAccount));

        //when
        Account actualAccount = service.findById(account_id);

        //then
        assertEquals(actualAccount, expectedAccount);
    }

    @Test
    public void findById_ReturnsNoSuchElementException_ForInvalidId() {
        //given
        Long account_id = 1L;
        when(repository.findById(account_id)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(NoSuchElementException.class, () -> service.findById(account_id));
    }

    @Test
    public void transferFunds_ReturnsIllegalArgumentException_AmountBelowZero() {
        //given
        Long accountFrom = 1L;
        Long accountTo = 2L;
        double amount = -100;
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> service.transferFunds(accountFrom, accountTo, amount));
    }

    @Test
    public void transferFunds_ReturnsIllegalArgumentException_AccountIdTheSame() {
        //given
        Long accountIdFrom = 2L;
        Long accountIdTo = 2L;
        double amount = 100;
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> service.transferFunds(accountIdFrom, accountIdTo, amount));
    }

    @Test
    public void transferFunds_ReturnsIllegalArgumentException_NotEnoughFunds() {
        //given
        Long accountIdFrom = 1L;
        Long accountIdTo = 2L;
        double amount = 5000;
        Optional<Account> accountFrom = Optional.of(new Account("PLN", 1000, null));
        Optional<Account> accountTo = Optional.of(new Account("PLN", 2000, null));
        when(repository.findById(accountIdFrom)).thenReturn(accountFrom);
        when(repository.findById(accountIdTo)).thenReturn(accountTo);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> service.transferFunds(accountIdFrom, accountIdTo, amount));

    }
}

































