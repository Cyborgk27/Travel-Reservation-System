package com.cepeda.trs.model.persistence.interfaces;

import com.cepeda.trs.model.entities.Travel;
import com.cepeda.trs.model.entities.User;
import java.util.List;

/**
 *
 * @author CyborgK27
 */
public interface ITravelRepository extends IGenericRepository<Travel> {
    List<Travel> findByUser(User user);
}
