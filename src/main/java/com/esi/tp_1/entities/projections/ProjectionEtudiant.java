package com.esi.tp_1.entities.projections;

import com.esi.tp_1.entities.Etudiant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="pr1",types={Etudiant.class})
public interface ProjectionEtudiant {

    public String getNom();
    /*ou
        @Value("#{target.nom}")
        public String jj();*/


    @Value("#{target.formation.nom}")
    public String getNomFormation();
}
