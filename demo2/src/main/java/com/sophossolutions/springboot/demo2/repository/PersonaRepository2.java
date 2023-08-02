package com.sophossolutions.springboot.demo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophossolutions.springboot.demo.model.Persona;

@Repository("personaRepository2")
public interface PersonaRepository2 extends JpaRepository<Persona, Integer>{
	
	List<Persona> findByName(String name);
	List<Persona> findByNameAndEmail(String name, String email);
	List<Persona> findByNameAndEmailAndDate(String name, String email, String date);
}
