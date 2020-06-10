package com.torodeporte.toroDeporteBackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//implements CommandLineRunner
@SpringBootApplication
public class ToroDeporteBackEndApplication implements CommandLineRunner {
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ToroDeporteBackEndApplication.class, args);
	}

	public void run(String... args) throws Exception {
		String password = "admintorodeporte";
		
		for (int i = 0; i < 4; i++) {
			String passwordEncriptada = passwordEncoder.encode(password);
			System.out.println(passwordEncriptada);
		}
	}

}
