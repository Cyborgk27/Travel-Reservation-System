package com.cepeda.trs.model.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author CyborgK27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEntity {
    public int id;
    public int state;
}