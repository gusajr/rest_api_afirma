package com.api.ejemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.api.microservicio.dao.MicroservicioDao;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

	@Mock
    MicroservicioDao microservicioDao;
	
	// Test con Mockito
	@Test
	public void mockitoTest() {
		MicroservicioDao microservicioDao = Mockito.mock(MicroservicioDao.class);
		Mockito.when(microservicioDao.eliminarUsuario("10")).thenReturn(1);

		int respuesta = microservicioDao.eliminarUsuario("10");

		assertEquals(1, respuesta);
	}
}
