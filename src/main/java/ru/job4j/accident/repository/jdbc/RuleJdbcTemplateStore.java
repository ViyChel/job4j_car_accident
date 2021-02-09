package ru.job4j.accident.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.List;

/**
 * Class RuleJdbcTemplateStore.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 09.02.2021
 */
//@Repository
public class RuleJdbcTemplateStore implements Store<Rule> {
    private final JdbcTemplate jdbc;

    public RuleJdbcTemplateStore(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void create(Rule model) {

    }

    @Override
    public Rule save(Rule model) {
        jdbc.update(
                "insert into rules (name) values (?)",
                model.getName());
        return model;
    }

    @Override
    public Rule findById(int id) {
        return jdbc.queryForObject(
                "select id, name from rules where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }

    @Override
    public List<Rule> findAll() {
        return jdbc.query(
                "select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }
}
