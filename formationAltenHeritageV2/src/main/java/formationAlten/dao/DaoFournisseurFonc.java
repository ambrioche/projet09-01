package formationAlten.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import formationAlten.entity.Fournisseur;
import formationAlten.util.JpaContext;

@Repository
public class DaoFournisseurFonc implements DaoFournisseur {
	
	@PersistenceContext
	private EntityManager em;

	public void insert(Fournisseur obj) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(obj);
		et.commit();
		em.close();
	}

	public Fournisseur update(Fournisseur obj) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		Fournisseur Fournisseur = null;
		et.begin();
		Fournisseur = em.merge(obj);
		et.commit();
		em.close();
		return Fournisseur;
	}

	public void delete(Fournisseur obj) {
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
		em.remove(em.find(Fournisseur.class, key));
		et.commit();
		em.close();

	}

	public Fournisseur findByKey(Long key) {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		Fournisseur Fournisseur = null;
		et.begin();
		Fournisseur = em.find(Fournisseur.class, key);
		et.commit();
		em.close();
		return Fournisseur;
	}

	public List<Fournisseur> findAll() {
		EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
		TypedQuery<Fournisseur> query = em.createQuery("from Fournisseur", Fournisseur.class);
		List<Fournisseur> Fournisseurs = query.getResultList();
		em.close();
		return Fournisseurs;
	}
}
