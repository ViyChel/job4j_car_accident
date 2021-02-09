package ru.job4j.accident.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Class AccidentJdbcTemplate.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 09.02.2021
 */
//@Repository
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
                    return accident;
                });
    }
}
