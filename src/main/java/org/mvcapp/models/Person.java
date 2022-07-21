package org.mvcapp.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotBlank(message = "Name should be not empty!")
    @Size(min=2, max=15, message = "The name should not be between 2 and 15 characters")
    private String name;

    @NotEmpty(message = "Email should be not empty!")
    @Email(message = "Please enter correct email")
    private String email;
    private String address;

    public Person(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    @Pattern(regexp = "[A-Z][a-z]{2,20}," +
            "\\s[1-9]{5}," +
            "\\s[A-Z][a-z]{2,20}," +
            "\\s[1-9]{1,4}",
            message = "Please enter your address in this format: City, index, Street, house number")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person(int i, String s, String s1) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
