package com.neosoft.poc.repository;

import com.neosoft.poc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByOrderByDobDescDojDesc();

    List<User> findAllByFirstNameOrLastNameOrPinCode(String firstName,
                                                     String lastName,
                                                     String pinCode);

}