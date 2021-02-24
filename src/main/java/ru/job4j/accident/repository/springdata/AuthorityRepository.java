package ru.job4j.accident.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Authority;

/**
 * Class AuthorityRepository.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 24.02.2021
 */
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}