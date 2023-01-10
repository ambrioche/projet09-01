package formationAlten.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import formationAlten.entity.Commande;

import formationAlten.util.JpaContext;

public class DaoCommandeFonc implements DaoCommande {

	public void insert(Commande obj) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(obj);
		et.commit();
		em.close();
	}

	public Commande update(Commande obj) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		Commande Commande = null;
		et.begin();
		Commande = em.merge(obj);
		et.commit();
		em.close();
		return Commande;
	}

	public void delete(Commande obj) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(em.merge(obj));
		et.commit();
		em.close();

	}

	public void deleteByKey(Long key) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(em.find(Commande.class, key));
		et.commit();
		em.close();

	}

	public Commande findByKey(Long key) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		Commande Commande = null;
		et.begin();
		Commande = em.find(Commande.class, key);
		et.commit();
		em.close();
		return Commande;
	}

	public List<Commande> findAll() {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		TypedQuery<Commande> query = em.createQuery("from Commande", Commande.class);
		List<Commande> Commandes = query.getResultList();
		em.close();
		return Commandes;
	}


}
