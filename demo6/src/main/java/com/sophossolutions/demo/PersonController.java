package com.sophossolutions.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200", maxAge=7200)
@RestController
@RequestMapping({"/persons"})
@SpringBootApplication
public class PersonController {
	
	@Autowired
	PersonService service;
	
	@GetMapping
	public List<Person> findAll(){
		//return service.findAllByDateBetween(new Date(17, 1, 1), new Date());
		//return service.findAllByDateBetween("2020-01-01 00:00:00", "2022-12-31 00:00:00");
		//return service.findAllByIdBetween(1, 100);
		return service.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public Person findById(@PathVariable("id") int id) {
		return service.findById(id);
	}

	@PostMapping
	public Person add(@RequestBody Person p) {
		System.out.println("person: " + p.toString());
		return service.add(p);
	}

	@PutMapping("/{id}")
	public Person edit(@PathVariable("id") int id, @RequestBody Person p) {
		return service.edit(p);
	}

	@DeleteMapping("/{id}")
	public Person delete(@PathVariable("id") int id) {
		return service.delete(id);
	}
}
