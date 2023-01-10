package formationAlten;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import formationAlten.config.JpaConfig;
import formationAlten.entity.Adresse;
import formationAlten.entity.Fournisseur;
import formationAlten.entity.Produit;
import formationAlten.exception.IdException;
import formationAlten.exception.ProduitException;
import formationAlten.service.ProduitService;

@SpringJUnitConfig(JpaConfig.class)
public class ProduitServiceTest {
	
	@Autowired
	private ProduitService produitSrv;
	
	@Test
	void test() {
		assertNotNull(produitSrv);
	}
	
	@Test
	public void insert() {
		Produit p= new Produit ("Ordinateur","Ram, taille, processeur", 1000.0, new Fournisseur("Mme laFournisseuse", "mmeFournisseuse@gmail.com",
				new Adresse("50", "Rue de la Tech", "33000", "Fournisseuse City"), 
				"contact Mme la Fournisseuse"));
		produitSrv.create(p);
		assertNotNull(produitSrv.getById(p.getId()));
	}
	
	@Test
	void findById(){
		assertThrows(IdException.class, ()->{
			produitSrv.getById(null);
		});
		assertThrows(ProduitException.class, ()->{
			produitSrv.getById(1L);
		});
	}

}
