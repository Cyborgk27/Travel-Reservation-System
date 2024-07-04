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
public class Agency extends BaseEntity {

    public Agency() {
        travels = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    String name;

    int userId;
    User user;

    Iterable<Travel> travels;
    Iterable<Reservation> reservations;
}
