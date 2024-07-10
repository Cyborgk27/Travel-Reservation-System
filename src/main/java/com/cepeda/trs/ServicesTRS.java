package com.cepeda.trs;

import com.cepeda.trs.model.entities.Role;
import com.cepeda.trs.model.entities.Travel;
import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.model.persistence.repositories.RoleRepository;
import com.cepeda.trs.model.persistence.repositories.TravelRepository;
import com.cepeda.trs.model.persistence.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author CyborgK27
 */
public class ServicesTRS {
    public static void ConfigureServices(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = emf.createEntityManager();
        
        UserRepository userRepository = new UserRepository(User.class);
        userRepository.setEntityManager(entityManager);
        
        RoleRepository roleRepository = new RoleRepository(Role.class);
        roleRepository.setEntityManager(entityManager);
        
        TravelRepository travelRepository = new TravelRepository(Travel.class);
        travelRepository.setEntityManager(entityManager);
    }
}
