package com.cepeda.trs.model.entities;

import jakarta.persistence.*;
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
@MappedSuperclass
public abstract class BaseEntity<T> {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected int state;
}