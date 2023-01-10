package formationAlten.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formationAlten.entity.Commande;
import formationAlten.entity.Fournisseur;
import formationAlten.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	List<Produit> findByLibelle(String libelle);

	List<Produit> findByLibelleContaining(String libelle);

	Page<Produit> findByLibelleContaining(String libelle, Pageable pageable);
	
	List<Produit> findByFournisseur(Fournisseur fournisseur);
	
//	@Modifying
//	@Transactional
//	@Query("update Produit p set p.listeProduits=null where p.listeProduits=:listeProduits")
//	void updateByListeProduitsSetListeProduitsToNull(@Param("listeProduits")Fournisseur fournisseur);
	
	@Modifying
	@Transactional
	@Query("update Produit p set p.fournisseur=null where p.fournisseur=:fournisseur")
	void updateByFournisseurSetFournisseurToNull(@Param("fournisseur")Fournisseur fournisseur);
	
	@Modifying
	@Transactional
	@Query("delete Produit p where p.fournisseur=: fournisseur")
	void deleteByFournisseur(@Param("fournisseur")Fournisseur fournisseur);
}
