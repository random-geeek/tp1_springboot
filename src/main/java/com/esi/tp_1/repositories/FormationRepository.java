package com.esi.tp_1.repositories;

import com.esi.tp_1.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface FormationRepository  extends JpaRepository<Formation, Long> {

    boolean existsByNomContaining(String word);

}
