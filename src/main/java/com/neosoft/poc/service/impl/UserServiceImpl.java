package com.neosoft.poc.service.impl;

import com.neosoft.poc.domain.User;
import com.neosoft.poc.repository.UserRepository;
import com.neosoft.poc.requestbody.CreateUserDTO;
import com.neosoft.poc.requestbody.UpdateUserDTO;
import com.neosoft.poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());  //java.util.logging.Logger

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserDTO createUserDTO) {
        User newUser = createUserDTO.getUserFromUserDTO();
        User savedUser = userRepository.save(newUser);
        LOG.info("savedUser: " + savedUser);
        return savedUser;
    }

    @Override
    public boolean updateUserById(String userId,
                                  UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            updatedUser.setFirstName(updateUserDTO.getFirstName() != null ? updateUserDTO.getFirstName() : updatedUser.getFirstName());
            updatedUser.setLastName(updateUserDTO.getLastName() != null ? updateUserDTO.getLastName() : updatedUser.getLastName());
            updatedUser.setEmail(updateUserDTO.getEmail() != null ? updateUserDTO.getEmail() : updatedUser.getEmail());
            updatedUser.setPhoneNumber(updateUserDTO.getPhoneNumber() != null ? updateUserDTO.getPhoneNumber() : updatedUser.getPhoneNumber());
            updatedUser.setPinCode(updateUserDTO.getPinCode() != null ? updateUserDTO.getPinCode() : updatedUser.getPinCode());
            updatedUser.setDob(updateUserDTO.getDob() != null ? updateUserDTO.getDob() : updatedUser.getDob());
            updatedUser.setDoj(updateUserDTO.getDoj() != null ? updateUserDTO.getDoj() : updatedUser.getDoj());
            userRepository.save(updatedUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUserById(String deleteType,
                                  String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            if ("soft".equalsIgnoreCase(deleteType)) {
                User user = optionalUser.get();
                user.setActive(false);
                userRepository.save(user);
            } else {
                userRepository.deleteById(userId);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> getAllUsersWithOrderByDobDescDojDesc() {
        List<User> allByOrderByDobDescDojDesc = userRepository.findAllByOrderByDobDescDojDesc();
        for (User user : allByOrderByDobDescDojDesc) {
            LOG.info("allByOrderByDobDescDojDesc: " + user);
        }
        return allByOrderByDobDescDojDesc;
    }

    @Override
    public List<User> getAllByFirstNameOrLastNameOrPinCode(String firstName,
                                                           String lastName,
                                                           String pinCode) {
        List<User> allByFirstNameOrLastNameOrPinCode = userRepository.findAllByFirstNameOrLastNameOrPinCode(firstName, lastName, pinCode);
        for (User user : allByFirstNameOrLastNameOrPinCode) {
            LOG.info("allByFirstNameOrLastNameOrPinCode: " + user);
        }
        return allByFirstNameOrLastNameOrPinCode;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            LOG.info("userList: " + user);
        }
        return userList;
    }

}
