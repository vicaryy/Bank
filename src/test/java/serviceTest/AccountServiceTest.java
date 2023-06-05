package serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vicary.service.ExchangeService;
import org.vicary.service.dto.AccountRequest;
import org.vicary.model.Account;
import org.vicary.model.User;
import org.vicary.repository.AccountRepository;
import org.vicary.service.dto.CurrencyRateResponse;
import org.vicary.service.map.AccountMapper;
import org.vicary.service.AccountService;
import org.vicary.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {
    AccountService service;
    AccountRepository repository;
    UserService userService;
    ExchangeService exchangeService;
    AccountMapper mapper;
    @BeforeEach
    public void setup() {
        repository = mock(AccountRepository.class);
        userService = mock(UserService.class);
        mapper = mock(AccountMapper.class);
        exchangeService = mock(ExchangeService.class);
        service = new AccountService(repository, userService, exchangeService, mapper);
    }

    @Test
    public void createAccount_ValidAccount() {
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
    public void createAccount_ReturnsIllegalArgumentException_AmountBelowZero() {
        //given
        Long userId = 1L;
        AccountRequest accountRequest = new AccountRequest("PLN", -100, userId);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> service.createAccount(accountRequest));
    }

    @Test
    public void createAccount_ReturnsIllegalArgumentException_NullCurrency() {
        //given
        Long userId = 1L;
        AccountRequest accountRequest = new AccountRequest(null, 1000, userId);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> service.createAccount(accountRequest));
    }

    @Test
    public void createAccount_ReturnsIllegalArgumentException_WrongCurrency() {
        //given
        Long userId = 1L;
        AccountRequest accountRequest = new AccountRequest("NOT3CHARS", 1000, userId);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> service.createAccount(accountRequest));
    }

    @Test
    public void createAccount_ReturnsNoSuchElementException_WrongUser() {
        //given
        Long userId = 1L;
        AccountRequest accountRequest = new AccountRequest("PLN", 1000, userId);
        when(userService.findUserById(userId)).thenReturn(null);
        //when
        //then
        assertThrows(NoSuchElementException.class, () -> service.createAccount(accountRequest));
    }

    @Test
    public void createAccount_IllegalArgumentException_NullUser() {
        //given
        AccountRequest accountRequest = new AccountRequest("PLN", 1000, null);
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> service.createAccount(accountRequest));
    }

    @Test
    public void findById_ReturnsAccount_ValidId() {
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
    public void findById_ReturnsNoSuchElementException_InvalidId() {
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

    @Test
    public void transferFunds_ExpectVerifyTransaction_ValidAccounts() {
        //given
        long accountIdFrom = 1L;
        long accountIdTo = 2L;
        double amount = 1000;
        Optional<Account> accountFrom = Optional.of(new Account(1L, "USD", 3000, null));
        Optional<Account> accountTo = Optional.of(new Account(2L, "PLN", 5000, null));
        Map<String, Double> conversionRates = Map.of("EUR", 0.93, "PLN", 4.18);
        CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse(accountFrom.get().getCurrency(), conversionRates);
        when(repository.findById(accountIdFrom)).thenReturn(accountFrom);
        when(repository.findById(accountIdTo)).thenReturn(accountTo);
        when(exchangeService.getCurrencyRate(accountFrom.get().getCurrency())).thenReturn(currencyRateResponse);

        Account expectedAccountFromSave = new Account(1L, "USD", 2000, null);
        Account expectedAccountToSave = new Account(2L, "PLN", 9180, null);

        //when
        service.transferFunds(accountIdFrom, accountIdTo, amount);

        //verify
        verify(repository).save(eq(expectedAccountFromSave));
        verify(repository).save(eq(expectedAccountToSave));
    }
}

































