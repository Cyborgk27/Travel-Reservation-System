package com.cepeda.trs.model.persistence.repositories;

import com.cepeda.trs.model.entities.Role;
import com.cepeda.trs.model.persistence.interfaces.IRoleRepository;

/**
 *
 * @author CyborgK27
 */
public class RoleRepository extends GenericRepository<Role>
        implements IRoleRepository {
    
    public RoleRepository(Class<Role> entityClass) {
        super(entityClass);
    }
}
