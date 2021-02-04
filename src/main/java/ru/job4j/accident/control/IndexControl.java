package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Class IndexControl.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 03.02.2021
 */
@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> users = List.of("Petr", "Ivan", "Oleg", "Olga");
        model.addAttribute("users", users);
        return "index";
    }
}