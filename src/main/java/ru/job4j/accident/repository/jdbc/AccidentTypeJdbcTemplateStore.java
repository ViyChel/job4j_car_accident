package ru.job4j.accident.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.Store;

import java.util.List;

/**
 * Class AccidentTypeJdbcTemplateStore.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 09.02.2021
 */
//@Repository
public class AccidentTypeJdbcTemplateStore implements Store<AccidentType> {
    private final JdbcTemplate jdbc;

    public AccidentTypeJdbcTemplateStore(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void create(AccidentType model) {

    }

    @Override
    public AccidentType save(AccidentType model) {
        jdbc.update(
                "insert into types (name) values (?)",
                model.getName());
        return model;
    }

    @Override
    public AccidentType findById(int id) {
        return jdbc.queryForObject(
                "select id, name from types where id = ?",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                }, id);
    }

    @Override
    public List<AccidentType> findAll() {
        return jdbc.query(
                "select id, name from types",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }
}
