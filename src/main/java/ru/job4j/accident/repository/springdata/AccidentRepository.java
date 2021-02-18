package ru.job4j.accident.repository.springdata;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import java.util.List;

/**
 * Interface AccidentRepository.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 11.02.2021
 */
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query(value = "from Accident ac left join fetch ac.rules where ac.id = :id")
    Accident findById(@Param("id") int id);

    @Query(value = "select distinct ac from Accident ac left join fetch ac.rules")
    List<Accident> findAllWithRules();
}
