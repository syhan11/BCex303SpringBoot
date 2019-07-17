package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        if (result.hasErrors()) {
            return "addTodo";
        }
        else {
            todoRepository.save(todo);
            return "redirect:/";
        }
    }

    @RequestMapping("/update/{id}")
    public String updateTodo(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "updateTodo";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") long id, Model model) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }

}
