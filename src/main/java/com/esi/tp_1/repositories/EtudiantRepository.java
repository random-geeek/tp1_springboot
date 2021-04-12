package com.esi.tp_1.repositories;

import com.esi.tp_1.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface EtudiantRepository  extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findEtudiantByNom(String n);
    List<Etudiant> findEtudiantByNomAndDateNAfter(String n, Date d);

    @Query("select e.nom from Etudiant  e where e.idEtudiant=:id")
    String findNameById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update Etudiant e  set e.dateN=:d where upper(e.nom) =upper(:nom)")
    int updateEtudiant(@Param("d") Date d, @Param("nom") String nom);


    // nb Etudiants By Formation
    @Query(value = "Select f.id_formation, count(*) from etudiant e  join formation f " +
            "on e.formation_id_formation=f.id_formation " +
            " group by f.id_formation ", nativeQuery = true)
    List<Object[]> nbEtudiantsByFormation();
}
