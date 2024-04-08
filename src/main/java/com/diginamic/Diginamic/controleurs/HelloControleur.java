package com.diginamic.Diginamic.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.Diginamic.service.HelloService;

@RestController
public class HelloControleur {
	
	@Autowired
    private HelloService helloService;


	@GetMapping
	public String direHello() {
		return "Hello";
	}
	
    @GetMapping("/bonjour")
    public String direBonjour() {
        return helloService.salutations();
    }
}
