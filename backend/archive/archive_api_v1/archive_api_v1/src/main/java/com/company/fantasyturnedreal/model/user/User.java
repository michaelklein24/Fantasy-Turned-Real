package com.company.fantasyturnedreal.model.user;

import com.company.fantasyturnedreal.model.contestant.Contestant;
import com.company.fantasyturnedreal.model.league.Answer;
import com.company.fantasyturnedreal.model.league.League;
import com.company.fantasyturnedreal.model.league.Question;
import com.company.fantasyturnedreal.model.league.Score;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = {"leagues", "roles", "answers"})
@ToString(exclude = {"roles", "answers", "leagues", "scores", "questions", "password"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference("user-password")
    private Password password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference("users-roles-managed")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference("user-answers")
    private Set<Answer> answers;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.DETACH)
    @JsonBackReference("league-users-back")
    private Set<League> leagues;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference("user-scores")
    private Set<Score> scores;

    @OneToMany(mappedBy = "submitter", cascade = CascadeType.DETACH)
    @JsonBackReference("user-questions")
    private Set<Question> questions;
}
