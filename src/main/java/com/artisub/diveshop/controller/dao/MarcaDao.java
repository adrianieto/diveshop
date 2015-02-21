package com.artisub.diveshop.controller.dao;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.artisub.diveshop.model.Marca;

public class MarcaDao implements IDAO<Marca> {

	EntityManagerFactorySingleton es = EntityManagerFactorySingleton.getInstance();
	EntityManager em = es.emf.createEntityManager();

	@SuppressWarnings("unchecked")
	@Override
	public List<Marca> findAll() {
		List<Marca> marcas;
		Query query = em.createNamedQuery("Marca.findAll");
		marcas = query.getResultList();
		em.clear();
		return marcas;
	}

	@Override
	public Marca findById(int id) {
		return em.find(Marca.class, id);
	}

	@Override
	public Marca create(Marca obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(Marca obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}

	@Override
	public Marca update(Marca obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public Marca getByQuery(String namedquery, Properties props)
			throws NoResultException {
		Marca marca = null;
		Query q = null;
		if(!props.isEmpty()){
			Object[] keys = props.keySet().toArray();
			q = em.createNamedQuery(namedquery, Marca.class).setMaxResults(1);
			q.setParameter((String) keys[0], props.getProperty((String) keys[0]));
			marca = (Marca) q.getSingleResult();
		}else{
			q = em.createNamedQuery(namedquery, Marca.class).setMaxResults(1);
			marca = (Marca) q.getSingleResult();
		}
		return marca;
	}
}
