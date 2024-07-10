package com.cepeda.trs.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Users")
public class User extends BaseEntity<Integer> implements Serializable {
    public User() {
        travels = new ArrayList<>();
    }
    private String username;
    private String email;
    private String password;
    
    @ToString.Exclude
    @ManyToOne
    private Role role;
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Travel> travels;
//    @OneToMany
//    private Iterable<Reservation> reservations;
    
    
}