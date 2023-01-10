package eshop.restcontroller;

import java.time.LocalDate;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import eshop.util.Check;
import formationAlten.entity.Achat;
import formationAlten.entity.AchatKey;
import formationAlten.entity.Client;
import formationAlten.entity.Commande;
import formationAlten.entity.Produit;
import formationAlten.jsonview.Views;
import formationAlten.service.CommandeService;

@RestController
@RequestMapping("/api/commande")
public class CommandeRestController {
	
	@Autowired
	private CommandeService commandeService;
	
	
	@GetMapping("")
	@JsonView(Views.Common.class)
	public List<Commande> getAll(){
		return commandeService.getAll();
	}
	
	//Recherche par ID
	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public Commande getById(@PathVariable Long id) {
		return commandeService.getByIdCommandWithAchats(id);
	}
	
	//Recherche par Client
	@GetMapping("/{date}")
	@JsonView(Views.CommandeByClient.class)
	public Commande getByDate(@PathVariable LocalDate date) {
		return commandeService.GetByDateCommandWithClient(date);
	}
	
	//post Création
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	@JsonView(Views.CommandeWithAchats.class)
	public Commande create(@Valid @RequestBody Commande commande, Client client,Produit produit,AchatKey achatKey,Achat achat, BindingResult br) {
		Check.checkBindingResulHasError(br);
		
//		Client client = clientService.getById(5L) ;
//		
//		Commande c = new Commande(LocalDate.now(), client);
//		
//		//Produit produit = new Produit("testprod", "testdesc", 33.33); 
//		Produit produit = produitService.getById(101L);
//		AchatKey achatKey = new AchatKey(c, produit);
//		Achat achat = new Achat(achatKey, 2);
//		List<Achat> achats = new ArrayList<>();
//		achats.add(achat);
//		c.setAchats(achats);
//		commandeService.create(c);
		
		
		return commandeService.create(commande);
	}
	
	//Delete Commande
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		commandeService.delete(id);
	}
	
	
}
