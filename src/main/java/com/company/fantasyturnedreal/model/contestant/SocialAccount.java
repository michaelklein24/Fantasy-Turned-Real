package com.company.fantasyturnedreal.model.contestant;

import com.company.fantasyturnedreal.enums.SocialPlatform;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "social_account")
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long socialAccountId;
    private String handle;
    @Enumerated(EnumType.STRING)
    private SocialPlatform application;

    @ManyToOne
    @JoinColumn(name = "contestant_id")
    private Contestant contestant;
}
