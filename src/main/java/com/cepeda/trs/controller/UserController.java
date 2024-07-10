package com.cepeda.trs.controller;

import com.cepeda.trs.model.entities.Travel;
import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.model.persistence.interfaces.IUserRepository;
import com.cepeda.trs.model.persistence.repositories.UserRepository;
import com.cepeda.trs.view.FrmLogin;
import com.cepeda.trs.view.JpAgency;
import com.cepeda.trs.view.RegisterTravel;
import com.cepeda.utiities.Validators;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CyborgK27
 */
public class UserController {

    private IUserRepository _userRepository;
    private FrmLogin _view;

    public UserController(IUserRepository userRepository,
            FrmLogin view) {
        _userRepository = userRepository;
        _view = view;

        _view.setVisible(true);
        _view.btnLogin.addActionListener(new LoginUserListener());

    }

    class LoginUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JLabel emailValidatorLabel = _view.lblEmailValidator;
            JLabel passwordValidatorLabel = _view.lblPasswordValidator;
            String email = _view.txtEmail.getText();
            String password = new String(_view.pfPassword.getPassword());

            if (Validators.validateLogin(email, password)
                    && Validators.validateEmail(email, emailValidatorLabel)
                    && Validators.validatePassword(password, passwordValidatorLabel)) {

                Optional<User> userOptional = _userRepository
                        .login(email, password);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    // Aquí puedes manejar el inicio de sesión exitoso, por ejemplo:
                    validateRole(user);
                    JOptionPane.showMessageDialog(_view,
                            "Inicio de sesión exitoso",
                            "Login", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(_view,
                            "Usuario no encontrado o credenciales incorrectas.",
                            "Login Fallido", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(_view,
                        "Validaciones fallidas. Por favor, revisa tus entradas.",
                         "Login Fallido", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void validateRole(User user) {
            final int ADMINISTRADOR = 1;
            final int AGENCIA = 2;
            final int PASAJERO = 3;

            var roleId = user.getRole().getId();

            switch (roleId) {
                case ADMINISTRADOR:

                    break;
                case AGENCIA:
                    panelAgency(user);
                    break;
                case PASAJERO:

                    break;
                default:
                    throw new AssertionError();
            }

        }

        private void panelAgency(User user) {
            JpAgency pAgency = new JpAgency();
            fillDateUser(user, pAgency);
            actionAgency(pAgency, user);
            pAgency.setSize(720, 360);
            
            
            _view.content.removeAll();
            _view.content.add(pAgency);
            _view.content.revalidate();
            _view.content.repaint();
            
        }

        private void fillDateUser(User user, JpAgency pAgency) {
            String username = user.getUsername();
            String role = user.getRole().getName();
            int travels = user.getTravels().size();

            pAgency.lblUsername.setText(username);
            pAgency.lblRolename.setText(role);
            pAgency.lblTravels.setText("" + travels);
        }

        private void actionAgency(JpAgency pAgency, User user) {
            var travels = user.getTravels();
            var table = pAgency.tblTravels;
            var userId = user.getId();
            
            fillTable(table, travels);
            senToTravelRegister(userId, pAgency);
            
        }

        private void fillTable(JTable table, List<Travel> travels) {
            String[] columnNames = {"Destino", "Lugares disponibles", "Estado"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            for (Travel travel : travels) {
                String destination = travel.getDestination();
                int avaliablePlaces = travel.getAvaliablePlaces();
                String state = getStateString(travel.getState());
                Object[] row = {destination, avaliablePlaces, state};
                tableModel.addRow(row);
            }
            table.setModel(tableModel);
        }

        private String getStateString(int state) {
            return state == 0 ? "INACTIVO" : "ACTIVO";
        }

        private void senToTravelRegister(int id, JpAgency pAgency) {
            pAgency.btnAddTravel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RegisterTravel rt = new RegisterTravel(_view, true);
                    rt.setVisible(true);
                }
            });
        }

    }
 
}
