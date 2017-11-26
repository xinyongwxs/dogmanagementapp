package com.ws.dog.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity  
@Table(name = "workingdog")  
@PrimaryKeyJoinColumn(name = "id") 
public class WorkingDog extends Dog {
	public int getTrainingLevel() {
		return trainingLevel;
	}
	public void setTrainingLevel(int trainingLevel) {
		this.trainingLevel = trainingLevel;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public boolean getReadyForWorking() {
		return readyForWorking;
	}
	public void setReadyForWorking(boolean readyForWorking) {
		this.readyForWorking = readyForWorking;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name = "traininglevel")
	private int trainingLevel;
	
	@Column(name = "trainer")
	private String trainer;
	
	@Column(name = "readyforworking")
	private boolean readyForWorking;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "category")
	private String category;
}
