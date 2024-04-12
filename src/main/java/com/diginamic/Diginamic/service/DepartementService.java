package com.diginamic.Diginamic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diginamic.Diginamic.exception.GestionExceptions;
import com.diginamic.Diginamic.model.Departement;
import com.diginamic.Diginamic.repository.DepartementRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    @Transactional
    public boolean insertDepartement(Departement departement) throws GestionExceptions {
        Optional<Departement> departementBdd = departementRepository.findByCode(departement.getCode());
        
        if (departement.getNom().length() < 3) {
            throw new GestionExceptions("Le nom du département doit contenir au minimum 3 lettres");
        }
        
        if (departement.getCode().length() < 2 || departement.getCode().length() > 3) {
            throw new GestionExceptions("Le code département doit être composé de 2 ou 3 caractères");
        }
        
        if(!departementBdd.isPresent()) {
            departementRepository.save(departement);
            return true;
        }
		return false;
    }

    @Transactional
    public List<Departement> getAllDepartements() {
        Iterable<Departement> departementsIterable = departementRepository.findAll();
        List<Departement> departements = new ArrayList<>();
        departementsIterable.forEach(departements::add);
        
        if(departements.isEmpty()) {
            return null;
        } else {
            return departements;
        }
    }

    @Transactional
    public Departement getDepartementByCode(String code) throws GestionExceptions {
        if (code.length() < 2 || code.length() > 3) {
            throw new GestionExceptions("Le code département doit être composé de 2 ou 3 caractères");
        }
        
        Optional<Departement> departement = departementRepository.findByCode(code);
        
        if(departement != null) {
            return departement.get();
        } else {
            return null;
        }
    }

    @Transactional
    public boolean updateDepartement(Departement departement) throws GestionExceptions {
        Optional<Departement> departementUpdate = departementRepository.findByCode(departement.getCode());
        
        if (departement.getNom().length() < 3) {
            throw new GestionExceptions("Le nom du département doit contenir au minimum 3 lettres");
        }
        
        if (departement.getCode().length() < 2 || departement.getCode().length() > 3) {
            throw new GestionExceptions("Le code département doit être composé de 2 ou 3 caractères");
        }
        
        if (departementUpdate.get() != null) {
            departementRepository.save(departement);
            return true;
        }
		return false;
    }

    @Transactional
    public boolean supprimerDepartement(String code) {
        Optional<Departement> departementDelete = departementRepository.findByCode(code);
        
        if (departementDelete.isPresent()) {
            departementRepository.delete(departementDelete.get());
            return true;
        }
		return false;
    }
    
    
}