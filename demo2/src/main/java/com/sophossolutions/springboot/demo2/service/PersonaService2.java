package com.sophossolutions.springboot.demo2.service;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.sophossolutions.springboot.demo.model.Persona;

public interface PersonaService2 {
	public Iterable<Persona> findAll();
	public Page<Persona> findAllById(Pageable pag);
	public Optional<Persona> findById(int id);
	public Persona save(Persona p);
	public void deleteById(int id);
}
