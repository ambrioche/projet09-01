package formationAlten.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formationAlten.entity.Civilite;
import formationAlten.entity.Client;
import formationAlten.entity.Commande;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	
	
	
	
	List<Client> findByNomContaining(String nom);
	
	List<Client> findByEmailContaining(String email);
	
	
	@Query("select c from Client c left join fetch c.commandes where c.id=:id")
	Optional<Client> findByIdFetchCommandes(@Param("id") Long id);

}
