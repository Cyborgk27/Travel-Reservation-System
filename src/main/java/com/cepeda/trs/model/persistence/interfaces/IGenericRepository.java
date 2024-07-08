package com.cepeda.trs.model.persistence.interfaces;

import com.cepeda.trs.model.entities.BaseEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author CyborgK27
 */
public interface IGenericRepository<T extends BaseEntity> {
    List<T> getAll();
    Optional<T> getById (int id);
    boolean create (T entity);
    boolean update (T entity);
    boolean remove (int id);
}
