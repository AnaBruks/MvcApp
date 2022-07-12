package org.mvcapp.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.mvcapp.dao.PeopleDAO;

public class Person {
    private int id;
    @NotBlank(message = "Name should be not empty!")
    @Size(min=2, max=15, message = "The name should not be between 2 and 15 characters")
    private String name;

    @NotEmpty(message = "Email should be not empty!")
    @Email(message = "Please enter correct email")
    private String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Person() {
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
