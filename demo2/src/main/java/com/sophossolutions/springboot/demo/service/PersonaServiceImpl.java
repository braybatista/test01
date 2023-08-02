package com.sophossolutions.springboot.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sophossolutions.springboot.demo.model.Persona;
import com.sophossolutions.springboot.demo.repository.PersonaRepository;

@Service("personaServiceImpl")
@Transactional //etiqueta procesos de insert, update and delete
public class PersonaServiceImpl implements PersonaService{
	
	@Autowired //etiqueta clave para la injeccion esto causa que no nececitemos instanciar la clase
	@Qualifier("personaRepository")
	private PersonaRepository repositorio;

	@Override
	public List<Persona> listarPersonas() {
		return (List<Persona>) repositorio.findAll();
	}
	
	@Override
	public Persona encontrarPersonaPorId(int id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Persona encontrarPersona(Persona p) {
		return repositorio.findById(p.getId()).get();
	}

	@Override
	public Persona guardarPersona(Persona p) {
		return repositorio.save(p);
	}

	@Override
	public void eliminarPersona(int id) {
		repositorio.deleteById(id);
	}

}
