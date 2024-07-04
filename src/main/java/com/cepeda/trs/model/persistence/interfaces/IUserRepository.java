package com.cepeda.trs.model.persistence.interfaces;

import com.cepeda.trs.model.entities.User;
import java.util.concurrent.Future;

/**
 *
 * @author CyborgK27
 */
public interface IUserRepository {
    Future<User> login(String email, String password);
    Future<Boolean> registerUser(User user);
}