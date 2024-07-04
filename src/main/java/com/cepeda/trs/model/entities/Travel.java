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
public class Travel extends BaseEntity {
    String codeTravel;
    String destination;
    LocalDate travelDate;
    LocalDate returnDate;
    int avaliablPlaces;
    float price;
    
    int agencyId;
    Agency agency;
}
