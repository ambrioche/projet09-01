package formationAlten.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formationAlten.entity.Client;
import formationAlten.entity.Commande;
	

public interface CommandeRepository extends JpaRepository<Commande, Long> {
	
	List<Commande> findByNumero(Long numero);
	List<Commande> findByDate(LocalDate date);
	List<Commande> findByClient(Client client);
	
	
	@Query("select c from Commande c left join fetch c.achats where c.id=:id") // pas de condition d'�galit� avec requ�te JPQL il automatise gr�ce � la relation One to Many et Many to One avec le mapped By
	Optional<Commande> findByCommandeFetchAchats(@Param("id")Long id);
	
	@Query("select c from Commande c left join fetch c.client where c.date=:date")
	Optional<Commande> findByDateCommandeFetchClient(@Param("date")LocalDate date);
	
	//@Query("select c from Commande c left join fetch c.client where c.date=:date")
	//Optional<Commande> findByClientCommandeFetchClient(@Param("date")LocalDate date);

	@Modifying
	@Transactional
	@Query("delete Commande c where c.client=:client")
	void deleteByCommande(@Param("client")Client client);

	@Modifying
	@Transactional
	@Query("update Commande c set c.client=null where c.client=:client")
	void updateByClient(@Param("client") Client client);
	
	
}
