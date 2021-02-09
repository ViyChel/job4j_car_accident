package ru.job4j.accident.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class AccidentMem.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 04.02.2021
 */
@Data
//@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final HashMap<Integer, AccidentType> accidentsTypes = new HashMap<>();
    private final HashMap<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        AccidentType twoCar = AccidentType.of(1, "Две машины");
        AccidentType carAndMan = AccidentType.of(2, "Машина и человек");
        AccidentType carAndBike = AccidentType.of(3, "Машина и велосипед");
        Rule rule1 = Rule.of(1, "Статья. 1");
        Rule rule2 = Rule.of(2, "Статья. 2");
        Rule rule3 = Rule.of(3, "Статья. 3");
        Set<Rule> accidentRules = new HashSet<>(Arrays.asList(rule1, rule3));
        Accident accident1 = new Accident(1, "name1", "test1", "address1", twoCar, accidentRules);
        Accident accident2 = new Accident(2, "name2", "test2", "address2", carAndMan, accidentRules);
        Accident accident3 = new Accident(3, "name3", "test3", "address3", carAndBike, accidentRules);

        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
        accidents.put(accident3.getId(), accident3);
        accidentsTypes.put(twoCar.getId(), twoCar);
        accidentsTypes.put(carAndMan.getId(), carAndMan);
        accidentsTypes.put(carAndBike.getId(), carAndBike);
        rules.put(rule1.getId(), rule1);
        rules.put(rule2.getId(), rule2);
        rules.put(rule3.getId(), rule3);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public AccidentType findTypeById(int id) {
        return accidentsTypes.get(id);
    }

    public Collection<AccidentType> findAllTypes() {
        return accidentsTypes.values();
    }

    public Collection<Rule> findAllRules() {
        return rules.values();
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }
}
