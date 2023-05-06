package com.api.ejemplo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.microservicio.controller.MicroservicioController;
import com.api.microservicio.model.Usuario;

@SpringBootTest(classes = { com.api.microservicio.MicroservicioApplication.class })
class MicroservicioApplicationTests {

	@Autowired
	MicroservicioController microservicioController;
	
	@BeforeEach
	public void crearUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Ernesto");
		usuario.setApellido("Carrasco");
		usuario.setCorreoElectronico("correo@gmail.com");
		LocalDate fechaNacimiento = LocalDate.parse("1999-02-14");
		usuario.setFechaNacimiento(Date.valueOf(fechaNacimiento));
		microservicioController.crearUsuario(usuario);
	}

	@Test
	public void tests() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Joaquin");
		usuario.setApellido("Martinez");
		usuario.setCorreoElectronico("correo2@gmail.com");
		LocalDate fechaNacimiento = LocalDate.parse("1999-03-14");
		usuario.setFechaNacimiento(Date.valueOf(fechaNacimiento));
		microservicioController.crearUsuario(usuario);
		assertNotNull(microservicioController.obtenerUsuario("2"));
		assertNotNull(microservicioController.crearUsuario(usuario));
		usuario.setApellido("Mendez");
		assertNotNull(microservicioController.actualizarUsuario(usuario));
		assertNotNull(microservicioController.eliminarUsuario("2"));
	}

}
