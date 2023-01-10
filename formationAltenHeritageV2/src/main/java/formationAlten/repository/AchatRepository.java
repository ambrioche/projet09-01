package formationAlten.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formationAlten.entity.Achat;
import formationAlten.entity.AchatKey;
import formationAlten.entity.Commande;

public interface AchatRepository extends JpaRepository<Achat, AchatKey> {
	
	@Modifying
	@Transactional
	@Query("update Achat a set a.id.commande=null where a.id.commande=:commande")
	void updateByAchatKeySetAchatKeyToNull(@Param("commande") Commande commande);

	@Modifying
	@Transactional
	@Query("delete Achat a where a.id.commande=:commande")
	void deleteByAchat(@Param("commande") Commande commande);

	

}
