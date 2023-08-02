package com.sophossolutions.demo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;

@Service
public class PersonServiceImp implements PersonService{
	
	@Autowired
	private PersonRepository repository;

	@Override
	public List<Person> findAll() {
		return repository.findAll();
	}

	@Override
	public Person findById(int id) {
		return repository.findById(id);
	}

	@Override
	public Person add(Person p) {
		return repository.save(p);
	}

	@Override
	public Person edit(Person p) {
		return repository.save(p);
	}

	@Override
	@Transactional
	public Person delete(int id) {
		Person p = repository.findById(id);
		if(p != null) {
			//this is a custom way to execute querys
			//you need to add @transactional in service impl methos and @Modifying in Repository
			//repository.deleteBooks(id);
			
			//this is a way to execute an stored procedure only declare in repository as @Procedure or with @Query
			//the best way to execute Stored procedures is with the return type Optional<?>
			//System.out.println(repository.findBooks(1));
			
			//System.out.println(repository.findPersonSP(id));
			//System.out.println(repository.findAllSP());
			
			//System.out.println(repository.deletePersonSP(id).get());
			
			repository.delete(p);
		}
		return p;
	}

	@Override
	public List<Person> findAllByIdBetween(int id1, int id2) {
		return repository.findAllByIdBetween(id1, id2, Sort.by(Sort.Direction.DESC, "id"));
	}
	
	@Override
	public List<Person> findAllByDateBetween(String date1, String date2) {
		return repository.findAllByDateBetween(date1, date2, Sort.by(Sort.Direction.DESC, "date"));
	}
	
	@Override
	public List<Person> findAllByDateBetween(Date date1, Date date2) {
		return repository.findAllByDateBetween(date1, date2, Sort.by(Sort.Direction.DESC, "date"));
	}
}
