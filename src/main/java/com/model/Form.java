package com.model;

import org.springframework.data.annotation.Id;

public class Form {

	 @Id
	    public String id;
	String name;
	String email;
	String favc;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFavc() {
		return favc;
	}
	public Form(String name, String email, String favc) {
		super();
		this.name = name;
		this.email = email;
		this.favc = favc;
	}
	public Form() {
		// TODO Auto-generated constructor stub
	}
	public void setFavc(String favc) {
		this.favc = favc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Customer[id=%s, name='%s', email='%s',favc='%s']",
                id, name, email,favc);
    }
}
