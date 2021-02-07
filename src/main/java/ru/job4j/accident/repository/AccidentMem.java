package ru.job4j.accident.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

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
    private final HashMap<Integer, AccidentType> accidentsType = new HashMap<>();

    public AccidentMem() {
        AccidentType twoCar = AccidentType.of(1, "Две машины");
        AccidentType carAndMan = AccidentType.of(2, "Машина и человек");
        AccidentType carAndBike = AccidentType.of(3, "Машина и велосипед");
        Accident accident1 = new Accident(1, "name1", "test1", "address1", twoCar);
        Accident accident2 = new Accident(2, "name2", "test2", "address2", carAndMan);
        Accident accident3 = new Accident(3, "name3", "test3", "address3", carAndBike);

        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
        accidents.put(accident3.getId(), accident3);
        accidentsType.put(twoCar.getId(), twoCar);
        accidentsType.put(carAndMan.getId(), carAndMan);
        accidentsType.put(carAndBike.getId(), carAndBike);
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
        return accidentsType.get(id);
    }

    public Collection<AccidentType> findTypeAll() {
        return accidentsType.values();
    }
}
