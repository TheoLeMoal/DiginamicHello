package com.diginamic.Diginamic.controleurs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diginamic.Diginamic.model.Ville;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
	
    private static List<Ville> listeVilles = new ArrayList<Ville>();
    
    static {
    	listeVilles.add(new Ville("Paris", 2175601));
    	listeVilles.add(new Ville("Marseille", 855393));
    	listeVilles.add(new Ville("Lyon", 513275));
    	listeVilles.add(new Ville("Toulouse", 479553));
    	listeVilles.add(new Ville("Nice", 342295));
    }

	
    @GetMapping
    public List<Ville> getListeVilles() {
        return listeVilles;
    }
	
    @PostMapping
    public ResponseEntity<String> ajouterVille(@RequestBody Ville nouvelleVille) {
        if (villeExistante(nouvelleVille)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ville existe déjà");
        }
        
        listeVilles.add(nouvelleVille);
        return ResponseEntity.status(HttpStatus.OK).body("Ville insérée avec succès");
    }

    private boolean villeExistante(Ville nouvelleVille) {
        for (Ville ville : listeVilles) {
            if (ville.getNom().equalsIgnoreCase(nouvelleVille.getNom())) {
                return true;
            }
        }
        return false;
    }
}
