package ru.job4j.accident.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplateStore;
import ru.job4j.accident.repository.AccidentTypeJdbcTemplateStore;
import ru.job4j.accident.repository.RuleJdbcTemplateStore;
import ru.job4j.accident.repository.Store;

import javax.servlet.http.HttpServletRequest;

/**
 * Class AccidentControl.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 05.02.2021
 */
@Controller
public class AccidentControl {
    private final AccidentJdbcTemplateStore accidents;
    private final Store<AccidentType> typeStore;
    private final Store<Rule> ruleStore;

    public AccidentControl(AccidentJdbcTemplateStore accidents, AccidentTypeJdbcTemplateStore typeStore,
                           RuleJdbcTemplateStore ruleStore) {
        this.accidents = accidents;
        this.typeStore = typeStore;
        this.ruleStore = ruleStore;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", typeStore.findAll());
        model.addAttribute("rules", ruleStore.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setType(typeStore.findById(accident.getType().getId()));
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            accident.setRules(accidents.arrToSet(ids));
        }
        accidents.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", typeStore.findAll());
        model.addAttribute("rules", ruleStore.findAll());
        return "accident/edit";
    }

}