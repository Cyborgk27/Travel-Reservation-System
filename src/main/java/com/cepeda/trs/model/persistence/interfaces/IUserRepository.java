package com.cepeda.trs.model.persistence.interfaces;

import com.cepeda.trs.model.entities.User;
import java.util.Optional;

/**
 *
 * @author CyborgK27
 */
public interface IUserRepository extends IGenericRepository<User>{
    Optional<User> login(String email, String password);
}