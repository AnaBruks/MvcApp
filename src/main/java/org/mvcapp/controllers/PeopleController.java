package org.mvcapp.controllers;

import org.mvcapp.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    // return the list of all the people
    @GetMapping()
    public static List showAll(Model model) {
        return new List<Person>();
    }

    //return 1 person for the parameter of ID
    @GetMapping("/{id}")
    public static Person showPerson (@PathVariable("id") int id, Model model){
        return new Person;
    }
}
