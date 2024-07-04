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
@Builder(access = AccessLevel.PUBLIC)
public class User extends BaseEntity {
    String username;
    String email;
    String password;
    
    int roleId;
    Role role;
}
