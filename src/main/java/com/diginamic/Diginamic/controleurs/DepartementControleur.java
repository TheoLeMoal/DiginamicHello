package com.diginamic.Diginamic.controleurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.Diginamic.exception.GestionExceptions;
import com.diginamic.Diginamic.model.Departement;
import com.diginamic.Diginamic.service.DepartementService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    @Autowired
    private DepartementService departementService;

    @PostMapping
    public ResponseEntity<String> createDepartement(@RequestBody Departement departement) throws GestionExceptions {
        departementService.insertDepartement(departement);
        return ResponseEntity.ok("Le département a bien été créée !");
    }

    @GetMapping
    public ResponseEntity<String> getVilles() {
        List<Departement> result = departementService.getAllDepartements();
        if (result.isEmpty()) {
            return ResponseEntity.badRequest().body("Il n'y a aucune département !");
        }
        return ResponseEntity.ok("Succès !");
    }

    @PutMapping
    public ResponseEntity<String> updateVille(@RequestBody Departement departement) throws GestionExceptions {
        boolean departementModifiee = departementService.updateDepartement(departement);
        if(departementModifiee) {
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Ce département n'existe pas !");
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteDepartement(@PathVariable("code") String code) {
        boolean departementSupprimee = departementService.supprimerDepartement(code);
        if(departementSupprimee) {
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Ce département n'existe pas !");
        }
    }
    
    @GetMapping("/extraire")
    public void getFichierDepartements(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"departements.csv\"");
        List<Departement> departements = departementService.getAllDepartements();
        PrintWriter writer = response.getWriter();
        writer.println("Code département,Nom du département");
        for (Departement departement : departements) {
            writer.println(departement.getCode() + "," +
                    departement.getNom());
        }
    }
}