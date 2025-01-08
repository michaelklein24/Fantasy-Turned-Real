package com.kleintwins.ftr.auth.model;

import com.kleintwins.ftr.auth.code.GlobalRole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usr_user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private GlobalRole role = GlobalRole.USER;

    public UserModel(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserModel() {}
}