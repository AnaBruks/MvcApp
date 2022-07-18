package org.mvcapp.controllers;

import org.mvcapp.dao.PeopleDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/batch")
public class BatchController {
    private final PeopleDAO peopleDAO;

    public BatchController(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GetMapping()
        public String showAll(){
            return "batch/showAll";
        }

    @GetMapping("/batch-update")
    public String withBatch(){
        peopleDAO.batchUpdate();
        return "redirect:/people";
    }
}
