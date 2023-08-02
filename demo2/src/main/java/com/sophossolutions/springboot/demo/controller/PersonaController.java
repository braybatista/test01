package com.sophossolutions.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophossolutions.springboot.demo.model.Persona;
import com.sophossolutions.springboot.demo.repository.GetIDRepository;
import com.sophossolutions.springboot.demo.service.PersonaServiceImpl;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins="*") //etiqueta que habilita quienes pueden consumir este API
public class PersonaController {
	
	@Autowired //etiqueta clave para la injeccion esto causa que no nececitemos instanciar la clase
	@Qualifier("personaServiceImpl") //anotacion para la injeccion se debe poner el mismo nombre de la etiqueta de la clase
	private PersonaServiceImpl servicio;
	
	@Autowired //etiqueta clave para la injeccion esto causa que no nececitemos instanciar la clase
	@Qualifier("getIDRepository") //anotacion para la injeccion se debe poner el mismo nombre de la etiqueta de la clase
	private GetIDRepository getIDService;
	
	@GetMapping
	public List<Persona> listarPersonas(){
		return servicio.listarPersonas();
	}
	
	@GetMapping("/{id}")
	public Persona encontrarPersonas(@PathVariable(value="id") int id){
		return servicio.encontrarPersonaPorId(id);
	}
	
	@PostMapping
	public Persona agregarPersona(@RequestBody Persona p) {
		return servicio.guardarPersona(p);
	}
	
	@PutMapping
	public Persona editarPersona(@RequestBody Persona p) {
		return servicio.guardarPersona(p);
	}
	
	@DeleteMapping("/{id}")
	public Persona deletePersona(@PathVariable(value="id") int id) {
		Persona p = servicio.encontrarPersonaPorId(id);
		if(p != null) {
			servicio.eliminarPersona(id);
		}
		return p;
	}
}
