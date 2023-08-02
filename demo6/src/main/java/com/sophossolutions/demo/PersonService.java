package com.sophossolutions.demo;

import java.util.Date;
import java.util.List;

public interface PersonService {
	List<Person> findAll();
	List<Person> findAllByIdBetween(int id1, int id2);
	List<Person> findAllByDateBetween(Date date1, Date date2);
	List<Person> findAllByDateBetween(String date1, String date2);
	Person findById(int id);
	Person add(Person p);
	Person edit(Person p);
	Person delete(int id);
}
