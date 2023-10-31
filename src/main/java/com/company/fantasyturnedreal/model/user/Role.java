package com.company.fantasyturnedreal.model.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
