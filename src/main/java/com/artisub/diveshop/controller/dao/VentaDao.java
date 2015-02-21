package com.artisub.diveshop.controller.dao;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.artisub.diveshop.model.Venta;

public class VentaDao implements IDAO<Venta> {

	EntityManagerFactorySingleton es = EntityManagerFactorySingleton.getInstance();
	EntityManager em = es.emf.createEntityManager();

	@SuppressWarnings("unchecked")
	@Override
	public List<Venta> findAll() {
		List<Venta> ventas;
		Query query = em.createNamedQuery("Venta.findAll");
		ventas = query.getResultList();
		em.clear();
		return ventas;
	}

	@Override
	public Venta findById(int id) {
		return em.find(Venta.class, id);
	}

	@Override
	public Venta create(Venta obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(Venta obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}

	@Override
	public Venta update(Venta obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public Venta getByQuery(String namedquery, Properties props)
			throws NoResultException {
		Venta venta = null;
		Query q = null;
		if(!props.isEmpty()){
			Object[] keys = props.keySet().toArray();
			q = em.createNamedQuery(namedquery, Venta.class).setMaxResults(1);
			q.setParameter((String) keys[0], props.getProperty((String) keys[0]));
			venta = (Venta) q.getSingleResult();
		}else{
			q = em.createNamedQuery(namedquery, Venta.class).setMaxResults(1);
			venta = (Venta) q.getSingleResult();
		}
		return venta;
	}
}
