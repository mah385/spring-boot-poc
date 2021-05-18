package com.neosoft.poc;

import com.neosoft.poc.requestbody.CreateUserDTO;
import com.neosoft.poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class SpringBootPocApplication {

    private final UserService userService;

    @Autowired
    public SpringBootPocApplication(UserService userService) {
        this.userService = userService;
    }

    @EventListener(value = ApplicationReadyEvent.class)
    public void sampleUserData() {
        System.out.println("inside sampleUserData");

        CreateUserDTO createUserDTO_1 = new CreateUserDTO();
        createUserDTO_1.setFirstName("Md. Arif");
        createUserDTO_1.setLastName("Hussain");
        createUserDTO_1.setEmail("arif.hussain@gmail.com");
        createUserDTO_1.setPhoneNumber("8017535001");
        createUserDTO_1.setPinCode("700016");
        createUserDTO_1.setDob(LocalDate.of(1993, 4, 21));
        createUserDTO_1.setDoj(LocalDate.of(2021, 4, 30));

        CreateUserDTO createUserDTO_2 = new CreateUserDTO();
        createUserDTO_2.setFirstName("Venga");
        createUserDTO_2.setLastName("Nathan");
        createUserDTO_2.setEmail("venga.nathan@gmail.com");
        createUserDTO_2.setPhoneNumber("8017535002");
        createUserDTO_2.setPinCode("700015");
        createUserDTO_2.setDob(LocalDate.of(1994, 7, 3));
        createUserDTO_2.setDoj(LocalDate.of(2021, 4, 30));

        CreateUserDTO createUserDTO_3 = new CreateUserDTO();
        createUserDTO_3.setFirstName("Mozammil");
        createUserDTO_3.setLastName("Rahman");
        createUserDTO_3.setEmail("mozammil.rahman@gmail.com");
        createUserDTO_3.setPhoneNumber("8017535003");
        createUserDTO_3.setPinCode("700016");
        createUserDTO_3.setDob(LocalDate.of(1993, 4, 21));
        createUserDTO_3.setDoj(LocalDate.of(2020, 6, 15));

        CreateUserDTO createUserDTO_4 = new CreateUserDTO();
        createUserDTO_4.setFirstName("Almas");
        createUserDTO_4.setLastName("Rabbani");
        createUserDTO_4.setEmail("almas.rabbani@gmail.com");
        createUserDTO_4.setPhoneNumber("8017535004");
        createUserDTO_4.setPinCode("700014");
        createUserDTO_4.setDob(LocalDate.of(1993, 4, 21));
        createUserDTO_4.setDoj(LocalDate.of(2021, 1, 25));

        userService.createUser(createUserDTO_1);
        userService.createUser(createUserDTO_2);
        userService.createUser(createUserDTO_3);
        userService.createUser(createUserDTO_4);

    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPocApplication.class, args);
    }

}
