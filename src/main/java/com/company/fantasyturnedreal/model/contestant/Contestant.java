package com.company.fantasyturnedreal.model.contestant;

import com.company.fantasyturnedreal.model.season.Season;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "contestant")
@ToString(exclude = {"seasons", "statuses", "socialAccounts"})
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
    private String originCity;
    private String originState;
    private Date birthday;
    private String nickName;
    @Column(length = 1000)
    private String biography;
    @Lob
    private Byte[] image;

    @ManyToMany
    @JoinColumn(
            name = "season_contestant",
            referencedColumnName = "season_id"
    )
    private Set<Season> seasons = new HashSet<>();

    @OneToMany(mappedBy = "contestant", cascade = CascadeType.ALL)
    @JsonBackReference("contestant-statuses")
    private Set<ContestantStatus> statuses = new HashSet<>();

    @OneToMany(mappedBy = "contestant", cascade = CascadeType.ALL)
    @JsonManagedReference("contestant-socialAccounts")
    private Set<SocialAccount> socialAccounts = new HashSet<>();

}
