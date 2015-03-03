package com.artisub.diveshop.controller.dao;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.artisub.diveshop.model.Producto;

public class ProductoDao implements IDAO<Producto> {
	
	EntityManagerFactorySingleton es = EntityManagerFactorySingleton.getInstance();
	EntityManager em = es.emf.createEntityManager();

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> findAll() {
		List<Producto> producto;
		Query query = em.createNamedQuery("Producto.findAll");
		producto = query.getResultList();
		em.clear();
		return producto;
	}

	@Override
	public Producto findById(int id) {
		return em.find(Producto.class, id);
	}

	@Override
	public Producto create(Producto obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(Producto obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}

	@Override
	public Producto update(Producto obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public Producto getByQuery(String namedquery, Properties props)
			throws NoResultException {
		Producto producto = null;
		Query q = null;
		if(props != null){
			Object[] keys = props.keySet().toArray();
			q = em.createNamedQuery(namedquery, Producto.class).setMaxResults(1);
			q.setParameter((String) keys[0], props.getProperty((String) keys[0]));
			producto = (Producto) q.getSingleResult();
		}else{
			q = em.createNamedQuery(namedquery, Producto.class).setMaxResults(1);
			producto = (Producto) q.getSingleResult();
		}
	
		return producto;
	}

}
