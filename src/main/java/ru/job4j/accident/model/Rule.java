package ru.job4j.accident.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Class Rule.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 08.02.2021
 */
@Component
@Data
public class Rule {
    private int id;
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
    }
}
