package formationAlten.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import formationAlten.entity.Commande;
import formationAlten.exception.CommandeException;
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
	
	public void create(Commande commande) {
		checkCommandeIsNotNull(commande);
		/*if (commande.getClient() == null){
			throw new CommandeException("prenom vide");
		}*/
		if (commande.getAchats() == null) {
			throw new CommandeException("liste d'achats vide");
		}
		commandeRepo.save(commande);
		achatRepo.saveAll(commande.getAchats());
	}
	
	private void checkCommandeIsNotNull(Commande commande) {
		if (commande == null) {
			throw new CommandeException("commande null");
		}
	} 
	
	public Commande getByIdCommandWithAchats(Long id) {
		if(id == null) {
			throw new IdException();
		}
		return commandeRepo.findByCommandeFetchAchats(id).orElseThrow(() -> {
			throw new CommandeException("id de commande inconnu !");
		});
	}
	
	private void deleteById(Long id) {
		Commande commande = getByIdCommandWithAchats(id);
		achatRepo.updateByAchatKeySetAchatKeyToNull(commande);
		commandeRepo.delete(commande);
	}
	
	public void delete(Long id) {
		deleteById(id);
	}
	
}
