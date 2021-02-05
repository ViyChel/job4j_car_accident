package ru.job4j.accident.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;

/**
 * Class AccidentMem.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 04.02.2021
 */
@Data
@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        Accident accident1 = new Accident(1, "name1", "test1", "address1");
        Accident accident2 = new Accident(2, "name2", "test2", "address2");
        Accident accident3 = new Accident(3, "name3", "test3", "address3");

        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
        accidents.put(accident3.getId(), accident3);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accidents.put(accident.getId(), accident);
    }
}
