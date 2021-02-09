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
import ru.job4j.accident.repository.Store;
import ru.job4j.accident.repository.hibernate.AccidentHibernateStore;
import ru.job4j.accident.repository.hibernate.AccidentTypeHibernateStore;
import ru.job4j.accident.repository.hibernate.RuleHibernateStore;

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
    private final AccidentHibernateStore accidents;
    private final Store<AccidentType> typeStore;
    private final Store<Rule> ruleStore;

    public AccidentControl(AccidentHibernateStore accidents, AccidentTypeHibernateStore typeStore,
                           RuleHibernateStore ruleStore) {
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
            accident.setRules(accidents.arrToSet(ids, ruleStore));
        }
        if (accident.getId() == 0) {
            accidents.create(accident);
        } else {
            accidents.save(accident);
        }
        return "redirect:/";
    }
/*
    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setType(typeStore.findById(accident.getType().getId()));
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            accident.setRules(accidents.arrToSet(ids, ruleStore));
        }
        accidents.save(accident);
        return "redirect:/";
    }*/

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", typeStore.findAll());
        model.addAttribute("rules", ruleStore.findAll());
        return "accident/edit";
    }

}