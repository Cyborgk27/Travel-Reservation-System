package com.cepeda.trs.services;

import com.cepeda.trs.model.entities.Travel;
import com.cepeda.trs.model.entities.User;
import com.cepeda.trs.model.persistence.interfaces.ITravelRepository;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

public class AgencyService {
    private ITravelRepository _travelRepository;

    public AgencyService(ITravelRepository travelRepository) {
        _travelRepository = travelRepository;
    }
    
    public List<Travel> getAllTravels(User user) {
        return _travelRepository.findByUser(user);
    }
    
    public Optional<Travel> getTravelById(int id) {
        return _travelRepository.getById(id);
    }
    
    public boolean createTravel(Travel travel, User user) {
        if (travel != null && user != null) {
            travel.setUser(user);
            if (_travelRepository.create(travel)) {
                JOptionPane.showMessageDialog(null, "El viaje se ha creado correctamente");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear el viaje");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los datos del viaje o usuario son nulos");
        }
        return false;
    }
    
    public boolean updateTravel(Travel travel) {
        if (travel != null) {
            if (_travelRepository.update(travel)) {
                JOptionPane.showMessageDialog(null, "El viaje se ha actualizado correctamente");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el viaje");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El viaje a actualizar es nulo");
        }
        return false;
    }
    
    public boolean removeTravel(int id) {
        if (_travelRepository.remove(id)) {
            JOptionPane.showMessageDialog(null, "El viaje se ha eliminado correctamente");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el viaje");
            return false;
        }
    }
}
