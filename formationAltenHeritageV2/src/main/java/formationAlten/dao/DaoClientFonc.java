package formationAlten.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import formationAlten.entity.Client;
import formationAlten.entity.Client;
import formationAlten.entity.Fournisseur;
import formationAlten.util.JpaContext;

@Repository
public class DaoClientFonc implements DaoClient {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insert(Client obj) {
		em.persist(obj);
	}
	
	@Override
	@Transactional
	public Client update(Client obj) {
		return em.merge(obj);
	}
	
	@Override
	@Transactional
	public void delete(Client obj) {	
		em.remove(em.merge(obj));
	}
	
	@Override
	@Transactional
	public void deleteByKey(Long key) {
		em.remove(em.find(Client.class, key));
	}
	
	@Override
	public Client findByKey(Long key) {		
		return em.find(Client.class, key);
	}
	
	@Override
	public List<Client> findAll() {
		return em.createQuery("from Client", Client.class).getResultList();		
	}


}
