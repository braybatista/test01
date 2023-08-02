package com.sophossolutions.springboot.demo.model;

import javax.persistence.*;

@Entity
@Table(name="persons")
public class Persona {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String date;
	
	@Column(name = "mail", nullable = false, length = 50, unique = true)
	private String email;
	
	//C = current, I = inactive
	@Column(nullable = false, length = 1, columnDefinition = "char(1) default 'V'")
	private char status;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", name=" + name + ", date=" + date + ", email=" + email + ", status=" + status
				+ "]";
	}

}
