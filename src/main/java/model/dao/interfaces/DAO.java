package model.dao.interfaces;

import java.util.List;

/**
 * Created by admin on 18.04.2017.
 */
public interface DAO<E, PK> {

    E getById(PK id);

    List<E> getAll();

    E save(E entity);

    void insert(E entity);

    int update(E entity);

    int delete(E entity);

}