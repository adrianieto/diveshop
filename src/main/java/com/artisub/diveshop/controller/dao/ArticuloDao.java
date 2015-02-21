package com.artisub.diveshop.controller.dao;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.artisub.diveshop.controller.dao.EntityManagerFactorySingleton;
import com.artisub.diveshop.model.Articulo;

public class ArticuloDao implements IDAO<Articulo> {

	EntityManagerFactorySingleton es = EntityManagerFactorySingleton.getInstance();
	EntityManager em = es.emf.createEntityManager();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findAll() {
		List<Articulo> articulos;
		Query query = em.createNamedQuery("Articulo.findAll");
		articulos = query.getResultList();
		em.clear();
		return articulos;
	}

	@Override
	public Articulo findById(int id) {
		return em.find(Articulo.class, id);
	}

	@Override
	public Articulo create(Articulo obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(Articulo obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}

	@Override
	public Articulo update(Articulo obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public Articulo getByQuery(String namedquery, Properties props)throws NoResultException {
		Articulo articulo = null;
		Query q = null;
		if(!props.isEmpty()){
			Object[] keys = props.keySet().toArray();
			q = em.createNamedQuery(namedquery, Articulo.class).setMaxResults(1);
			q.setParameter((String) keys[0], props.getProperty((String) keys[0]));
			articulo = (Articulo) q.getSingleResult();
		}else{
			q = em.createNamedQuery(namedquery, Articulo.class).setMaxResults(1);
			articulo = (Articulo) q.getSingleResult();
		}
		return articulo;
	}
	

}
