package com.artisub.diveshop.controller.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	private static EntityManagerFactorySingleton es = new EntityManagerFactorySingleton();
	
	public EntityManagerFactory emf;
	
	private EntityManagerFactorySingleton(){
		this.emf = Persistence.createEntityManagerFactory("diveshop");
	}
	
	public static EntityManagerFactorySingleton getInstance(){
		return es;
	}

}
