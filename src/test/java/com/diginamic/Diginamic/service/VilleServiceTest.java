package com.diginamic.Diginamic.service;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.diginamic.Diginamic.DiginamicApplication;
import com.diginamic.Diginamic.config.Config;
import com.diginamic.Diginamic.exception.GestionExceptions;
import com.diginamic.Diginamic.model.Departement;
import com.diginamic.Diginamic.model.Ville;
import com.diginamic.Diginamic.repository.DepartementRepository;
import com.diginamic.Diginamic.repository.VilleRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VilleServiceTest {

    @Autowired
    VilleService villeService;

    @Test
    public void testInsertExistingVille()  {
        Ville villeTest = new Ville("Paris",235125, new Departement("Paris","75"));
        assertDoesNotThrow(()->villeService.insertVille(villeTest));
    }

    @Test
    public void testInsertNonExistingVille() throws GestionExceptions {
        Ville villeTest = new Ville("Lyon",587430, new Departement("Rhones ","69"));
        assertDoesNotThrow(()->villeService.insertVille(villeTest));
    }
	
	@MockBean
	VilleRepository villeRepository;
	
	@MockBean
	DepartementRepository departementRepository;
	
	@Autowired
	MockMvc mockMVC;
	
	@Test
	public void testGetAllTowns() throws Exception{
		Ville v1 = new Ville("Paris",213333,new Departement("75", "Paris"));
		Ville v2 = new Ville("Marseille",873076,new Departement("13", "Bouches-Du-Rhone"));
		Ville v3 = new Ville("Lyon",522250,new Departement("69", "Rhone"));
		when(villeRepository.findAll()).thenReturn(List.of(v1, v2, v3));
		
		mockMVC.perform(MockMvcRequestBuilders.get("/villes")).andDo(print())
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().string(containsString("Marseille")));
	}	
}