package com.diginamic.Diginamic.controleurs;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.diginamic.Diginamic.exception.GestionExceptions;
import com.diginamic.Diginamic.model.Ville;
import com.diginamic.Diginamic.service.VilleService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @PostMapping
    public ResponseEntity<String> createVille(@RequestBody Ville ville) throws GestionExceptions {
        boolean result = villeService.insertVille(ville);
        if (!result) {
            return ResponseEntity.badRequest().body("Cette ville n'a pas été créée !");
        } else {
            return ResponseEntity.ok("La ville a bien été créée !");
        }
    }

    @GetMapping
    public ResponseEntity<String> getVilles() {
        List<Ville> result = villeService.getAllVilles();
        if (result.isEmpty()) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville !");
        }
        return ResponseEntity.ok("Succès !");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getVilleById(@PathVariable("id") Long id) {
        Ville result = villeService.getVilleById(id);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville avec cette id !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<String> getVilleByNom(@PathVariable("nom") String nom) {
        Ville result = villeService.getVilleByNom(nom);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville avec ce nom !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateVille(@RequestBody Ville ville) {
        boolean villeModifiee = villeService.updateVille(ville);
        if(villeModifiee) {
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Cette ville n'existe pas !");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVilleById(@PathVariable("id") Long id) {
        boolean villesupprimee = villeService.supprimerVille(id);
        if(villesupprimee) {
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Cette ville n'existe pas !");
        }
    }

    @GetMapping("/commencant/{nom}")
    public ResponseEntity<String> getVilleByNomCommencantPar(@PathVariable("nom") String nom) throws GestionExceptions {
        List<Ville> result = villeService.getVilleByNomStartingWith(nom);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville commençant par " + nom + " !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @GetMapping("/NbHabitant/{nbHabitants}")
    public ResponseEntity<String> getVilleByNbHabitantPlusGrand(@PathVariable("nbHabitants") int nbHabitants) throws GestionExceptions {
        List<Ville> result = villeService.getVilleNbHabitantsPlusGrandQue(nbHabitants);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville avec un nombre d'habitants plus grand que "+ nbHabitants + " !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @GetMapping("/nbHabitantsEntre/{nbHabitantsMin}/{nbHabitantsMax}")
    public ResponseEntity<String> getVilleByNbHabitantEntre(@PathVariable("nbHabitantsMin") int nbHabitantsMin, @PathVariable("nbHabitantsMax") int nbHabitantsMax) throws GestionExceptions {
        List<Ville> result = villeService.getVilleNbHabitantsEntre(nbHabitantsMin, nbHabitantsMax);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville avec un nombre d'habitants entre " + nbHabitantsMin + " et " + nbHabitantsMax + " !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @GetMapping("/departementNbHabitantsPlusGrand/{departementCode}/{nbHabitants}")
    public ResponseEntity<String> getVilleByDepartementEtNbHabitantsPlusGrandQue(@PathVariable("departementCode") String departementCode, @PathVariable("nbHabitants") int nbHabitants) throws GestionExceptions {
        List<Ville> result = villeService.getVilleByDepartementEtNbHabitantsPlusGrandQue(departementCode, nbHabitants);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville dans le département " + departementCode + " avec un nombre d'habitants plus grand que " + nbHabitants + " !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @GetMapping("/nbHabitantsDepartementEntre/{departementCode}/{nbHabitantsMin}/{nbHabitantsMax}")
    public ResponseEntity<String> getVilleByDepartementEtNbHabitantsEntre(@PathVariable("departementCode") String departementCode, @PathVariable("nbHabitantsMin") int nbHabitantsMin, @PathVariable("nbHabitantsMax") int nbHabitantsMax) throws GestionExceptions {
        List<Ville> result = villeService.getVilleByDepartementEtNbHabitantsEntre(departementCode, nbHabitantsMin, nbHabitantsMax);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville dans le département " + departementCode + " avec un nombre d'habitants entre " + nbHabitantsMin + " et " + nbHabitantsMax + " !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @GetMapping("/DepartementNbHabitants/{departementCode}/{size}")
    public ResponseEntity<String> getVilleByDepartementOrderByNbHabitantsDesc(@PathVariable("departementCode") String departementCode, @PathVariable("size") int size) {
        List<Ville> result = villeService.findVillesByDepartementOrderByNbHabitantsDesc(departementCode, size);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville dans le département " + departementCode + " !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }
    
    @GetMapping("/extraire")
    public void getFichierVille(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"villes.csv\"");
        List<Ville> villes = villeService.getAllVilles();
        PrintWriter writer = response.getWriter();
        writer.println("Nom de la ville,Nombre d'habitants,Nom du département,Code département");
        for (Ville ville : villes) {
            writer.println(ville.getNom() + "," +
                    ville.getNbHabitants() + "," +
                    ville.getDepartement().getNom()+ "," +
                    ville.getDepartement().getCode());
        }
    }
}