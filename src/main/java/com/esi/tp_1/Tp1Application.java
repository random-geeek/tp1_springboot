package com.esi.tp_1;

import com.esi.tp_1.entities.Etudiant;
import com.esi.tp_1.entities.Formation;
import com.esi.tp_1.repositories.EtudiantRepository;
import com.esi.tp_1.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.sql.Date;

@SpringBootApplication
public class Tp1Application implements CommandLineRunner {

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(Tp1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //repositoryRestConfiguration.exposeIdsFor(Formation.class, Etudiant.class);

        Formation f1= formationRepository.save(new Formation(null,"SpringBoot", null));
        Formation f2= formationRepository.save(new Formation(null,"MicroService", null));

        etudiantRepository.save(new Etudiant(null, "malki", Date.valueOf("2000-01-01"),f1));
        etudiantRepository.save(new Etudiant(null, "said", Date.valueOf("2001-01-01"),f1));
        etudiantRepository.save(new Etudiant(null, "karim", Date.valueOf("2002-01-01"),f2));

        formationRepository.findAll().forEach(System.out::println);


        etudiantRepository.findAll().forEach(System.out::println);

        etudiantRepository.updateEtudiant(new java.util.Date(),"malki");

        etudiantRepository.findAll().forEach(System.out::println);


        etudiantRepository.findEtudiantByNom("said").forEach(System.out::println);

        System.out.println(etudiantRepository.findNameById(1L));

        System.out.println(formationRepository.existsByNomContaining("Boot"));


        for(Object[] row:etudiantRepository.nbEtudiantsByFormation())
            System.out.println("formation_id:"+row[0]+"  nb_Etudiants:"+row[1]);


    }
}
