package ru.job4j.accident.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

/**
 * Interface RuleRepository.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 11.02.2021
 */
public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
