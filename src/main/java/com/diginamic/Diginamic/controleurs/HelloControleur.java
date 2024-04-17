package com.diginamic.Diginamic.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.diginamic.Diginamic.model.Ville;
import com.diginamic.Diginamic.service.HelloService;
import com.diginamic.Diginamic.service.VilleService;

@RestController
public class HelloControleur {
	
	@Autowired
    private HelloService helloService;
	
	@Autowired
	VilleService villeService;


    @GetMapping()
    public ModelAndView getVilles() {
        List<Ville> villes = villeService.getAllVilles();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("villes");
        modelAndView.addObject("villes", villes);

        return modelAndView;
    }
	
    @GetMapping("/bonjour")
    public String direBonjour() {
        return helloService.salutations();
    }
}
