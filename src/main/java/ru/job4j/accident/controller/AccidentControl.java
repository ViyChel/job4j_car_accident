package ru.job4j.accident.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

/**
 * Class AccidentControl.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 05.02.2021
 */
@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.findTypeAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accident.setType(accidents.findTypeById(accident.getType().getId()));
        accidents.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", accidents.findTypeAll());
        return "accident/edit";
    }

}