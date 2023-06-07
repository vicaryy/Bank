package serviceTest.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vicary.service.dto.AccountRequest;
import org.vicary.service.dto.AccountResponse;
import org.vicary.entity.Account;
import org.vicary.entity.User;
import org.vicary.service.map.AccountMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AccountMapperTest {
    private AccountMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new AccountMapper();
    }

    @Test
    public void mapAccountToAccountResponse_ExpectEquals_ValidAccount() {
        //given
        User givenUser = new User(25L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        Account givenAccount = new Account(1L, "PLN", 1000, givenUser);
        AccountResponse expectedAccount = new AccountResponse(1L, "PLN", 1000, 25L);

        //when
        AccountResponse accountResponse = mapper.map(givenAccount);

        //then
        assertEquals(accountResponse, expectedAccount);
    }

    @Test
    public void mapAccountToAccountResponse_ExpectNotEquals_ValidAccount() {
        //given
        User givenUser = new User(25L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        Account givenAccount = new Account(1L, "PLN", 1000, givenUser);
        AccountResponse expectedAccount = new AccountResponse(1L, "PLN", 1000, 15L);

        //when
        AccountResponse accountResponse = mapper.map(givenAccount);

        //then
        assertNotEquals(accountResponse, expectedAccount);
    }

    @Test
    public void mapAccountRequestToAccount_ExpectEquals_ValidAccount() {
        //given
        AccountRequest givenAccountRequest = new AccountRequest("PLN", 1000, 15L);
        User expectedAndGivenUser = new User(15L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        Account expectedAccount = new Account("PLN", 1000, expectedAndGivenUser);

        //when
        Account account = mapper.map(givenAccountRequest, expectedAndGivenUser);

        //then
        assertEquals(account, expectedAccount);
    }

    @Test
    public void mapAccountRequestToAccount_ExpectNotEquals_ValidAccount() {
        //given
        AccountRequest givenAccountRequest = new AccountRequest("EUR", 1000, 15L);
        User expectedAndGivenUser = new User(15L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        Account expectedAccount = new Account("PLN", 1000, expectedAndGivenUser);

        //when
        Account account = mapper.map(givenAccountRequest, expectedAndGivenUser);

        //then
        assertNotEquals(account, expectedAccount);
    }

    @Test
    public void mapListAccountToListAccountResponse_ExpectEquals_ValidAccounts() {
        //given
        List<Account> givenListOfAccounts = new ArrayList<>();
        User givenUser1 = new User(2L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        User givenUser2 = new User(3L, "Adam", "Nowak", "nowaka@gmail.com", null);
        Account givenAccount1 = new Account(1L, "PLN", 1000, givenUser1);
        Account givenAccount2 = new Account(3L, "EUR", 2000, givenUser1);
        Account givenAccount3 = new Account(4L, "AUD", 3000, givenUser2);
        givenListOfAccounts.add(givenAccount1);
        givenListOfAccounts.add(givenAccount2);
        givenListOfAccounts.add(givenAccount3);

        List<AccountResponse> expectedListOfAccountsResponse = new ArrayList<>();
        AccountResponse expectedAccount1 = new AccountResponse(1L, "PLN", 1000, 2L);
        AccountResponse expectedAccount2 = new AccountResponse(3L, "EUR", 2000, 2L);
        AccountResponse expectedAccount3 = new AccountResponse(4L, "AUD", 3000, 3L);
        expectedListOfAccountsResponse.add(expectedAccount1);
        expectedListOfAccountsResponse.add(expectedAccount2);
        expectedListOfAccountsResponse.add(expectedAccount3);

        //when
        List<AccountResponse> accountResponses = mapper.map(givenListOfAccounts);

        //then
        assertEquals(accountResponses, expectedListOfAccountsResponse);
    }

    @Test
    public void mapListAccountToListAccountResponse_ExpectNotEquals_ValidAccounts() {
        //given
        List<Account> givenListOfAccounts = new ArrayList<>();
        User givenUser1 = new User(2L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        User givenUser2 = new User(3L, "Adam", "Nowak", "nowaka@gmail.com", null);
        Account givenAccount1 = new Account(1L, "PLN", 1000, givenUser1);
        Account givenAccount2 = new Account(3L, "EUR", 2000, givenUser1);
        Account givenAccount3 = new Account(4L, "AUD", 3000, givenUser2);
        givenListOfAccounts.add(givenAccount1);
        givenListOfAccounts.add(givenAccount2);
        givenListOfAccounts.add(givenAccount3);

        List<AccountResponse> expectedListOfAccountsResponse = new ArrayList<>();
        AccountResponse expectedAccount1 = new AccountResponse(1L, "WEIRDVALUE", 1000, 2L);
        AccountResponse expectedAccount2 = new AccountResponse(3L, "EUR", 2000, 2L);
        AccountResponse expectedAccount3 = new AccountResponse(4L, "AUD", 3000, 3L);
        expectedListOfAccountsResponse.add(expectedAccount1);
        expectedListOfAccountsResponse.add(expectedAccount2);
        expectedListOfAccountsResponse.add(expectedAccount3);

        //when
        List<AccountResponse> accountResponses = mapper.map(givenListOfAccounts);

        //then
        assertNotEquals(accountResponses, expectedListOfAccountsResponse);
    }
}
