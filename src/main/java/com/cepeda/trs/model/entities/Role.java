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
public class Role extends BaseEntity {

    public Role() {
        users = new ArrayList<>();
    }
    
    String name;
    Iterable<User> users;
}