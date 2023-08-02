package com.sophossolutions.springboot.demo2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sophossolutions.springboot.demo.model.Persona;
import com.sophossolutions.springboot.demo2.repository.PersonaRepository2;

@Service("personaServicioImpl2")
public class PersonaServicioImpl2 implements PersonaService2 {
	
	@Autowired
	@Qualifier("personaRepository2")
	private PersonaRepository2 repoPersona;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findAll() {
		return repoPersona.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Persona> findAllById(Pageable pag) {
		return repoPersona.findAll(pag);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> findById(int id) {
		return repoPersona.findById(id);
	}

	@Override
	@Transactional
	public Persona save(Persona p) {
		return repoPersona.save(p);
	}

	@Override
	@Transactional(readOnly = true)
	public void deleteById(int id) {
		repoPersona.deleteById(id);
	}

}
