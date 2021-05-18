package com.neosoft.poc.requestbody;

import com.neosoft.poc.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Setter
@Getter
@ToString
public class CreateUserDTO {

    @NotBlank(message = "First Name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number")
    private String phoneNumber;

    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid Pin Code Number")
    private String pinCode;

    @Past(message = "Date Of Birth must be in the past")
    private LocalDate dob;

    @PastOrPresent(message = "Date Of Join must not be in the future")
    private LocalDate doj;

    public User getUserFromUserDTO() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPinCode(pinCode);
        user.setDob(dob);
        user.setDoj(doj);
        user.setActive(true);
        return user;
    }

}
