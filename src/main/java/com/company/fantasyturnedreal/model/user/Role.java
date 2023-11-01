package com.company.fantasyturnedreal.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@Table(name = "roles")
@EqualsAndHashCode(exclude = {"users"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonBackReference("users-roles-back")
    private Set<User> users;
}
