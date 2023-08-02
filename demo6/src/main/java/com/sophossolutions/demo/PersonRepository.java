package com.sophossolutions.demo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends Repository<Person, Integer>{
	List<Person> findAll();
		
	List<Person> findAllByIdBetween(int id1, int id2, Sort sort);
	
	List<Person> findAllByDateBetween(Date date1, Date date2, Sort sort);
	
	List<Person> findAllByDateBetween(String date1, String date2, Sort sort);
		
	Person findById(int id);
	
	Person save(Person p);
	
	void delete(Person p);
	
	void deleteById(int id);
	
	@Modifying
	@Query("delete from users p where p.id = :id")
	void deletePerson(@Param("id") int id);
	
	//@Procedure("TEST")
	@Query(value = "CALL TEST(:wid, 'S');", nativeQuery = true)
	Optional<Person> findPersonSP(@Param("wid") int wid);
	
	@Query(value = "CALL TEST(:wid, 'I');", nativeQuery = true)
	Optional<Object> deletePersonSP(@Param("wid") int wid);
	
	@Query(value = "CALL TEST(null, 'Q');", nativeQuery = true)
	List<Person> findAllSP();
}
