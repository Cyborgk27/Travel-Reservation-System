package com.cepeda.trs;

import com.cepeda.trs.controller.UserController;
import com.cepeda.trs.controller.UserController2;
import com.cepeda.trs.model.entities.Role;
import com.cepeda.trs.model.entities.Travel;
import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.model.persistence.repositories.RoleRepository;
import com.cepeda.trs.model.persistence.repositories.TravelRepository;
import com.cepeda.trs.model.persistence.repositories.UserRepository;
import com.cepeda.trs.services.AgencyService;
import com.cepeda.trs.services.UserService;
import com.cepeda.trs.view.FrmLogin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
/**
 *
 * @author CyborgK27
 */
public class TRS {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = emf.createEntityManager();
        
        UserRepository userRepository = new UserRepository(User.class);
        userRepository.setEntityManager(entityManager);
        
        RoleRepository roleRepository = new RoleRepository(Role.class);
        roleRepository.setEntityManager(entityManager);
        
        TravelRepository travelRepository = new TravelRepository(Travel.class);
        travelRepository.setEntityManager(entityManager);
        
        //ServicesTRS.ConfigureServices();
//        var user = User.builder()
//                .username("AlexKiller0408")
//                .email("alexkiller@gmail.com")
//                .password("password123")
//                .role(roleRepository.getById(1).get())
//                .build();
//        
//        userRepository.create(user);
//        System.out.println(user);

//        var user = userRepository.getById(1).get();
//        System.out.println(user.toString());
        UserService userService = new UserService(userRepository);
        AgencyService agencyService = new AgencyService(travelRepository);

//        
        UserController2 userController2 = new UserController2(new FrmLogin(), userService);
        //UserController uc = new UserController(userRepository, new FrmLogin());

//        var user = userRepository.login("alexkiller@gmail.com", "password123");
//        System.out.println(user.get().toString());
    }
}
