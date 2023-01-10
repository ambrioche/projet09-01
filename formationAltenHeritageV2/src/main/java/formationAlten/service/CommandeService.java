package formationAlten.service;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import formationAlten.entity.Commande;
import formationAlten.entity.Fournisseur;

import formationAlten.exception.CommandeException;
import formationAlten.exception.DateException;
import formationAlten.exception.IdException;
import formationAlten.repository.AchatRepository;
import formationAlten.repository.ClientRepository;
import formationAlten.repository.CommandeRepository;

@Service
public class CommandeService {
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private AchatRepository achatRepo;
	@Autowired
	private CommandeRepository commandeRepo;
//	@Autowired
//	private AchatJsonRepository achatJsonRepo;
	
	public Commande create(Commande commande) {
		checkCommandeIsNotNull(commande);
		if (commande.getClient() == null){
			throw new CommandeException("client vide");
		}
		if (commande.getAchats() == null) {
			throw new CommandeException("liste d'achats vide");
		}
		achatRepo.saveAll(commande.getAchats());
		
		return commandeRepo.save(commande);
	}
	
//	public CommandeJson createJson(CommandeJson commandeJson) {
//		checkCommandeJsonIsNotNull(commandeJson);
//		if (commandeJson.getClient() == null){
//			throw new CommandeException("client vide");
//		}
//		if (commandeJson.getAchatsJson() == null) {
//			throw new CommandeException("liste d'achats vide");
//		}
//		achatJsonRepo.saveAll(commandeJson.getAchatsJson());
//		
//		return commandeRepo.save(commandeJson);
//	}
	
	
	public Commande getByNumero(Long numero) {
		if (numero == null) {
			throw new IdException();
		}

		return commandeRepo.findById(id).orElseThrow(CommandeException::new);

	}
	
	private void checkCommandeIsNotNull(Commande commande) {
		if (commande == null) {
			throw new CommandeException("commande null");
		}
	} 
	
//	private void checkCommandeJsonIsNotNull(CommandeJson commandeJson) {
//		if (commandeJson == null) {
//			throw new CommandeException("commandeJson null");
//		}
//	} 
	
	public Commande getByIdCommandWithAchats(Long id) {
		if(id == null) {
			throw new IdException();
		}
		return commandeRepo.findByCommandeFetchAchats(id).orElseThrow(() -> {
			throw new CommandeException("id de commande inconnu !");
		});
	}
	
	public Commande GetByDateCommandWithClient(LocalDate date) {
		if(date == null) {
			throw new DateException("la date est null !");
		}
		return commandeRepo.findByDateCommandeFetchClient(date).orElseThrow(() -> {
			throw new CommandeException("Client null pour la date donnée !");
		});
	}
	public void delete(Commande commade) {
		checkCommandeIsNotNull(commade);
		deleteById(commade.getid());
	}
	private void deleteById(Long id) {
		Commande commande = getByIdCommandWithAchats(id);
		achatRepo.deleteByAchatKey(commande);
		commandeRepo.delete(commande);
	}
	
	public void delete(Long id) {
		deleteById(id);
	}
	
	
	public List<Commande> getAll() {
		return commandeRepo.findAll();
	}

	public Page<Commande> getAll(Pageable pageable) {
		if (pageable == null) {
			throw new CommandeException();
		}
		return commandeRepo.findAll(pageable);
	}

	public Page<Commande> getNextPage(Page<Commande> page) {
		if (page == null) {
			throw new CommandeException();
		}
		return commandeRepo.findAll(page.nextOrLastPageable());
	}

	public Page<Commande> getPrevious(Page<Commande> page) {
		if (page == null) {
			throw new CommandeException();
		}
		return commandeRepo.findAll(page.previousOrFirstPageable());
	}
	
}
