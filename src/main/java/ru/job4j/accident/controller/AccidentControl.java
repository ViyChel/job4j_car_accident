package ru.job4j.accident.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.springdata.AccidentRepository;
import ru.job4j.accident.repository.springdata.AccidentTypeRepository;
import ru.job4j.accident.repository.springdata.RuleRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class AccidentControl.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 05.02.2021
 */
@Controller
public class AccidentControl {
    private final AccidentRepository accidents;
    private final AccidentTypeRepository typeStore;
    private final RuleRepository ruleStore;

    public AccidentControl(AccidentRepository accidents, AccidentTypeRepository typeStore, RuleRepository ruleStore) {
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
        typeStore.findById(accident.getType().getId()).ifPresent(accident::setType);
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            accident.setRules(arrToSet(ids, ruleStore.findAll()));
        }
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        Accident acc = accidents.findById(id);
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", typeStore.findAll());
        model.addAttribute("rules", ruleStore.findAll());
        return "accident/edit";
    }

    private Set<Rule> arrToSet(String[] ids, Iterable<Rule> rulesStore) {
        Set<Rule> result = new LinkedHashSet<>();
        for (String el : ids) {
            int id = Integer.parseInt(el);
            for (Rule rule : rulesStore) {
                if (id == rule.getId()) {
                    result.add(rule);
                }
            }
        }
        return result;
    }
}