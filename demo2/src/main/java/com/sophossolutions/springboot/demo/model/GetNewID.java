package com.sophossolutions.springboot.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetNewID {
	
	@Id
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GetNewID [id=" + id + "]";
	}
	
}
