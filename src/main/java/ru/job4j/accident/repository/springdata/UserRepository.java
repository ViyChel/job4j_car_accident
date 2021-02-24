package ru.job4j.accident.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

/**
 * Class UserRepository.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 24.02.2021
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
