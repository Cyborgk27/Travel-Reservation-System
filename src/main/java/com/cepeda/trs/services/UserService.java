package com.cepeda.trs.services;

import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.model.persistence.interfaces.IUserRepository;
import com.cepeda.utiities.Validators;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.util.Optional;
/**
 *
 * @author CyborgK27
 */
public class UserService {
    private final IUserRepository _userRepository;

    public UserService(IUserRepository userRepository) {
        _userRepository = userRepository;
    }
    
    public Optional<User> signIn(String email, String password,
            JLabel lblEmailValidator, JLabel lblPasswordValidator){
        if (!Validators.validateLogin(email, password)) {
            JOptionPane.showMessageDialog(null
                    ,"Debe ingresar ambos campos (email y contraseña).");
            return Optional.empty();
        }

        if (!Validators.validateEmail(email, lblEmailValidator)) {
            JOptionPane.showMessageDialog(null
                    ,"El email ingresado no es válido.");
            return Optional.empty();
        }

        if (!Validators.validatePassword(password, lblPasswordValidator)) {
            JOptionPane.showMessageDialog(null
                    ,"La contraseña debe tener al menos 8 caracteres.");
            return Optional.empty();
        }

        Optional<User> user = _userRepository.login(email, password);
        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(null
                     ,"Email y/o contraseña incorrectos.");
        }
        
        return user;
    }
}
