package com.sophossolutions.springboot.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sophossolutions.springboot.demo.model.GetNewID;

@Repository("getIDRepository")
public interface GetIDRepository extends CrudRepository<GetNewID, Integer>{
	@Query(value="select max(id) from personas", nativeQuery = true)
	GetNewID getID();
}
