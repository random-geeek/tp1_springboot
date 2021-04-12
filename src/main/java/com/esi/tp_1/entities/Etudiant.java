package com.esi.tp_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;

    //@Column(nullable = false)
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateN;

    @ManyToOne
    private Formation formation;
}
