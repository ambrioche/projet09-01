package formationAlten.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import formationAlten.entity.Produit;
import formationAlten.util.JpaContext;

@Repository
public class DaoProduitFonc implements DaoProduit {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insert(Produit obj) {		
		em.persist(obj);
	}
	
	@Override
	@Transactional
	public Produit update(Produit obj) {
		return em.merge(obj);
	}
	
	@Override
	@Transactional
	public void delete(Produit obj) {
		em.remove(em.merge(obj));
	}
	
	@Override
	@Transactional
	public void deleteByKey(Long key) {
		em.remove(em.find(Produit.class, key));
	}
	
	@Override
	@Transactional
	public Produit findByKey(Long key) {
		return em.find(Produit.class, key);
	}
	
	@Override
	public List<Produit> findAll() {
		return em.createQuery("from Produit", Produit.class).getResultList();
	}
	
	@Override
	public List<Produit> findByLibelle(String libelle){				
		return em.createQuery("select p from Produit p where p.libelle like :libelle",Produit.class).getResultList();
	}
}
