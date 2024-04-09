package com.diginamic.Diginamic.controleurs;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.Diginamic.model.Ville;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
	
	@GetMapping
	public List<Ville> villes() {
        return Arrays.asList(
                new Ville("Paris", 2175601),
                new Ville("Marseille", 855393),
                new Ville("Lyon", 513275),
                new Ville("Toulouse", 479553),
                new Ville("Nice", 342295)
            );
	}
}
