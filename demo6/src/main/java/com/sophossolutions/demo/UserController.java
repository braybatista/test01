package com.sophossolutions.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="http://localhost:4200", maxAge=7200)
@RestController
@RequestMapping({"/users"})
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping
	public List<User> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{data}")
	public User findByUser(@PathVariable(value="data") String data) {
		System.out.println("[UserController] [findByUser]");
		String[] splitData = data.split("&&");
		System.out.println("data: " + data);
		System.out.println("url: " + splitData[0]);
		System.out.println("url: " + splitData[1]);
		System.out.println("url: " + splitData[2]);
		
		System.setProperty("dd", "xx");
		return service.findByUser(splitData[0], splitData[1], splitData[2]);
	}

	@PostMapping
	public User add(@RequestBody User p) {
		System.out.println(p.toString());
		return service.add(p);
	}

}
