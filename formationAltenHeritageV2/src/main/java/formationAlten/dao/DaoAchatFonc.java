package formationAlten.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import formationAlten.entity.Achat;
import formationAlten.entity.AchatKey;

import formationAlten.util.JpaContext;

public class DaoAchatFonc implements DaoAchat {

	public void insert(Achat obj) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(obj);
		et.commit();
		em.close();
	}

	public Achat update(Achat obj) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		Achat Achat = null;
		et.begin();
		Achat = em.merge(obj);
		et.commit();
		em.close();
		return Achat;
	}

	public void delete(Achat obj) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(em.merge(obj));
		et.commit();
		em.close();

	}

	public void deleteByKey(AchatKey id) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(em.find(Achat.class, id));
		et.commit();
		em.close();

	}

	public Achat findByKey(AchatKey id) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		Achat Achat = null;
		et.begin();
		Achat = em.find(Achat.class, id);
		et.commit();
		em.close();
		return Achat;
	}

	public List<Achat> findAll() {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		TypedQuery<Achat> query = em.createQuery("from Achat", Achat.class);
		List<Achat> Achats = query.getResultList();
		em.close();
		return Achats;
	}


}
