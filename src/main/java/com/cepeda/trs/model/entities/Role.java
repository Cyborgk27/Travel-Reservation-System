package com.cepeda.trs.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Roles")
public class Role extends BaseEntity<Integer> implements Serializable {

    // Constructor por defecto para JPA
    public Role() {
        users = new ArrayList<>();
    }
    
    private String name;
    
    @OneToMany(mappedBy = "role") // Ejemplo de mapeo de la relación uno a muchos con User
    private List<User> users;
}
