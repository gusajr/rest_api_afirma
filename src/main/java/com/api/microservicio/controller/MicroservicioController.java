package com.api.microservicio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.microservicio.dao.MicroservicioDao;
import com.api.microservicio.model.Paginacion;
import com.api.microservicio.model.Respuesta;
import com.api.microservicio.model.Usuario;

@RestController
public class MicroservicioController {
	
	@Autowired
	MicroservicioDao dao;

	// Recurso para estatus
	@GetMapping("/")
	public ResponseEntity<Object> obtenerEstatus(){
		Map<String,String> ok = new HashMap<String,String>();
		ok.put("applicationName", "microservicio");
		ok.put("estatus", "OK");
		return new ResponseEntity<Object>(ok, HttpStatus.OK);
	}
	
	
	// Recurso para crear usuario
	@PostMapping("/usuario")
	public ResponseEntity<Object> crearUsuario(@RequestBody Usuario usuario){
		Respuesta respuesta = new Respuesta();
		if(dao.crearUsuario(usuario)==1){
			respuesta.setCodigo(200);
			respuesta.setMensaje("Ejecutado correctamente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		}else{
			respuesta.setCodigo(500);
			respuesta.setMensaje("No se pudo insertar el nuevo usuario");
			return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Recurso para obtener usuarios
	@GetMapping("/usuarios")
	public ResponseEntity<Object> obtenerUsuarios(){
		return new ResponseEntity<Object>(dao.obtenerUsuarios(), HttpStatus.OK);
	}
	
	// Recurso para obtener usuarios
	@PostMapping("/usuariosPaginacion")
	public ResponseEntity<Object> obtenerUsuarios(Paginacion paginacion){
		return new ResponseEntity<Object>(dao.obtenerUsuariosPaginacion(paginacion), HttpStatus.OK);
	}

	// Recurso para obtener usuario por id
	@GetMapping("/usuario/{idUsuario}")
	public String obtenerUsuario(@PathVariable (required = true) String idUsuario){
		return dao.obtenerUsuario(idUsuario).toString();
	}
	
	// Recurso para actualizar un usuario
	@PutMapping("/usuario/{idUsuario}")
	public ResponseEntity<Object> actualizarUsuario(@RequestBody Usuario usuario){
		Respuesta respuesta = new Respuesta();
		if(dao.actualizarUsuario(usuario)==1){
			respuesta.setCodigo(200);
			respuesta.setMensaje("Ejecutado correctamente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		}else{
			respuesta.setCodigo(500);
			respuesta.setMensaje("No se pudo actualizar el nuevo usuario");
			return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Recurso para eliminar un usuario
	@DeleteMapping("/usuario/{idUsuario}")
	public ResponseEntity<Object> eliminarUsuario(@PathVariable (required = true) String idUsuario){
		Respuesta respuesta = new Respuesta();
		if(dao.eliminarUsuario(idUsuario)==1){
			respuesta.setCodigo(200);
			respuesta.setMensaje("Ejecutado correctamente");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		}else{
			respuesta.setCodigo(500);
			respuesta.setMensaje("No se pudo eliminar el usuario");
			return new ResponseEntity<Object>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
