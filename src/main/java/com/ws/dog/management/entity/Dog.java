package com.ws.dog.management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity  
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "DOG")
public class Dog {
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getKennelId() {
		return kennelId;
	}
	public void setKennelId(String kennelId) {
		this.kennelId = kennelId;
	}
	public Date getEpDate() {
		return epDate;
	}
	public void setEpDate(Date epDate) {
		this.epDate = epDate;
	}
	public String getChipId() {
		return chipId;
	}
	public void setChipId(String chipId) {
		this.chipId = chipId;
	}
	public String getEarId() {
		return earId;
	}
	public void setEarId(String earId) {
		this.earId = earId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "kennelid")
	private String kennelId;
	
	@Column(name = "epdate")
	private Date epDate;
	
	@Column(name = "chipid")
	private String chipId;
	
	@Column(name = "earid")
	private String earId;
	
	@Column(name = "companycode")
	private String companyCode;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "type")
	private String type;
}
