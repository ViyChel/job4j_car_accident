package ru.job4j.accident.repository.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class AccidentHibernate.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 10.02.2021
 */
@Repository
@Slf4j
public class AccidentHibernateStore implements Store<Accident> {
    private final SessionFactory sf;

    public AccidentHibernateStore(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void create(Accident model) {
        this.tx(session -> session.save(model), sf);
    }

    public Accident save(Accident model) {
        return this.tx(session -> {
            session.update(model);
            return model;
        }, sf);
    }

    @Override
    public Accident findById(int id) {
        return (Accident) this.tx(session -> session.createQuery("from Accident ac left join fetch ac.rules  "
                + "where ac.id = :id")
                .setParameter("id", id).uniqueResult(), sf
        );
    }

    @Override
    public List<Accident> findAll() {
        return this.tx(session -> session.createQuery("select distinct ac from Accident ac left join fetch ac.rules",
                Accident.class).list(), sf);
    }

    public Set<Rule> arrToSet(String[] ids, Store<Rule> rulesStore) {
        return Arrays.stream(ids)
                .map(Integer::parseInt)
                .map(rulesStore::findById)
                .collect(Collectors.toSet());
    }
}