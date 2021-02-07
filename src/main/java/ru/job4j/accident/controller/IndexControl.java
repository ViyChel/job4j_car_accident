package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.Map;

/**
 * Class IndexControl.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 03.02.2021
 */
@Controller
public class IndexControl {
    private AccidentMem store;

    public IndexControl(AccidentMem store) {
        this.store = store;
    }

    @GetMapping("/")
    public String index(Model model) {
        Collection<Accident> list = store.findAll();
        model.addAttribute("accidents", list);
        return "index";
    }
}