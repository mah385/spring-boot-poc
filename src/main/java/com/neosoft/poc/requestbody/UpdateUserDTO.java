package com.neosoft.poc.requestbody;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Setter
@Getter
@ToString
public class UpdateUserDTO {

    private String firstName;

    private String lastName;

    @Email(message = "Invalid Email")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number")
    private String phoneNumber;

    private String pinCode;

    @Past(message = "Date Of Birth must be in the past")
    private LocalDate dob;

    @PastOrPresent(message = "Date Of Join must not be in the future")
    private LocalDate doj;

}
