package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Function;

/**
 * Interface Store.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 09.02.2021
 */
public interface Store<T> {
    void create(T model);

    T save(T model);

    T findById(int id);

    List<T> findAll();

    default <T> T tx(final Function<Session, T> command, SessionFactory sf) {
        try (final Session session = sf.openSession()) {
            final Transaction tx = session.beginTransaction();
            try {
                T rsl = command.apply(session);
                tx.commit();
                return rsl;
            } catch (final Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
