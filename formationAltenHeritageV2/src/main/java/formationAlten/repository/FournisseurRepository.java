package formationAlten.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formationAlten.entity.Fournisseur;



public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

	List<Fournisseur>findByNom(String nom);
//	Optional<Fournisseur> findById(Long id);
	List<Fournisseur>findByContact(String contact);
	List<Fournisseur>findByEmail(String email);
	
	@Query("select f from Fournisseur f left join fetch f.listeProduits where f.id=:id")
	Optional<Fournisseur>findByIdFetchListeProduits(@Param("id")Long id);

//	@Query("select f from Fournisseur f left join fetch f.contact where f.id=:id")
//	Optional<Fournisseur>findByIdFetchContact(@Param("id")Long id);
//	
//
//	@Query("select f from Fournisseur f left join fetch f.email where f.id=:id")
//	Optional<Fournisseur>findByIdFetchEmail(@Param("id")Long id);
//
//
//
//	
	
	

}
