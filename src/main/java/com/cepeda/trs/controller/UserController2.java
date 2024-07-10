package com.cepeda.trs.controller;

import com.cepeda.trs.model.entities.Travel;
import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.model.persistence.repositories.TravelRepository;
import com.cepeda.trs.model.persistence.repositories.UserRepository;
import com.cepeda.trs.services.AgencyService;
import com.cepeda.trs.services.UserService;
import com.cepeda.trs.view.FrmLogin;
import com.cepeda.trs.view.JpAgency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author CyborgK27
 */
public class UserController2 {

    private final FrmLogin _view;
    private final UserService _userService;

    public UserController2(FrmLogin view, UserService userService) {
        this._view = view;
        this._userService = userService;
        _view.setVisible(true);
        _view.btnLogin.addActionListener(e -> signIn());
    }

    public void signIn() {
        String email = _view.txtEmail.getText();
        String password = String.valueOf(_view.pfPassword.getPassword());
        JLabel lblEmailValidator = _view.lblEmailValidator;
        JLabel lblPasswordValidator = _view.lblPasswordValidator;

        var user = _userService.signIn(email, password, lblEmailValidator,
                 lblPasswordValidator);
        if (user.isPresent()) {
            var result = user.get();

            JpAgency agencyPanel = new JpAgency();
            AgencyService agencyService;
            AgencyController agencyController = new AgencyController(agencyPanel, callServiceAgency(), result);
            
            agencyPanel.setSize(720, 360);
            _view.content.removeAll();
            _view.content.add(agencyPanel);
            _view.content.revalidate();
            _view.content.repaint();
        } else {

        }
    }

    private AgencyService callServiceAgency() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = emf.createEntityManager();
        TravelRepository travelRepository = new TravelRepository(Travel.class);
        travelRepository.setEntityManager(entityManager);

        var service = new AgencyService(travelRepository);
        return service;
    }
}
