package com.neosoft.poc.service;

import com.neosoft.poc.domain.User;
import com.neosoft.poc.requestbody.CreateUserDTO;
import com.neosoft.poc.requestbody.UpdateUserDTO;

import java.util.List;

public interface UserService {

    //TESTING COMPLETED
    User createUser(CreateUserDTO createUserDTO);

    //TESTING COMPLETED
    boolean updateUserById(String userId,
                           UpdateUserDTO updateUserDTO);

    //TESTING COMPLETED
    boolean deleteUserById(String deleteType,
                           String userId);

    //TESTING COMPLETED
    List<User> getAllUsersWithOrderByDobDescDojDesc();

    //TESTING COMPLETED
    List<User> getAllByFirstNameOrLastNameOrPinCode(String firstName,
                                                    String lastName,
                                                    String pinCode);

    //TESTING COMPLETED
    List<User> getAllUsers();

}
