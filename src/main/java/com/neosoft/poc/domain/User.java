package com.neosoft.poc.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@ToString
@Entity
@Table(name = "tbl_users")
public class User extends BaseModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)", updatable = false)
    private String id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "doj")
    private LocalDate doj;

    @Column(name = "is_active", nullable = false, columnDefinition = "tinyint(1)")
    private boolean isActive;

    public User() {
    }

    public User(String id,
                String firstName,
                String lastName,
                String email,
                String phoneNumber,
                String pinCode,
                LocalDate dob,
                LocalDate doj,
                boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
        this.dob = dob;
        this.doj = doj;
        this.isActive = isActive;
    }
}
