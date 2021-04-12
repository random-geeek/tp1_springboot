package com.esi.tp_1.Apis;

import com.esi.tp_1.entities.Etudiant;
import com.esi.tp_1.repositories.EtudiantRepository;
import com.esi.tp_1.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    FormationRepository formationRepository;

    @GetMapping("/etudiant/all")      //GET http://localhost:8081/api/etudiant/all
    public List<Etudiant> getEtudiants()
    {
        return     etudiantRepository.findAll();
    }


    @GetMapping("/etudiant/{id}")       //GET http://localhost:8081/api/etudiant/3
    public Etudiant getEtudiantById(@PathVariable("id") Long id){
        return etudiantRepository.findById(id).get();
    }

    @GetMapping("/etudiant")     //GET http://localhost:8081/api/etudiant?id=1
    public Etudiant getEtudiantById2(@RequestParam("id") Long id) {
        return etudiantRepository.findById(id).get();
    }


    @PostMapping("/etudiant/")    //POST "http://localhost:8081/api/etudiant/?idf=2
    public Etudiant CreateEtudinat(@RequestBody Etudiant etudiant, @RequestParam("idf") Long idFormation )
    {
        etudiant.setFormation(formationRepository.findById(idFormation).get());
        return  etudiantRepository.save(etudiant);
    }

    @PostMapping("/etudiant2")  //POST "http://localhost:8081/api/etudiant2
    public Etudiant createEtudiant2(@RequestBody Map<String, Object> payload) {
        Etudiant e=new Etudiant();
        e.setNom(payload.get("nom").toString());
        e.setDateN(Date.valueOf(payload.get("dateN").toString()));

        Long idf= Long.valueOf(payload.get("idformation").toString());
        e.setFormation(formationRepository.findById(idf).get());

        return etudiantRepository.save(e);
    }

    @PutMapping("/etudiant/{id}")
    public Etudiant updateEtudiant(@PathVariable(value="id") Long ide, @RequestBody Map<String, Object>  payload){

        if( etudiantRepository.findById(ide).isPresent()){

            Etudiant etudiant=new Etudiant();
            etudiant.setIdEtudiant(ide);

            if(payload.get("nom")!=null) etudiant.setNom(payload.get("nom").toString());


            if(payload.get("dateN")!=null) etudiant.setDateN(Date.valueOf(payload.get("dateN").toString()));


            if(payload.get("idformation")!=null) {
                Long idf= Long.valueOf(payload.get("idformation").toString());
                etudiant.setFormation(formationRepository.findById(idf).get());
            }


            return etudiantRepository.save(etudiant);
        }
        return null;
    }


    @PatchMapping("/etudiant/{id}")
    public Etudiant updateEtudiant2(@PathVariable(value="id") Long ide, @RequestBody Map<String, Object>  payload){

        if( etudiantRepository.findById(ide).isPresent()){

            Etudiant etudiant=etudiantRepository.findById(ide).get();

             if(payload.get("nom")!=null) etudiant.setNom(payload.get("nom").toString());
             if(payload.get("dateN")!=null) etudiant.setDateN(Date.valueOf(payload.get("dateN").toString()));
             if(payload.get("idformation")!=null) {
                 Long idf= Long.valueOf(payload.get("idformation").toString());
                 etudiant.setFormation(formationRepository.findById(idf).get());
                }
            return etudiantRepository.save(etudiant);
        }
        return null;
    }

}
