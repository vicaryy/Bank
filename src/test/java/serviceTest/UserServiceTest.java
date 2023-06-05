package serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vicary.service.dto.UserRequest;
import org.vicary.model.User;
import org.vicary.repository.UserRepository;
import org.vicary.service.map.UserMapper;
import org.vicary.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService service;
    private UserRepository repository;
    private UserMapper mapper;

    @BeforeEach
    public void setup() {
        repository = mock(UserRepository.class);
        mapper = mock(UserMapper.class);
        service = new UserService(repository, mapper);
    }

    @Test
    public void findUserByEmail_ExpectEqualsUser_ValidEmail() {
        //given
        User expectedUser = new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com");
        String givenEmail = "vicary@gmail.com";
        when(repository.findByEmail(givenEmail)).thenReturn(expectedUser);
        //when
        //then
        assertEquals(service.findUserByEmail(givenEmail), expectedUser);
    }

    @Test
    public void findUserByEmail_ExpectNotEqualsUser_ValidEmail() {
        //given
        User expectedUser = new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com");
        String givenEmail = "vicary@gmail.com";
        when(repository.findByEmail(givenEmail)).thenReturn(new User(2L, "Wiktor", "Cholewa", "vicar@gmail.com"));
        //when
        //then
        assertNotEquals(service.findUserByEmail(givenEmail), expectedUser);
    }

    @Test
    public void findUserByEmail_ExpectIllegalArgumentException_NullEmail() {
        //given
        String givenEmail = null;
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> service.findUserByEmail(givenEmail));
    }

    @Test
    public void findUserById_ExpectEqualsUser_ValidId() {
        //given
        Optional<User> expectedUser = Optional.of(new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com"));
        Long id = 1L;
        when(repository.findById(id)).thenReturn(expectedUser);
        //when
        //then
        assertEquals(service.findUserById(id), expectedUser.get());
    }

    @Test
    public void findUserById_ExpectNotEqualsUser_ValidId() {
        //given
        Optional<User> expectedUser = Optional.of(new User(1L, "Wiktor", "Cholewa", "vicary@gmail.com"));
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(new User(2L, "Wiktor", "Cholewa", "vicar@gmail.com")));
        //when
        //then
        assertNotEquals(service.findUserById(id), expectedUser.get());
    }

    @Test
    public void findUserById_ExpectIllegalArgumentException_NullId() {
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> service.findUserByEmail(null));
    }

    @Test
    public void createUser_ExpectUser_ValidUser() {
        //given
        UserRequest givenRequestUser = new UserRequest("Wiktor", "Cholewa", "vicary@gmail.com");
        User expectedUser = new User(70L, "Wiktor", "Cholewa", "vicary@gmail.com");
        when(mapper.map(givenRequestUser)).thenReturn(expectedUser);

        //when
        service.createUser(givenRequestUser);

        //verify
        verify(mapper).map(eq(givenRequestUser));
        verify(repository).save(eq(expectedUser));
    }
}

































