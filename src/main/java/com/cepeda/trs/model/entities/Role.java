package com.cepeda.trs.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@Entity
public class Role extends BaseEntity<Integer> {

    // Constructor por defecto para JPA
    public Role() {
        users = new ArrayList<>();
    }
    
    private String name;
    
    @OneToMany(mappedBy = "role") // Ejemplo de mapeo de la relación uno a muchos con User
    private List<User> users;
}
