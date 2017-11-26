package com.ws.dog.management.entity;


public class User {

	private String id;
	
	private String name;
	
	private int age;
	
	private String position;
	
	private String gender;
	
    public User(String id, String name, int age, String position, String gender){
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.gender = gender;
    }

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
