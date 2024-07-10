package com.cepeda.trs.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
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
@Entity
@Table(name = "Travels")
public class Travel extends BaseEntity<Integer> implements Serializable {

    public Travel() {
    }
    
    String codeTravel;
    String destination;
    LocalDate travelDate;
    LocalDate returnDate;
    int avaliablePlaces;
    float price;
    
    @ManyToOne
    User user;
}
