package com.company.fantasyturnedreal.model.contestant;

import com.company.fantasyturnedreal.model.season.Season;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "contestant")
public class Contestant {
    @Id
    @GeneratedValue()
    private Long contestantId;
    @Column(length = 55)
    private String firstName;
    @Column(length = 55)
    private String lastName;
    @Column(length = 255)
    private String occupation;
    private String origin_city;
    private String origin_state;
    private String nickName;
    @Column(length = 1000)
    private String biography;
    @Lob
    private Byte[] image;

    @ManyToMany(mappedBy = "contestants", cascade = CascadeType.REMOVE)
    private Set<Season> seasons = new HashSet<>();

    @OneToMany(mappedBy = "contestant", cascade = CascadeType.ALL)
    @JsonManagedReference("contestant-statuses")
    private Set<ContestantStatus> statuses;

    @OneToMany(mappedBy = "contestant", cascade = CascadeType.ALL)
    @JsonManagedReference("contestant-socialAccounts")
    private Set<SocialAccount> socialAccounts;

}
