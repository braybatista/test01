package com.sophossolutions.springboot.demo.service;

import java.util.List;

import com.sophossolutions.springboot.demo.model.Persona;

public interface PersonaService {
	public abstract List<Persona> listarPersonas();
	
	public abstract Persona encontrarPersona(Persona p);
	
	public abstract Persona encontrarPersonaPorId(int id);
	
	public abstract Persona guardarPersona(Persona p);
	
	public abstract void eliminarPersona(int id);

}
