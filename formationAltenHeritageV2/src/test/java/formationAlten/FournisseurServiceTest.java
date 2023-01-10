package formationAlten;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import formationAlten.config.JpaConfig;
import formationAlten.entity.Adresse;
import formationAlten.entity.Fournisseur;
import formationAlten.exception.FournisseurException;
import formationAlten.exception.IdException;
import formationAlten.service.FournisseurService;

@SpringJUnitConfig(JpaConfig.class)
public class FournisseurServiceTest {

	@Autowired
	private FournisseurService fournisseurSrv;

	@Test
	void test() {
		assertNotNull(fournisseurSrv);
	}

	@Test
	public void insert() {
		Fournisseur f = new Fournisseur("Mr leFournisseur", "mrFournisseur@gmail.com",
				new Adresse("10", "Rue des Matériau", "33000", "Fournisseur City"), 
				"contact Mr Fournisseur");
		fournisseurSrv.create(f);
		assertNotNull(fournisseurSrv.getById(f.getId()));
	}

	@Test
	void findById() {
		assertThrows(IdException.class, ()->{
			fournisseurSrv.getById(null);
		});
		assertThrows(FournisseurException.class, ()->{
			fournisseurSrv.getById(1L);
		});
	}
}
