package com.ws.dog.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity  
@Table(name = "petdog")  
@PrimaryKeyJoinColumn(name = "id") 
public class PetDog extends Dog {
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
	public String getApperance() {
		return apperance;
	}
	public void setApperance(String apperance) {
		this.apperance = apperance;
	}
	public boolean getReadyForSell() {
		return readyForSell;
	}
	public void setReadyForSell(boolean readyForSell) {
		this.readyForSell = readyForSell;
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
	public String getBreeder() {
		return breeder;
	}
	public void setBreeder(String breeder) {
		this.breeder = breeder;
	}
	
	@Column(name = "traininglevel")
	private int trainingLevel;
	
	@Column(name = "trainer")
	private String trainer;
	
	@Column(name = "apperance")
	private String apperance;
	
	@Column(name = "readyforsell")
	private boolean readyForSell;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "breeder")
	private String breeder;
}
