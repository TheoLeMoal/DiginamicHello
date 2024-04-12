package com.diginamic.Diginamic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.diginamic.Diginamic.exception.GestionExceptions;
import com.diginamic.Diginamic.model.Departement;
import com.diginamic.Diginamic.model.Ville;
import com.diginamic.Diginamic.repository.VilleRepository;

@SpringBootTest
@ActiveProfiles("test")
public class VilleRepositoryTest {
	@Autowired
	VilleService villeService;
	
	@Autowired
	VilleRepository villeRepository;
	
	@Test
	public void testFindByNameStartingWith() throws GestionExceptions {
		Ville ville = new Ville("Paris", 4500, new Departement("Paris", "93"));
		villeService.insertVille(ville);
		List<Ville> villes = villeRepository.findByNomStartingWith("Par");
		assertEquals(villes.size(), 1);
		assertNotNull(villes.get(0));
		assertEquals("Paris", villes.get(0).getNom());
	}
	
}
