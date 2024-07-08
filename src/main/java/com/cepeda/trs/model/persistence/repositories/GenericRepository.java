/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cepeda.trs.model.persistence.repositories;

import com.cepeda.trs.model.entities.BaseEntity;
import com.cepeda.trs.model.persistence.interfaces.IGenericRepository;
import com.cepeda.utiities.StateTypes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author CyborgK27
 */
public class GenericRepository<T extends BaseEntity> implements IGenericRepository<T> {

    @PersistenceContext
    private EntityManager _entityManager;
    private final Class<T> _entityClass;

    public GenericRepository(Class<T> entityClass) {
        _entityClass = entityClass;
    }

    @Override
    public List<T> getAll() {
        return _entityManager.createQuery(
                "from" + _entityClass.getName(),
                _entityClass).getResultList();
    }

    @Override
    public Optional<T> getById(int id) {
        return Optional.ofNullable(_entityManager.find(_entityClass, id));
    }

    @Override
    public boolean create(T entity) {

        entity.setState(StateTypes.ACTIVE.ordinal());

        try {
            _entityManager.getTransaction().begin();
            _entityManager.persist(entity);
            _entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            _entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(T entity) {
        try {
            _entityManager.getTransaction().begin();
            _entityManager.merge(entity);
            _entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            _entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        Optional<T> optionalEntity = getById(id);
        if (optionalEntity.isPresent()) {
            T entity = optionalEntity.get();
            entity.setState(StateTypes.INACTIVE.ordinal());
            try {
                _entityManager.getTransaction().begin();
                _entityManager.merge(entity); // Actualiza el estado en la base de datos
                _entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                _entityManager.getTransaction().rollback();
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
