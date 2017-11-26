package com.ws.dog.management.repository.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;

public class DogCommonDao<T> {
	
	protected Class<T> dogClass;
	
	@SuppressWarnings("unchecked")
	public List<T> getLimitItems(Integer limit, Integer offset, Map<String, Object> filter,
			String orderByName, String sortMethod, EntityManager em) {
		dogClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String[] classNameArray = dogClass.getName().split("\\.");
		String className = "";
		if (classNameArray.length > 0) {
			className = classNameArray[classNameArray.length - 1];
		} else {
			return null;
		}
		
		List<T> result = null;
		
		String queryStr = "select s from " + className + " s";
		if (!filter.equals(null)) {
			queryStr = queryStr + " where ";
			int currentIndex = 0;
			List<Object> args = new ArrayList<Object>();
			for (Entry<String, Object> entry: filter.entrySet()) {
				args.add(entry.getKey());
				args.add(entry.getKey());
				if (currentIndex < filter.size() - 1) {
					queryStr = queryStr + "%s=:%s and ";
					currentIndex++;
				} else {
					queryStr = queryStr + "%s=:%s";
				}
			}
			queryStr = String.format(queryStr, args.toArray());
		}
		
		if (!orderByName.equals(null) && !sortMethod.equals(null) && (sortMethod.equals("asc") || sortMethod.equals("desc"))) {
			queryStr = String.format(queryStr + " order by %s %s", orderByName, sortMethod);
		}
		
		TypedQuery<T> rq = em.createQuery(queryStr, dogClass);
		for (Entry<String, Object> entry: filter.entrySet()) {
			rq.setParameter(entry.getKey(), entry.getValue());
		}
		
		if (!limit.equals(null)) {
			rq = rq.setMaxResults(limit);
		}
		
		if (!offset.equals(null)) {
			rq = rq.setFirstResult(offset);
		}
		
		result = rq.getResultList();
		
		return result;
	}
}
