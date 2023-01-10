package formationAlten.repository;

import java.util.Optional;
import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formationAlten.entity.Client;
import formationAlten.entity.Commande;
	

public interface CommandeRepository extends JpaRepository<Commande, Long> {
	
	@Query("select c from Commande c left join fetch c.achats where c.id=:id") // pas de condition d'égalité avec requête JPQL il automatise grâce à la relation One to Many et Many to One avec le mapped By
	Optional<Commande> findByCommandeFetchAchats(@Param("id")Long id);

	@Modifying
	@Transactional
	@Query("delete Commande c where c.client =: client")
	void deleteByCommade(@Param("client")Client client);

	@Modifying
	@Transactional
	@Query("update Commande c set c.client=null where c.client=:client")
	void updateByClient(@Param("client") Client client);
	
}
