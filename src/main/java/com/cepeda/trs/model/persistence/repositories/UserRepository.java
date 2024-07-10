package com.cepeda.trs.model.persistence.repositories;

import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.model.persistence.interfaces.IUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

/**
 *
 * @author CyborgK27
 */
public class UserRepository extends GenericRepository<User>
        implements IUserRepository {

    /* Define la consulta JPQL para buscar el usuario por username
     * y password
     */
    private static final String JPQL_LOGIN_QUERY
            = "FROM User u WHERE u.email ="
            + " :email AND u.password = :password AND u.state != 0";

    public UserRepository(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    public Optional<User> login(String email, String password) {
        try {
            TypedQuery<User> query = getEntityManager().createQuery(JPQL_LOGIN_QUERY,
                    User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            // Devuelve el primer resultado como un Optional
            return query.getResultStream().findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
