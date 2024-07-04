package com.cepeda.trs.model.entities;

import java.time.LocalDate;
import lombok.*;

/**
 *
 * @author CyborgK27
 */
@Getter
@Setter
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Reservation extends BaseEntity {
    LocalDate reservationDate;
    int stateReservation;
    
    int clientId;
    Client client;
    
    int travelId;
    Travel travel;
    
    int agencyId;
    Agency agency;
}
