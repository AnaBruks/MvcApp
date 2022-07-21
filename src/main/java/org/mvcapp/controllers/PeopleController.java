package org.mvcapp.controllers;

import jakarta.validation.Valid;
import org.mvcapp.dao.PeopleDAO;
import org.mvcapp.models.Person;
import org.mvcapp.util.PeopleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO personDAO;
    private final PeopleValidator pplValidator;

    @Autowired
    public PeopleController(PeopleDAO personDAO, PeopleValidator pplValidator) {
        this.personDAO = personDAO;
        this.pplValidator = pplValidator;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("people", personDAO.showAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public static String showPerson(@PathVariable("id") int id, Model model) {
        return "people/{id}";
    }

    @GetMapping("/new") // for getting a new form for creating a new person
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        pplValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.showPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        pplValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
