package com.cepeda.trs.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@Entity
public class User extends BaseEntity<Integer> {
    
    private String username;
    private String email;
    private String password;
    
    private int roleId;
    
    @ManyToOne // Relación muchos a uno con Role
    private Role role;
}