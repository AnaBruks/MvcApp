package org.mvcapp.util;

import org.mvcapp.dao.PeopleDAO;
import org.mvcapp.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PeopleValidator implements Validator {
    private final PeopleDAO peopleDAO;

    public PeopleValidator(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleDAO.showPerson(person.getEmail()).isPresent()){
            errors.rejectValue("email", "", "User with this email is already exists");
        }
    }
}
