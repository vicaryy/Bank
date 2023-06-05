package serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vicary.service.dto.UserRequest;
import org.vicary.service.dto.UserResponse;
import org.vicary.model.Account;
import org.vicary.model.User;
import org.vicary.service.map.UserMapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class UserMapperTest {
    UserMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new UserMapper();
    }

    @Test
    public void mapUserToUserResponse_ExpectEquals_ValidUser() {
        //given
        User givenUser = new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        List<Account> givenAccounts = new ArrayList<>();
        givenAccounts.add(new Account(1L, "PLN", 1000, givenUser));
        givenAccounts.add(new Account(5L, "EUR", 3000, givenUser));
        givenAccounts.add(new Account(22L, "AUD", 5000, givenUser));
        givenUser.setAccounts(givenAccounts);

        UserResponse expectedUser = new UserResponse(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        List<Long> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(1L);
        expectedAccounts.add(5L);
        expectedAccounts.add(22L);
        expectedUser.setAccounts(expectedAccounts);

        //when
        UserResponse userResponse = mapper.map(givenUser);

        //then
        assertEquals(userResponse, expectedUser);
    }

    @Test
    public void mapUserToUserResponse_ExpectNotEquals_ValidUser() {
        //given
        User givenUser = new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        List<Account> givenAccounts = new ArrayList<>();
        givenAccounts.add(new Account(1L, "PLN", 1000, givenUser));
        givenAccounts.add(new Account(5L, "EUR", 3000, givenUser));
        givenAccounts.add(new Account(22L, "AUD", 5000, givenUser));
        givenUser.setAccounts(givenAccounts);

        UserResponse expectedUser = new UserResponse(3L, "Adam", "Cholewa", "vicary@gmail.com", null);
        List<Long> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(1L);
        expectedAccounts.add(5L);
        expectedAccounts.add(23L);
        expectedUser.setAccounts(expectedAccounts);

        //when
        UserResponse userResponse = mapper.map(givenUser);

        //then
        assertNotEquals(userResponse, expectedUser);
    }

    @Test
    public void mapUserToUserResponse_ExpectEquals_NullAccounts() {
        //given
        User givenUser = new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);

        UserResponse expectedUser = new UserResponse(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);

        //when
        UserResponse userResponse = mapper.map(givenUser);

        //then
        assertEquals(userResponse, expectedUser);
    }

    @Test
    public void mapUserRequestToUser_ExpectEquals_ValidUser() {
        //given
        UserRequest givenUser = new UserRequest("Wiktor", "Cholewa", "vicary@gmail.com");

        User expectedUser = new User("Wiktor", "Cholewa", "vicary@gmail.com");

        //when
        User user = mapper.map(givenUser);

        //then
        assertEquals(user, expectedUser);
    }

    @Test
    public void mapUserRequestToUser_ExpectNotEquals_ValidUser() {
        //given
        UserRequest givenUser = new UserRequest("Wiktor", "Cholewa", "vicary@gmail.com");

        User expectedUser = new User("Wiktorr", "Cholewa", "vicary@gmail.com");

        //when
        User user = mapper.map(givenUser);

        //then
        assertNotEquals(user, expectedUser);
    }

    @Test
    public void mapListUserToListUserResponse_ExpectEquals_ValidList() {
        //given
        List<User> givenListOfUsers = new ArrayList<>();
        User givenUser1 = new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        List<Account> givenAccounts1 = new ArrayList<>();
        givenAccounts1.add(new Account(1L, "PLN", 1000, givenUser1));
        givenAccounts1.add(new Account(5L, "EUR", 3000, givenUser1));
        givenAccounts1.add(new Account(22L, "AUD", 5000, givenUser1));
        givenUser1.setAccounts(givenAccounts1);

        User givenUser2 = new User(3L, "Adam", "Nowak", "nowaka@gmail.com", null);
        List<Account> givenAccounts2 = new ArrayList<>();
        givenAccounts2.add(new Account(2L, "PLN", 2000, givenUser2));
        givenAccounts2.add(new Account(6L, "EUR", 4000, givenUser2));
        givenAccounts2.add(new Account(23L, "AUD", 6000, givenUser2));
        givenUser2.setAccounts(givenAccounts2);

        User givenUser3 = new User(7L, "Waldemar", "Kowalski", "kowalskiw@gmail.com", null);
        List<Account> givenAccounts3 = new ArrayList<>();
        givenAccounts3.add(new Account(3L, "PLN", 3000, givenUser3));
        givenAccounts3.add(new Account(7L, "EUR", 5000, givenUser3));
        givenAccounts3.add(new Account(23L, "AUD", 7000, givenUser3));
        givenUser3.setAccounts(givenAccounts3);

        givenListOfUsers.add(givenUser1);
        givenListOfUsers.add(givenUser2);
        givenListOfUsers.add(givenUser3);


        List<UserResponse> expectedListOfUsersResponse = new ArrayList<>();
        UserResponse expectedUser1 = new UserResponse(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        List<Long> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(1L);
        expectedAccounts.add(5L);
        expectedAccounts.add(22L);
        expectedUser1.setAccounts(expectedAccounts);

        UserResponse expectedUser2 = new UserResponse(3L, "Adam", "Nowak", "nowaka@gmail.com", null);
        List<Long> expectedAccounts2 = new ArrayList<>();
        expectedAccounts2.add(2L);
        expectedAccounts2.add(6L);
        expectedAccounts2.add(23L);
        expectedUser2.setAccounts(expectedAccounts2);

        UserResponse expectedUser3 = new UserResponse(7L, "Waldemar", "Kowalski", "kowalskiw@gmail.com", null);
        List<Long> expectedAccounts3 = new ArrayList<>();
        expectedAccounts3.add(3L);
        expectedAccounts3.add(7L);
        expectedAccounts3.add(23L);
        expectedUser3.setAccounts(expectedAccounts3);

        expectedListOfUsersResponse.add(expectedUser1);
        expectedListOfUsersResponse.add(expectedUser2);
        expectedListOfUsersResponse.add(expectedUser3);

        //when
        List<UserResponse> userResponses = mapper.map(givenListOfUsers);

        //then
        assertEquals(userResponses, expectedListOfUsersResponse);
    }

    @Test
    public void mapListUserToListUserResponse_ExpectNotEquals_ValidList() {
        //given
        List<User> givenListOfUsers = new ArrayList<>();
        User givenUser1 = new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        List<Account> givenAccounts1 = new ArrayList<>();
        givenAccounts1.add(new Account(1L, "PLN", 1000, givenUser1));
        givenAccounts1.add(new Account(5L, "EUR", 3000, givenUser1));
        givenAccounts1.add(new Account(22L, "AUD", 5000, givenUser1));
        givenUser1.setAccounts(givenAccounts1);

        User givenUser2 = new User(3L, "Adam", "Nowak", "nowaka@gmail.com", null);
        List<Account> givenAccounts2 = new ArrayList<>();
        givenAccounts2.add(new Account(2L, "PLN", 2000, givenUser2));
        givenAccounts2.add(new Account(6L, "EUR", 4000, givenUser2));
        givenAccounts2.add(new Account(23L, "AUD", 6000, givenUser2));
        givenUser2.setAccounts(givenAccounts2);

        User givenUser3 = new User(7L, "Waldemar", "Kowalski", "kowalskiw@gmail.com", null);
        List<Account> givenAccounts3 = new ArrayList<>();
        givenAccounts3.add(new Account(3L, "PLN", 3000, givenUser3));
        givenAccounts3.add(new Account(7L, "EUR", 5000, givenUser3));
        givenAccounts3.add(new Account(23L, "AUD", 7000, givenUser3));
        givenUser3.setAccounts(givenAccounts3);

        givenListOfUsers.add(givenUser1);
        givenListOfUsers.add(givenUser2);
        givenListOfUsers.add(givenUser3);


        List<UserResponse> expectedListOfUsersResponse = new ArrayList<>();
        UserResponse expectedUser1 = new UserResponse(1L, "Wiktor", "Cholewa", "vicary@gmail.com", null);
        List<Long> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(1L);
        expectedAccounts.add(5L);
        expectedAccounts.add(22L);
        expectedUser1.setAccounts(expectedAccounts);

        UserResponse expectedUser2 = new UserResponse(3L, "Adam", "Nowak", "nowaka@gmail.com", null);
        List<Long> expectedAccounts2 = new ArrayList<>();
        expectedAccounts2.add(2L);
        expectedAccounts2.add(6L);
        expectedAccounts2.add(23L);
        expectedUser2.setAccounts(expectedAccounts2);

        UserResponse expectedUser3 = new UserResponse(3L, "Waldemar", "Kowalski", "kowalskiw@gmail.com", null);
        List<Long> expectedAccounts3 = new ArrayList<>();
        expectedAccounts3.add(3L);
        expectedAccounts3.add(7L);
        expectedAccounts3.add(23L);
        expectedUser3.setAccounts(expectedAccounts3);

        expectedListOfUsersResponse.add(expectedUser1);
        expectedListOfUsersResponse.add(expectedUser2);
        expectedListOfUsersResponse.add(expectedUser3);

        //when
        List<UserResponse> userResponses = mapper.map(givenListOfUsers);

        //then
        assertNotEquals(userResponses, expectedListOfUsersResponse);
    }
}


































