package com.artisub.diveshop.controller.dao;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.artisub.diveshop.model.Login;

public class LoginDao implements IDAO<Login> {
	
	EntityManagerFactorySingleton es = EntityManagerFactorySingleton.getInstance();
	EntityManager em = es.emf.createEntityManager();

	@SuppressWarnings("unchecked")
	@Override
	public List<Login> findAll() {
		List<Login> logins;
		Query query = em.createNamedQuery("Login.findAll");
		logins = query.getResultList();
		em.clear();
		return logins;
	}

	@Override
	public Login findById(int id) {
		return em.find(Login.class, id);
	}

	@Override
	public Login create(Login obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(Login obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}

	@Override
	public Login update(Login obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public Login getByQuery(String namedquery, Properties props)
			throws NoResultException {
		Login login = null;
		Query q = null;
		if(!props.isEmpty()){
			Object[] keys = props.keySet().toArray();
			q = em.createNamedQuery(namedquery, Login.class).setMaxResults(1);
			q.setParameter((String) keys[0], props.getProperty((String) keys[0]));
			login = (Login) q.getSingleResult();
		}else{
			q = em.createNamedQuery(namedquery, Login.class).setMaxResults(1);
			login = (Login) q.getSingleResult();
		}
		return login;
	}

}
