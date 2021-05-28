package com.app.store.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    @Column
    private Long customerId;

    @Column
    @Length(min = 3, max = 20, message = "Name must be at least 3 characters, Name must be max 20 characters")
    private String name;

    @Column
    @Length(min = 3, max = 20, message = "Surname must be at least 3 characters, Surname must be max 20 characters")
    private String surname;

    @Column
    @NotNull(message = "Please enter a phone number")
    @Pattern(regexp = "^[9][0-9]{7}$",message = "Your phone number should start with 9 and contains 8 digits")
    private String phone;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
