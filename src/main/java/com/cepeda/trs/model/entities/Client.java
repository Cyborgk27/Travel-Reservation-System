package com.cepeda.trs.model.entities;

import java.util.ArrayList;
import lombok.*;

/**
 *
 * @author CyborgK27
 */
@Getter
@Setter
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Client extends BaseEntity {

    public Client() {
        reservations = new ArrayList<>();
        travels = new ArrayList<>();
    }

    String cellphone;

    int userId;
    User user;

    Iterable<Reservation> reservations;
    Iterable<Travel> travels;
}
