package formationAlten.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formationAlten.entity.Fournisseur;



public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

	List<Fournisseur>findByNom(String nom);
	
	@Query("select f from Fournisseur f left join fetch f.listeProduits where f.id=:id")
	Optional<Fournisseur>findByIdFetchListeProduits(@Param("id")Long id);


	
	
	

}
