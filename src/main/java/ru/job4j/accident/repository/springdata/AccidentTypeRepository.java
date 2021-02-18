package ru.job4j.accident.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

/**
 * Interface AccidentTypeRepository.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 11.02.2021
 */
public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
