package com.kleintwins.ftr.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "usr_password_history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String passwordId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private UserModel userModel;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column(nullable = false)
    private LocalDate expiryDate;

    public static PasswordModel of(String encodedPassword, UserModel userModel) {
        return PasswordModel.builder()
                .encodedPassword(encodedPassword)
                .userModel(userModel)
                .active(true)
                .createdDate(LocalDate.now())
                .expiryDate(LocalDate.now().plusDays(90))
                .build();
    }
}
