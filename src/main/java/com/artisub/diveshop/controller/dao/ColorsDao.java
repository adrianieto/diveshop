package com.artisub.diveshop.controller.dao;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.artisub.diveshop.model.Colors;

public class ColorsDao implements IDAO<Colors> {

	EntityManagerFactorySingleton es = EntityManagerFactorySingleton.getInstance();
	EntityManager em = es.emf.createEntityManager();

	@SuppressWarnings("unchecked")
	@Override
	public List<Colors> findAll() {
		List<Colors> marcas;
		Query query = em.createNamedQuery("Colors.findAll");
		marcas = query.getResultList();
		em.clear();
		return marcas;
	}

	@Override
	public Colors findById(int id) {
		return em.find(Colors.class, id);
	}

	@Override
	public Colors create(Colors obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(Colors obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}

	@Override
	public Colors update(Colors obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public Colors getByQuery(String namedquery, Properties props)
			throws NoResultException {
		Colors marca = null;
		Query q = null;
		if(!props.isEmpty()){
			Object[] keys = props.keySet().toArray();
			q = em.createNamedQuery(namedquery, Colors.class).setMaxResults(1);
			q.setParameter((String) keys[0], props.getProperty((String) keys[0]));
			marca = (Colors) q.getSingleResult();
		}else{
			q = em.createNamedQuery(namedquery, Colors.class).setMaxResults(1);
			marca = (Colors) q.getSingleResult();
		}
		return marca;
	}
}
