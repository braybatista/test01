package com.sophossolutions.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
	@Query(value = "select * from demo.users", nativeQuery = true)
	List<User> findAll();
	User findById(int id);
	@Query(value = "select * from demo.users where username = ?1 and password = ?2 and type = ?3", nativeQuery = true)
	User findByUser(String username, String password, String type);
	@SuppressWarnings("unchecked")
	User save(User p);
	void delete(User p);
}
