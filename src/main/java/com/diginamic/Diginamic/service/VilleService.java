package com.diginamic.Diginamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.diginamic.Diginamic.exception.GestionExceptions;
import com.diginamic.Diginamic.model.Departement;
import com.diginamic.Diginamic.model.Ville;
import com.diginamic.Diginamic.repository.VilleRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    @PostConstruct
    public void init() {
        villeRepository.save(new Ville("Paris",2133111, new Departement("Paris","75")));
        villeRepository.save(new Ville("Marseille", 873076, new Departement("Bouches-du-Rhône", "13")));
        villeRepository.save(new Ville("Lyon", 522250, new Departement("Rhône","69")));
    }

    @Transactional
    public boolean insertVille(Ville ville) throws GestionExceptions {
        if(ville.getNbHabitants() < 10) {
            throw new GestionExceptions("Une Ville doit avoir au minimum 10 habitants.");
        }
        
        if(ville.getNom().length() < 2) {
            throw new GestionExceptions("Le nom de la ville doit avoir au minimum 2 caractères");
        }
        
        if(ville.getDepartement().getCode().length() < 2 || ville.getDepartement().getCode().length() > 3) {
            throw new GestionExceptions("Le code département doit être composé de 2 ou 3 caractères");
        }
        
        if (ville.getDepartement().getNom().length() < 3) {
            throw new GestionExceptions("Le nom du département doit contenir au minimum 3 lettres");
        }
        
        Optional<Ville> villeBdd = villeRepository.findByNomAndDepartement(ville.getNom(), ville.getDepartement());
        
        if(villeBdd.get() == null) {
            villeRepository.save(ville);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public List<Ville> getAllVilles() {
        Iterable<Ville> villesIterable = villeRepository.findAll();
        List<Ville> villes = new ArrayList<>();
        villesIterable.forEach(villes::add);
        if(villes.isEmpty()) {
            return null;
        } else {
            return villes;
        }
    }

    @Transactional
    public Ville getVilleById(@Param("id") Long id) {
        Optional<Ville> ville = villeRepository.findById(id);
        if(ville.isPresent()) {
            return ville.get();
        } else {
            return null;
        }
    }

    @Transactional
    public Ville getVilleByNom(@Param("nom") String nom) {
        Optional<Ville> ville = villeRepository.findByNom(nom);
        if(ville.isPresent()) {
            return ville.get();
        } else {
            return null;
        }
    }

    @Transactional
    public boolean updateVille(Ville ville) {
        Optional<Ville> villeUpdate = villeRepository.findByNomAndDepartement(ville.getNom(), ville.getDepartement());
        if (villeUpdate.get() != null) {
            villeRepository.save(ville);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean supprimerVille(Long id) {
        Optional<Ville> villeDelete = villeRepository.findById(id);
        if (villeDelete.isPresent()) {
            villeRepository.delete(villeDelete.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public List<Ville> getVilleByNomStartingWith(String nom) throws GestionExceptions {
        List<Ville> villes = villeRepository.findByNomStartingWith(nom);
        if (villes.isEmpty()) {
        	throw new GestionExceptions("Aucune ville dont le nom commence par "+nom+" n’a été trouvée");
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> getVilleNbHabitantsPlusGrandQue(int nbHabitantsMin) throws GestionExceptions{
        List<Ville> villes = villeRepository.findByNbHabitantsGreaterThan(nbHabitantsMin);
        if (villes.isEmpty()) {
        	throw new GestionExceptions("Aucune ville n’a une population supérieure à " + nbHabitantsMin);
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> getVilleNbHabitantsEntre(int nbHabitantsMin, int nbHabitantsMax) throws GestionExceptions {
        List<Ville> villes = villeRepository.findByNbHabitantsBetween(nbHabitantsMin, nbHabitantsMax);
        if (villes.isEmpty()) {
        	throw new GestionExceptions("Aucune ville n’a une population comprise entre " + nbHabitantsMin + " et " + nbHabitantsMax);
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> getVilleByDepartementEtNbHabitantsPlusGrandQue(String departementCode, int nbHabitants) throws GestionExceptions {
        List<Ville> villes = villeRepository.findByDepartementCodeAndNbHabitantsGreaterThan(departementCode, nbHabitants);
        if (villes.isEmpty()) {
        	throw new GestionExceptions(" Aucune ville n’a une population supérieure à " + nbHabitants + " dans le département " + departementCode);
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> getVilleByDepartementEtNbHabitantsEntre(String departementCode, int nbHabitantsMin, int nbHabitantsMax) throws GestionExceptions {
        List<Ville> villes = villeRepository.findByDepartementCodeAndNbHabitantsBetween(departementCode, nbHabitantsMin, nbHabitantsMax);
        if (villes.isEmpty()) {
        	throw new GestionExceptions("Aucune ville n’a une population comprise entre "+nbHabitantsMin+" et "+nbHabitantsMax+" dans le département "+departementCode);
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> findVillesByDepartementOrderByNbHabitantsDesc(String departementCode, int size) {
        List<Ville> villes = villeRepository.findVillesByDepartementCodeOrderByNbHabitantsDesc(departementCode, Pageable.ofSize(size)).getContent();
        if (villes.isEmpty()) {
            return null;
        } else {
            return villes;
        }
    }
}