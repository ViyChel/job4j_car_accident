package ru.job4j.accident.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.Store;

import java.util.List;
import java.util.function.Function;

/**
 * Class AccidentTypeHibernateStore.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 10.02.2021
 */
//@Repository
public class AccidentTypeHibernateStore implements Store<AccidentType> {
    private final SessionFactory sf;

    public AccidentTypeHibernateStore(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void create(AccidentType model) {
        this.tx(session -> session.save(model), sf);
    }

    @Override
    public AccidentType save(AccidentType model) {
        return this.tx(session -> {
            session.update(model);
            return model;
        }, sf);
    }

    @Override
    public AccidentType findById(int id) {
        return this.tx(session -> session.get(AccidentType.class, id), sf);
    }

    @Override
    public List<AccidentType> findAll() {
        return this.tx(session -> session.createQuery("from AccidentType", AccidentType.class).list(), sf);
    }
}
