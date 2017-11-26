package com.ws.dog.management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity  
@Table(name = "breedingdog")  
@PrimaryKeyJoinColumn(name = "id") 
public class BreedingDog extends Dog {
	public Date getMatingDate() {
		return matingDate;
	}
	public void setMatingDate(Date matingDate) {
		this.matingDate = matingDate;
	}
	public Date getPuppyBirthday() {
		return puppyBirthday;
	}
	public void setPuppyBirthday(Date puppyBirthday) {
		this.puppyBirthday = puppyBirthday;
	}
	public String getFeeder() {
		return feeder;
	}
	public void setFeeder(String feeder) {
		this.feeder = feeder;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean getReadyForSell() {
		return readyForSell;
	}
	public void setReadyForSell(boolean readyForSell) {
		this.readyForSell = readyForSell;
	}
	public int getPuppyAccount() {
		return puppyAccount;
	}
	public void setPuppyAccount(int puppyAccount) {
		this.puppyAccount = puppyAccount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "matingdate")
	private Date matingDate;
	
	@Column(name = "puppybirthday")
	private Date puppyBirthday;
	
	@Column(name = "feeder")
	private String feeder;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "readyforsell")
	private boolean readyForSell;
	
	@Column(name = "puppyaccount")
	private int puppyAccount;
	
	@Column(name = "category")
	private String category;
}
