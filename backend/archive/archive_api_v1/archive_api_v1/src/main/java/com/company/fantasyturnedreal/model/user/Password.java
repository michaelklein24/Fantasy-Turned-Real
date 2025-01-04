package com.company.fantasyturnedreal.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "passwords")
public class Password {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long passwordId;
   private String encryptedPassword;
   @Temporal(TemporalType.TIMESTAMP)
   private Date lastChanged;

   @OneToOne
   @JoinColumn(name = "user_id")
   @JsonManagedReference("user-password")
   private User user;
}
