package com.sophossolutions.demo;

import java.util.List;

public interface UserService {
	List<User> findAll();
	User findById(int id);
	User findByUser(String username, String password, String type);
	User add(User p);
	User edit(User p);
	User delete(int id);
}
