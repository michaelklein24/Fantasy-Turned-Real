package com.ftr.api.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usr_role")
public class GlobalRoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserModel userModel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalRole role;

    public GlobalRoleModel(UserModel userModel) {
        this.userModel = userModel;
        this.role = GlobalRole.USER;
    }

    public GlobalRoleModel() {
    }
}
