package com.artisub.diveshop.controller.dao;

import java.util.List;
import java.util.Properties;

import javax.persistence.NoResultException;


public interface IDAO<T> {

	public List<T> findAll();
	public T findById(int id);
	public T create(T obj);
	public void delete(T obj);
	public T update(T obj);
	public T getByQuery(String namedquery, Properties props) throws NoResultException;
	
}
