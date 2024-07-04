package com.cepeda.trs.controller;

import com.cepeda.trs.model.persistence.interfaces.IUserRepository;
import com.cepeda.trs.model.persistence.repositories.UserRepository;
import com.cepeda.utiities.Validators;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author CyborgK27
 */
public class UserController {

    private IUserRepository _UserRepository;

    public UserController() {
        _UserRepository = new UserRepository();
    }

    public void SignIn(JTextField txtEmail, JPasswordField pfPassword,
            JLabel lblEmailValidator, JLabel lblPasswordValidator) {
        String email = txtEmail.getText();
        String password = new String(pfPassword.getPassword());
        if (Validators.validateLogin(email, password)) {
            Validators.validateEmail(email, lblEmailValidator);
            Validators.validatePassword(password, lblPasswordValidator);
            JOptionPane.showMessageDialog(null, "Inicio de sesión con éxito");
        }else{
            JOptionPane.showMessageDialog(null, "No dejar campos vacios");
        }
    }
}
