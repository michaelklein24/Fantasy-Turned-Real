package com.kleintwins.ftr.user.model;

import com.kleintwins.ftr.user.code.GlobalRole;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "usr_user")
@Entity
@Getter
@Setter
@ToString()
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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