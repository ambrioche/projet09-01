package formationAlten;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import formationAlten.config.JpaConfig;
import formationAlten.entity.Achat;
import formationAlten.entity.AchatKey;
import formationAlten.entity.Client;
import formationAlten.entity.Commande;
import formationAlten.entity.Produit;
import formationAlten.service.ClientService;
import formationAlten.service.CommandeService;
import formationAlten.service.ProduitService;


@SpringJUnitConfig(JpaConfig.class)
public class CommandeServiceTest {
	
	@Autowired
	private CommandeService commandeService;
	@Autowired
	private ProduitService  produitService;
	
	private ClientService clientService;
	
	
	@Test
	void test() {
		assertNotNull(commandeService);
	}
	
	@Test
	void insert() {
		//Client client = new Client("test", "test", null, "test", LocalDate.now(), Civilite.MME); en attente de la fin du projet pour pouvoir int�grer un vrai client
		Client client = clientService.getById(5L) ;
		
		Commande c = new Commande(LocalDate.now(), client);
		
		//Produit produit = new Produit("testprod", "testdesc", 33.33); 
		Produit produit = produitService.getById(101L);
		AchatKey achatKey = new AchatKey(c, produit);
		Achat achat = new Achat(achatKey, 2);
		List<Achat> achats = new ArrayList<>();
		achats.add(achat);
		c.setAchats(achats);
		commandeService.create(c);
		assertNotNull(commandeService.getByIdCommandWithAchats(c.getNumero()));
	}
	
	
	
}
