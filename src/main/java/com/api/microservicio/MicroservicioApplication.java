package com.api.microservicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class MicroservicioApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS usuario("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nombre VARCHAR(30) NOT NULL,"
				+ "apellido VARCHAR(30) NOT NULL,"
				+ "correoElectronico VARCHAR(80) NOT NULL,"
				+ "fechaNacimiento DATE NOT NULL"
				+ ");");
	}

}
