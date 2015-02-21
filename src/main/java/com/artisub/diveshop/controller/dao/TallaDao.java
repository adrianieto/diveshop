package com.artisub.diveshop.controller.dao;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.artisub.diveshop.model.Articulo;
import com.artisub.diveshop.model.Talla;

public class TallaDao implements IDAO<Talla>{

	EntityManagerFactorySingleton es = EntityManagerFactorySingleton.getInstance();
	EntityManager em = es.emf.createEntityManager();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Talla> findAll() {
		List<Talla> tallas;
		Query query = em.createNamedQuery("Talla.findAll");
		tallas = query.getResultList();
		em.clear();
		return tallas;
	}

	@Override
	public Talla findById(int id) {
		return em.find(Talla.class, id);
	}

	@Override
	public Talla create(Talla obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(Talla obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}

	@Override
	public Talla update(Talla obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public Talla getByQuery(String namedquery, Properties props) throws NoResultException {
		Talla talla = null;
		Query q = null;
		if(!props.isEmpty()){
			Object[] keys = props.keySet().toArray();
			q = em.createNamedQuery(namedquery, Talla.class).setMaxResults(1);
			q.setParameter((String) keys[0], props.getProperty((String) keys[0]));
			talla = (Talla) q.getSingleResult();
		}else{
			q = em.createNamedQuery(namedquery, Articulo.class).setMaxResults(1);
			talla = (Talla) q.getSingleResult();
		}
		return talla;
	}

}
