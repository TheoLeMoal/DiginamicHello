package com.diginamic.Diginamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diginamic.Diginamic.model.Ville;
import com.diginamic.Diginamic.repository.VilleRepository;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public List<Ville> extractVilles() {
        return villeRepository.findAll();
    }

    public Ville extractVille(Long id) {
        return villeRepository.findById(id);
    }

    public Ville extractVille(String nom) {
        return villeRepository.findByNom(nom);
    }

    public List<Ville> insertVille(Ville ville) {
        villeRepository.save(ville);
        return extractVilles();
    }

    public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
        Ville ville = villeRepository.findById(idVille).orElse(null);
        if (ville != null) {
            ville.setNom(villeModifiee.getNom());
            ville.setNbHabitants(villeModifiee.getNbHabitants());
            villeRepository.save(ville);
        }
        return extractVilles();
    }

    public List<Ville> supprimerVille(int idVille) {
        Ville ville = villeRepository.findById(idVille).orElse(null);
        if (ville != null) {
            villeRepository.delete(ville);
        }
        return extractVilles();
    }
}