package it.lessons.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.lessons.pizzeria.model.Pizza;
import it.lessons.pizzeria.repository.PizzaRepository;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pizze")
public class PizzeController {

    @Autowired
    private PizzaRepository pizzaRepo;

    @GetMapping
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

        List<Pizza> allPizza;

        if (keyword != null && !keyword.isBlank()) {
            allPizza = pizzaRepo.findByName(keyword);
        } else {
            allPizza = pizzaRepo.findAll();
        }
        model.addAttribute("pizze", allPizza);

        return "pizze/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable(name = "id") long id,
            @RequestParam(name = "keyword", required = false) String keyword, Model model) {

        Optional<Pizza> pizzaOptional = pizzaRepo.findById(id);

        if (pizzaOptional.isPresent()) {
            model.addAttribute("pizza", pizzaOptional.get());

        }
        if (keyword == null || keyword.isBlank() || keyword.equals("null")) {
            model.addAttribute("pizzaUrl", "/pizze");
        } else {
            model.addAttribute("pizzaUrl", "/pizze?keyword=" + keyword);
        }

        return "pizze/show";
    }

}
