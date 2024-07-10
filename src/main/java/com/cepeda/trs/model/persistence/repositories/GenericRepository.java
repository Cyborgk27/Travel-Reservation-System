package com.cepeda.trs.model.persistence.repositories;

import com.cepeda.trs.model.entities.BaseEntity;
import com.cepeda.trs.model.persistence.interfaces.IGenericRepository;
import com.cepeda.utiities.StateTypes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author CyborgK27
 */
public class GenericRepository<T extends BaseEntity>
        implements IGenericRepository<T> {

    @PersistenceContext
    private EntityManager _entityManager;
    private final Class<T> _entityClass;

    // Consultas JPQL como constantes
//    private static final String SELECT_ALL_QUERY = 
//            "FROM %s e WHERE e.state != 0";
//    private static final String SELECT_BY_ID_QUERY = 
//            "FROM %s e WHERE e.id = :id AND e.state != 0";
    private static final String SELECT_ALL_QUERY = "FROM %s e";
    private static final String SELECT_BY_ID_QUERY = "FROM %s e WHERE e.id = :id";

    public GenericRepository(Class<T> entityClass) {
        _entityClass = entityClass;
    }

    public void setEntityManager(EntityManager entityManager) {
        this._entityManager = entityManager;
    }

    protected EntityManager getEntityManager() {
        return _entityManager;
    }

    @Override
    public List<T> getAll() {
        String hql = String.format(SELECT_ALL_QUERY, _entityClass.getSimpleName());
        TypedQuery<T> query = _entityManager.createQuery(hql, _entityClass);
        return query.getResultList();
    }

    @Override
    public Optional<T> getById(int id) {
        String hql = String.format(SELECT_BY_ID_QUERY, _entityClass.getSimpleName());
        TypedQuery<T> query = _entityManager.createQuery(hql, _entityClass);
        query.setParameter("id", id);
        return query.getResultStream().findFirst();
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
                // Actualiza el estado en la base de datos
                _entityManager.merge(entity);
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
