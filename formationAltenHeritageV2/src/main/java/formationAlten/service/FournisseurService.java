package formationAlten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import formationAlten.entity.Adresse;
import formationAlten.entity.Fournisseur;
import formationAlten.exception.FournisseurException;
import formationAlten.exception.IdException;
import formationAlten.repository.FournisseurRepository;
import formationAlten.repository.ProduitRepository;

@Service
public class FournisseurService {

	@Autowired
	private FournisseurRepository fournisseurRepo;
	@Autowired
	private ProduitRepository produitRepo;

	public Fournisseur create(Fournisseur fournisseur) {
		checkFournisseurIsNull(fournisseur);


		return fournisseurRepo.save(fournisseur);
	}

	private void checkFournisseurIsNull(Fournisseur fournisseur) {
		if (fournisseur == null) {
			throw new FournisseurException("fournisseur null");
		}
	}

	public Fournisseur getById(Long id) {
		if (id == null) {
			throw new IdException();
		}
		return fournisseurRepo.findById(id).orElseThrow(IdException::new);
	}
	
	public Fournisseur getByIdWithProduits(Long id) {
		if (id == null) {
			throw new IdException();
		}
		return fournisseurRepo.findByIdFetchListeProduits(id).orElseThrow(() -> {
			throw new FournisseurException("fournisseurr inconnu");
		});
	}
	public void delete(Fournisseur fournisseur) {
		checkFournisseurIsNull(fournisseur);
		deleteById(fournisseur.getId());
	}

	public void delete(Long id) {
		deleteById(id);
	}

	private void deleteById(Long id) {
		Fournisseur fournisseur = getById(id);
		produitRepo.updateByFournisseurSetFournisseurToNull(fournisseur);
		fournisseurRepo.delete(fournisseur);
	}

	public List<Fournisseur> getAll() {
		return fournisseurRepo.findAll();
	}

	public Page<Fournisseur> getAll(Pageable pageable) {
		if (pageable == null) {
			throw new FournisseurException();
		}
		return fournisseurRepo.findAll(pageable);
	}

	public Page<Fournisseur> getNextPage(Page<Fournisseur> page) {
		if (page == null) {
			throw new FournisseurException();
		}
		return fournisseurRepo.findAll(page.nextOrLastPageable());
	}

	public Page<Fournisseur> getPrevious(Page<Fournisseur> page) {
		if (page == null) {
			throw new FournisseurException();
		}
		return fournisseurRepo.findAll(page.previousOrFirstPageable());
	}

	public Fournisseur update(Fournisseur fournisseur) {
		Fournisseur fournisseurEnBase = getById(fournisseur.getId());
		fournisseurEnBase.setNom(fournisseur.getNom() != null ? fournisseur.getNom() : fournisseurEnBase.getNom());
		fournisseurEnBase.setEmail(fournisseur.getEmail());
		if (fournisseur.getAdresse() != null) {
			fournisseurEnBase.setAdresse(
					new Adresse(
							fournisseur.getAdresse().getNumero(), 
							fournisseur.getAdresse().getRue(),
							fournisseur.getAdresse().getCodePostal(),
							fournisseur.getAdresse().getVille()));
		} else {
			fournisseurEnBase.setAdresse(null);

		}
		fournisseurEnBase.setContact(fournisseur.getContact());

		return fournisseurRepo.save(fournisseurEnBase);
	}

}
