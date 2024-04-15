package com.diginamic.Diginamic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import com.diginamic.Diginamic.model.Departement;
import com.diginamic.Diginamic.model.Ville;
import com.diginamic.Diginamic.service.DepartementService;
import com.diginamic.Diginamic.service.VilleService;

@SpringBootApplication
public class MyCommandLineRunner implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(DiginamicApplication.class);
		sa.setWebApplicationType(WebApplicationType.NONE);
		sa.run(args);
	}

	@Autowired
	VilleService villeService;
	
	@Autowired
	DepartementService departementService;
	
	@Override
	public void run(String... args) throws Exception{
        ClassPathResource fichier = new ClassPathResource("cities.csv");
        BufferedReader reader = new BufferedReader(new FileReader(fichier.getFile()));
        String ligne;
        boolean sautDeLigne = true;
        while((ligne = reader.readLine()) != null) {
            if (sautDeLigne) {
                sautDeLigne = false;
                continue;
            }
            String[] donnees = ligne.split((","));
            String nomVille = donnees[3];
            
            Departement departement = departementService.getDepartementByCode(donnees[7]);
            if (departement == null) {
            	departement = new Departement(donnees[6], donnees[7]);
			}
            
            departementService.insertDepartement(departement);
            
            // Générer un nombre aléatoire entre 0 et 1000000 pour le nombre d'habitants
            Random random = new Random();
            int randomNumber = random.nextInt(1000001);
            
            if (villeService.getVilleByNom(nomVille) == null) {
                Ville ville = new Ville(nomVille,randomNumber ,departement);
                villeService.insertVille(ville);
			}
        }
        reader.close();
	}
}
