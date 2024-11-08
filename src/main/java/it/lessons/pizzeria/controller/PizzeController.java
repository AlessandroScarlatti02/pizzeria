package it.lessons.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import it.lessons.pizzeria.repository.PizzaRepository;

@Controller
@RequestMapping("/pizze")
public class PizzeController {

    @Autowired
    private PizzaRepository pizzaRepo;

    @GetMapping 
    public String index(Model model) {

        model.addAttribute("pizze", pizzaRepo.findAll());
        return "pizze/index";
    }

}
