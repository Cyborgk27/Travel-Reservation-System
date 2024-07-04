package com.cepeda.trs.controller;

import com.cepeda.trs.model.persistence.interfaces.IUserRepository;
import com.cepeda.trs.model.persistence.repositories.UserRepository;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author CyborgK27
 */
public class UserController {
    private IUserRepository  _UserRepository;

    public UserController() {
        _UserRepository = new UserRepository();
    }
    
    public void SignIn(JTextField txtEmail, JPasswordField JpPassword){
        
    }
}
