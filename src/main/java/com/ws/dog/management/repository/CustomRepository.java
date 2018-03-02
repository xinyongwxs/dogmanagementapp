package com.ws.dog.management.repository;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
public interface CustomRepository<T> {
	List<T> getLimitItems(Integer limit, Integer offset, Map<String, Object> filter, String orderByName, String sortMethod);
}
