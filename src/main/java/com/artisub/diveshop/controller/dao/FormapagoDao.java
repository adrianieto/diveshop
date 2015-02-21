package com.artisub.diveshop.controller.dao;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.artisub.diveshop.model.Formapago;

public class FormapagoDao implements IDAO<Formapago> {

	EntityManagerFactorySingleton es = EntityManagerFactorySingleton.getInstance();
	EntityManager em = es.emf.createEntityManager();

	@SuppressWarnings("unchecked")
	@Override
	public List<Formapago> findAll() {
		List<Formapago> formasdepago;
		Query query = em.createNamedQuery("Formapago.findAll");
		formasdepago = query.getResultList();
		em.clear();
		return formasdepago;
	}

	@Override
	public Formapago findById(int id) {
		return em.find(Formapago.class, id);
	}

	@Override
	public Formapago create(Formapago obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(Formapago obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}

	@Override
	public Formapago update(Formapago obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public Formapago getByQuery(String namedquery, Properties props)
			throws NoResultException {
		Formapago formapago = null;
		Query q = null;
		if(!props.isEmpty()){
			Object[] keys = props.keySet().toArray();
			q = em.createNamedQuery(namedquery, Formapago.class).setMaxResults(1);
			q.setParameter((String) keys[0], props.getProperty((String) keys[0]));
			formapago = (Formapago) q.getSingleResult();
		}else{
			q = em.createNamedQuery(namedquery, Formapago.class).setMaxResults(1);
			formapago = (Formapago) q.getSingleResult();
		}
		return formapago;
	}

}
