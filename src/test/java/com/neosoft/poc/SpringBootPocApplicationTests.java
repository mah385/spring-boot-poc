package com.neosoft.poc;

import com.neosoft.poc.domain.User;
import com.neosoft.poc.repository.UserRepository;
import com.neosoft.poc.requestbody.CreateUserDTO;
import com.neosoft.poc.requestbody.UpdateUserDTO;
import com.neosoft.poc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SpringBootPocApplicationTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CreateUserDTO createUserDTO;

    @MockBean
    private UpdateUserDTO updateUserDTO;

    private final String RANDOM_USER_ID = UUID.randomUUID().toString();

    private List<User> getListOfUsers() {
        User user_1 = new User(RANDOM_USER_ID, "Md. Arif", "Hussain", "arif.hussain@gmail.com", "8017535001", "700016", LocalDate.of(1993, 4, 21), LocalDate.of(2021, 4, 30), true);
        User user_2 = new User(UUID.randomUUID().toString(), "Venga", "Nathan", "venga.nathan@gmail.com", "8017535002", "700015", LocalDate.of(1994, 7, 3), LocalDate.of(2021, 4, 30), true);
        User user_3 = new User(UUID.randomUUID().toString(), "Mozammil", "Rahman", "mozammil.rahman@gmail.com", "8017535003", "700016", LocalDate.of(1993, 4, 21), LocalDate.of(2020, 6, 15), true);
        User user_4 = new User(UUID.randomUUID().toString(), "Almas", "Rabbani", "almas.rabbani@gmail.com", "8017535004", "700014", LocalDate.of(1993, 4, 21), LocalDate.of(2021, 1, 25), true);
        return Stream.of(user_1, user_2, user_3, user_4).collect(Collectors.toList());
    }

    @Test
    public void createUserTest() {
        User savedUser = getListOfUsers().get(0);
        User newUser = createUserDTO.getUserFromUserDTO();

        when(userRepository.save(newUser)).thenReturn(savedUser);
        assertEquals(savedUser, userService.createUser(createUserDTO));
    }

    @Test
    public void updateUserByIdTest() {
        User user = getListOfUsers().get(0);
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findById(RANDOM_USER_ID)).thenReturn(Optional.of(user));
        boolean isUserUpdated = userService.updateUserById(RANDOM_USER_ID, updateUserDTO);
        verify(userRepository, times(1)).findById(RANDOM_USER_ID);
        verify(userRepository, times(1)).save(optionalUser.get());
        assertTrue(isUserUpdated);
    }

    @Test
    public void hardDeleteUserByIdTest() {
        User user = getListOfUsers().get(0);

        when(userRepository.findById(RANDOM_USER_ID)).thenReturn(Optional.of(user));
        boolean isHardDeleted = userService.deleteUserById("hard", RANDOM_USER_ID);
        verify(userRepository, times(1)).findById(RANDOM_USER_ID);
        verify(userRepository, times(1)).deleteById(RANDOM_USER_ID);
        assertTrue(isHardDeleted);
    }

    @Test
    public void softDeleteUserByIdTest() {
        User user = getListOfUsers().get(0);
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findById(RANDOM_USER_ID)).thenReturn(Optional.of(user));
        boolean isSoftDeleted = userService.deleteUserById("soft", RANDOM_USER_ID);
        verify(userRepository, times(1)).findById(RANDOM_USER_ID);
        verify(userRepository, times(1)).save(optionalUser.get());
        assertTrue(isSoftDeleted);
    }

    @Test
    public void getAllUsersWithOrderByDobDescDojDescTest() {
        List<User> listOfUsers = getListOfUsers();

        User user = listOfUsers.get(1);

        listOfUsers.sort(Comparator.comparing(User::getDob).thenComparing(User::getDoj));
        Collections.reverse(listOfUsers);

        when(userRepository.findAllByOrderByDobDescDojDesc()).thenReturn(listOfUsers);
        assertEquals(user.getFirstName(), userService.getAllUsersWithOrderByDobDescDojDesc().get(0).getFirstName());
    }

    @Test
    public void getAllByFirstNameOrLastNameOrPinCodeTest() {
        List<User> listOfUsers = getListOfUsers();
        List<User> collectedUsers = listOfUsers.stream().filter((user) -> user.getFirstName().equals("700016") || user.getLastName().equals("700016") || user.getPinCode().equals("700016")).collect(Collectors.toList());

        when(userRepository.findAllByFirstNameOrLastNameOrPinCode("700016", "700016", "700016")).thenReturn(collectedUsers);
        assertEquals(2, userService.getAllByFirstNameOrLastNameOrPinCode("700016", "700016", "700016").size());
    }

    @Test
    public void getAllUsersTest() {
        List<User> listOfUsers = getListOfUsers();
        when(userRepository.findAll()).thenReturn(listOfUsers);
        assertEquals(4, userService.getAllUsers().size());
    }

}
