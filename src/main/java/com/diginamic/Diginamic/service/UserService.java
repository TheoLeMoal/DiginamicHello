package com.diginamic.Diginamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diginamic.Diginamic.config.Config;

@Service
public class UserService {
	private Config config;
	
	public UserService(Config config) {
		this.config = config;
		System.out.println("UserService Constructor" + config.toString());
	}

	/**
	 * @param config the config to set
	 */
	@Autowired
	public void setConfig(Config config) {
		this.config = config;
		System.out.println("UserService Constructor" + config.toString());
	}
	
	
}
