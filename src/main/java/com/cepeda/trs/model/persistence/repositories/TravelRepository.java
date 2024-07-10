package com.cepeda.trs.model.persistence.repositories;

import com.cepeda.trs.model.entities.Travel;
import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.model.persistence.interfaces.ITravelRepository;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author CyborgK27
 */
public class TravelRepository extends GenericRepository<Travel> implements ITravelRepository{

    public TravelRepository(Class<Travel> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Travel> findByUser(User user) {
        String hql = "FROM Travel t WHERE t.user = :user";
        TypedQuery<Travel> query = getEntityManager().createQuery(hql, Travel.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
}
