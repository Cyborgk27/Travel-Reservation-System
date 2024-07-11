package com.cepeda.trs.controller;

import com.cepeda.trs.model.entities.Travel;
import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.services.AgencyService;
import com.cepeda.trs.view.JpAgency;
import com.cepeda.trs.view.RegisterTravel;
import com.cepeda.utiities.DateUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AgencyController {

    private final JpAgency _view;
    private final AgencyService _agencyService;
    private final User _user;
    private RegisterTravel _registerTravel;

    public AgencyController(JpAgency view, AgencyService agencyService,
            User user) {
        _view = view;
        _agencyService = agencyService;
        _user = user;
        _registerTravel = new RegisterTravel(null, true);
        initialize();
    }

    private void initialize() {
        fillDateUser(_user, _view);
        _view.btnAddTravel.addActionListener(e -> openTravelCreate());
        _view.btnEdit.addActionListener(e -> openTravelEdit());
        _view.btnRemove.addActionListener(e -> removeTravel());
        _view.btnView.addActionListener(e -> viewTravel());
        _registerTravel.btnSaveChanges.addActionListener(e -> saveTravel(_registerTravel));
        
        fillTable();
    }

    private void fillDateUser(User user, JpAgency pAgency) {
        String username = user.getUsername();
        String role = user.getRole().getName();
        int travels = user.getTravels().size();

        pAgency.lblUsername.setText(username);
        pAgency.lblRolename.setText(role);
        pAgency.lblTravels.setText("" + travels);
    }

    private void openTravelCreate() {
        RegisterTravel rt = _registerTravel;
        rt.setVisible(true);
        
    }

    private void saveTravel(RegisterTravel rt) {
        var travel = createTravel();
        if (_agencyService.createTravel(travel, _user)) {
//            JOptionPane.showMessageDialog(null,
//                    "Su viaje se ha guardado con éxito");
            fillTable();
        } else {
//            JOptionPane.showMessageDialog(null,
//                    "No se pudo guardar el viaje. Por favor, intente nuevamente.");
        }
    }

    private Travel createTravel() {
        String travelCode = _registerTravel.txtCodeTravel.getText().trim();
        String destination = _registerTravel.txtDestination.getText().trim();
        int avaliablePlaces = (Integer) _registerTravel.jsAvaliablePlaces.getValue();
        float price = Float.parseFloat(_registerTravel.txtPrice.getText());
        LocalDate travelDate = DateUtils
                .convertDateToLocalDate(_registerTravel.jdTravelDate.getDate());
        LocalDate returnDate = DateUtils
                .convertDateToLocalDate(_registerTravel.jdReturnDate.getDate());

        return Travel.builder()
                .codeTravel(travelCode)
                .destination(destination)
                .avaliablePlaces(avaliablePlaces)
                .price(price)
                .travelDate(travelDate)
                .returnDate(returnDate)
                .build();
    }

    private void fillTable() {
        var table = _view.tblTravels;
        var travels = _agencyService.getAllTravels(_user);
        
        
        String[] columnNames = {"ID","Destino"
                ,"Lugares disponibles", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        for (Travel travel : travels) {
            int travelId = travel.getId();
            String destination = travel.getDestination();
            int avaliablePlaces = travel.getAvaliablePlaces();
            String state = getStateString(travel.getState());
            
            Object[] row = {travelId, destination, avaliablePlaces, state};
            //table.setDefaultRenderer(Object.class, new RenderTable());
            tableModel.addRow(row);
        }
        table.setModel(tableModel);
        _view.lblTravels.setText(travels.size()+"");
    }

    private String getStateString(int state) {
        return state == 0 ? "INACTIVO" : "ACTIVO";
    }

    private List<JButton> buttons() {
        
        JButton btnUpdate = new JButton("Editar");
        JButton btnRemove = new JButton("Remover");
        
        List<JButton> buttons = new ArrayList<>();
        buttons.add(btnUpdate);
        buttons.add(btnRemove);
        return buttons;
    }

    private void openTravelEdit() {
        RegisterTravel rt = _registerTravel;
        rt.setVisible(true);
    }

    private void removeTravel() {
        var table = _view.tblTravels;
        var userId = getSelectRowUserId(table);
        _agencyService.removeTravel(userId);
        fillTable();
        
    }

    private void viewTravel() {
        var table = _view.tblTravels;
        
        var userId = getSelectRowUserId(table);
        var user = _agencyService.getTravelById(userId).get();
        JOptionPane.showMessageDialog(null, user.toString());
    }

    private int getSelectRowUserId(JTable table) {
        int CELL = 0;
        int SELECT_ROW = table.getSelectedRow();
        Object firstCellValue = table.getValueAt(SELECT_ROW, CELL);
        
        return (Integer) firstCellValue;
    }
    
    private void EditTravel(RegisterTravel rt) {
        var travel = createTravel();
        
        if (_agencyService.updateTravel(travel)) {
            JOptionPane.showMessageDialog(null,
                    "Su viaje se ha Editado con éxito");
            fillTable();
        } else {
//            JOptionPane.showMessageDialog(null,
//                    "No se pudo guardar el viaje. Por favor, intente nuevamente.");
        }
    }
}
