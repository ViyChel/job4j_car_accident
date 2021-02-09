package ru.job4j.accident.repository.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.List;

/**
 * Class RuleHibernateStore.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 10.02.2021
 */
@Repository
public class RuleHibernateStore implements Store<Rule> {
    private final SessionFactory sf;

    public RuleHibernateStore(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void create(Rule model) {
        this.tx(session -> session.save(model), sf);
    }

    @Override
    public Rule save(Rule model) {
        return this.tx(session -> {
            session.update(model);
            return model;
        }, sf);
    }

    @Override
    public Rule findById(int id) {
        return this.tx(session -> session.get(Rule.class, id), sf);
    }

    @Override
    public List<Rule> findAll() {
        return this.tx(session -> session.createQuery("from Rule", Rule.class).list(), sf);
    }
}
