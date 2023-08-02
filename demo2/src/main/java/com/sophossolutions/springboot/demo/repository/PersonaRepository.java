package com.sophossolutions.springboot.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sophossolutions.springboot.demo.model.Persona;

@Repository("personaRepository")
public interface PersonaRepository extends CrudRepository<Persona, Integer>{

}
