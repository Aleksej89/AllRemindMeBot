package org.allRemindMeBot.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID> {
    void save(T data);

    Optional<T> findById(ID id);

    Optional<List<T>> findAll();

    void delete(T data);

    void update();
}
