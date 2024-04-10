package com.diginamic.Diginamic.controleurs;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.diginamic.Diginamic.model.Ville;
import com.diginamic.Diginamic.service.VilleService;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
	
    @Autowired
    private VilleService villeService;
	
    @GetMapping
    public List<Ville> getListeVilles() {
        return villeService.extractVilles();
    }
    
    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable Long id) {
    	return villeService.extractVille(id);
    }
    
    @GetMapping("/nom/{nom}")
    public Ville getVilleByNom(@PathVariable String nom) {
        return villeService.extractVille(nom);
    }
    
    @PutMapping
    public List<Ville> addVille(@RequestBody Ville ville) {
        return villeService.insertVille(ville);
    }

    @PostMapping("/{id}")
    public List<Ville> modifierVille(@PathVariable int id, @RequestBody Ville villeModifiee) {
        return villeService.modifierVille(id, villeModifiee);
    }
	
    @DeleteMapping("/{id}")
    public List<Ville> supprimerVille(@PathVariable int id) {
        return villeService.supprimerVille(id);
    }
    
}
