package com.diginamic.Diginamic.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	private String name = "Config bean";
	
	public Config() {
		System.out.println("Configuration constructor");
	}
	
	public String getName() {
		return name;
	}
}
