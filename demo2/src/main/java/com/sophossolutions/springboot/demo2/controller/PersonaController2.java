package com.sophossolutions.springboot.demo2.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.sophossolutions.springboot.demo2.service.PersonaServicioImpl2;

@RestController
@RequestMapping("/personas2")
@CrossOrigin(origins="*")
public class PersonaController2 {
	
	@Autowired
	@Qualifier("personaServicioImpl2")
	private PersonaServicioImpl2 servicio;
	
	@GetMapping
	public Iterable<Persona> findAll(){
		return servicio.findAll();
	}
	
	@GetMapping
	public List<Persona> readAll(){
		List<Persona> persons = StreamSupport
				.stream(servicio.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return persons;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable(value="id") int id){
		Optional<Persona> oPersona = servicio.findById(id);
		
		return oPersona.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(oPersona.get()) 
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Persona p) {
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(p));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Persona p, @PathVariable(value="id") int id) {
		Optional<Persona> oPersona = servicio.findById(id);
		
		if(oPersona.isPresent()) {
			//BeanUtils.copyProperties(p, oPersona.get()); //utilidad para copiar propiedades de una entidad a otra
			oPersona.get().setName(p.getName());
			oPersona.get().setName(p.getDate());
			oPersona.get().setName(p.getEmail());
		}
		
		return oPersona.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(servicio.save(oPersona.get())) 
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") int id) {
		Optional<Persona> oPersona = servicio.findById(id);
		
		if(oPersona.isPresent()) {
			servicio.deleteById(id);
		}
		
		return oPersona.isPresent() ? ResponseEntity.ok(oPersona.get()) : ResponseEntity.notFound().build();		
	}
	
	@SuppressWarnings("unused")
	private Boolean validatePerson(int id){		
		return servicio.findById(id).isPresent();
	}
}
