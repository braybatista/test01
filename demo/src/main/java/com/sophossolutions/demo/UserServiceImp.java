package com.sophossolutions.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User findById(int id) {
		return repository.findById(id);
	}
	
	@Override
	public User findByUser(String username, String password, String type) {
		System.out.println("[UserServiceImp] [findByUser]");
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		System.out.println("type: " + type);
		System.out.println(System.getProperty("dd"));
		String ambiente = System.getProperty("dde") == null ? "is null" : System.getProperty("dde");
		System.out.println(ambiente);
		return repository.findByUser(username, password, type);
	}

	@Override
	public User add(User p) {
		return repository.save(p);
	}

	@Override
	public User edit(User p) {
		return null;
	}

	@Override
	public User delete(int id) {
		return null;
	}

}
