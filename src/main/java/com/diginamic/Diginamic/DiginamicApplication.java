package com.diginamic.Diginamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.diginamic.Diginamic.config.Config;

@SpringBootApplication
public class DiginamicApplication {

	@Autowired
	Config config;
	
	public static void main(String[] args) {
		SpringApplication.run(DiginamicApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		System.out.println(config);
	}

}
