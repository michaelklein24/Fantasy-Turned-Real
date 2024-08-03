package com.ftr.api.user.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "usr_password_history")
public class PasswordModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passwordId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserModel userModel;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private Date expiryDate;

}
