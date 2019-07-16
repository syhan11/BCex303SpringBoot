package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String showTodos(Model model){
        model.addAttribute("todos", todoRepository.findAll());
        return "showAll";
    }

    @GetMapping("/add")
    public String addTodo(Model model){
        model.addAttribute("todo", new Todo());
        return "addTodo";
    }

    @PostMapping("/process")
    public String processTodo(@Valid Todo todo, BindingResult result) {
        if (result.hasErrors())
            return "addTodo";
        else {
            todoRepository.save(todo);
            return "redirct:/";
        }
    }

}
