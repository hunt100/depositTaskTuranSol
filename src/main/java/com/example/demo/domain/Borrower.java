package com.example.demo.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "borrowers")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Human name can't be empty!")
    private String firstName;

    @Column(name = "surname")
    @NotBlank(message = "Surname can't be empty!")
    private String surname;

    @Column(name = "email")
    @Email(message = "Error in email type")
    private String email;

    @Column(name = "input")
    @NotNull(message = "Sum can't be null")
    private BigDecimal input;

    @Column(name = "active")
    private boolean active;

    public Borrower() {
    }

    public Borrower(@NotBlank(message = "Human name can't be empty!") String name, @NotBlank(message = "Surname can't be empty!") String surname, @Email(message = "Error in email type") String email, @NotNull(message = "Sum can't be null") @Length(min = 1, max = 1000) BigDecimal input, boolean isActive) {
        this.firstName = name;
        this.surname = surname;
        this.email = email;
        this.input = input;
        this.active = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getInput() {
        return input;
    }

    public void setInput(BigDecimal input) {
        this.input = input;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        active = active;
    }
}
