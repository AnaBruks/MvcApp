package org.mvcapp.controllers;

import jakarta.validation.Valid;
import jdk.internal.icu.text.NormalizerBase;
import org.mvcapp.dao.PeopleDAO;
import org.mvcapp.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO dao;

    public PeopleController(PeopleDAO dao) {
        this.dao = dao;
    }

    @GetMapping()
    public static String showAll(Model model) {
        model.getAttribute("people", dao.showAll());
        return "people/all";
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
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        dao.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.getAttribute("person", dao.showPerson());
        return "people/edit";
    }

    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        dao.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        dao.delete(id);
        return "redirect:/people";
    }

}
