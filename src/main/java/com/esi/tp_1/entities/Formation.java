package com.esi.tp_1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormation;

    private String nom;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private List<Etudiant> lesEtudiants;
}
