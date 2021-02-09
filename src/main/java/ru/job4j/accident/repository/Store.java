package ru.job4j.accident.repository;

import jdk.dynalink.linker.LinkerServices;

import java.util.List;

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

}
