package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class AccidentJdbcTemplate.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 09.02.2021
 */
@Repository
public class AccidentJdbcTemplateStore implements Store<Accident> {
    private final JdbcTemplate jdbc;
    private final Store<AccidentType> typesStore;
    private final Store<Rule> rulesStore;

    private static final String CREATE = "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)";

    public AccidentJdbcTemplateStore(JdbcTemplate jdbc, AccidentTypeJdbcTemplateStore typesStore,
                                     RuleJdbcTemplateStore rulesStore) {
        this.jdbc = jdbc;
        this.typesStore = typesStore;
        this.rulesStore = rulesStore;
    }

    @Override
    public void create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId(keyHolder.getKey().intValue());
        accident.getRules().forEach(el -> jdbc.update(
                "insert into accident_rules (accident_id, rule_id) values (?, ?)",
                accident.getId(),
                el.getId()));
    }

    @Override
    public Accident save(Accident accident) {
        jdbc.update(
                "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId());
        return accident;
    }

    @Override
    public Accident findById(int id) {
        return jdbc.queryForObject(
                "select id, name, text, address, type_id from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType = typesStore.findById(rs.getInt("type_id"));
                    accident.setType(accidentType);
                    return accident;
                }, id);
    }

    @Override
    public List<Accident> findAll() {
        return jdbc.query(
                "select id, name, text, address, type_id from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType = typesStore.findById(rs.getInt("type_id"));
                    accident.setType(accidentType);
                    /*Set<Rule> rules = rulesStore.findAll();
                    accident.setName(rs.getString("rule_id"));*/
                    return accident;
                });
    }

    public Set<Rule> arrToSet(String[] ids) {
        return Arrays.stream(ids)
                .map(Integer::parseInt)
                .map(rulesStore::findById)
                .collect(Collectors.toSet());
    }
}
