package com.ws.dog.management.model;

import java.util.Map;

public class DogFilterModel {
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Map<String, Object> getFilter() {
		return filter;
	}
	public void setFilter(Map<String, Object> filter) {
		this.filter = filter;
	}
	public String getOrderByName() {
		return orderByName;
	}
	public void setOrderByName(String orderByName) {
		this.orderByName = orderByName;
	}
	public String getSortMethod() {
		return sortMethod;
	}
	public void setSortMethod(String sortMethod) {
		this.sortMethod = sortMethod;
	}
	
	private Integer limit;
	private Integer offset;
	private Map<String, Object> filter;
	private String orderByName;
	private String sortMethod;
}
