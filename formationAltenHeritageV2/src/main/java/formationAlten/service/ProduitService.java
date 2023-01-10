package formationAlten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import formationAlten.entity.Adresse;
import formationAlten.entity.Fournisseur;
import formationAlten.entity.Produit;
import formationAlten.exception.IdException;
import formationAlten.exception.ProduitException;
import formationAlten.repository.FournisseurRepository;
import formationAlten.repository.ProduitRepository;

@Service
public class ProduitService {
	@Autowired
	private FournisseurRepository fournisseurRepo;
	@Autowired
	private ProduitRepository produitRepo;

	public void create(Produit produit) {
		if (produit.getLibelle() == null || produit.getLibelle().isEmpty()) {
			throw new ProduitException("Libelle vide");
		}
		if (produit.getDescription() == null || produit.getDescription().isEmpty()) {
			throw new ProduitException("Description vide");
		}
		produitRepo.save(produit);
	}

	public void checkProduitIsNull(Produit produit) {
		if (produit == null) {
			throw new ProduitException("Produit null");
		}
	}

	public Produit getById(Long id) {
		if (id == null) {
			throw new IdException();
		}
		return produitRepo.findById(id).orElseThrow(() -> {
			throw new ProduitException("Produit inconnu");
		});
	}

	public void delete(Produit produit) {
		checkProduitIsNull(produit);
		produit = getById(produit.getId());

	}

	public void delete(Long id) {
		deleteById(id);
	}

	private void deleteById(Long id) {
		Produit produit = getById(id);
		fournisseurRepo.findByIdFetchListeProduits(id);
		produitRepo.delete(produit);

	}

	public List<Produit> getAll() {
		return produitRepo.findAll();
	}

	public Page<Produit> getNextPage(Page<Produit> page) {
		if (page == null) {
			throw new ProduitException();
		}
		return produitRepo.findAll(page.nextOrLastPageable());
	}

	public Page<Produit> getPrevioustPage(Page<Produit> page) {
		if (page == null) {
			throw new ProduitException();
		}
		return produitRepo.findAll(page.previousOrFirstPageable());
	}

	public Produit update(Produit produit) {
		Produit produitEnBase = getById(produit.getId());
		produitEnBase.setLibelle(produit.getLibelle());
		produitEnBase.setDescription(produit.getDescription());
		produitEnBase.setPrix(produit.getPrix());
		if (produit.getFournisseur() != null) {
			produitEnBase.setFournisseur(
					new Fournisseur(
							produit.getFournisseur().getNom(),
							produit.getFournisseur().getEmail(),
							produit.getFournisseur().getAdresse(),
							produit.getFournisseur().getContact()));

					} else {
							produitEnBase.setFournisseur(null);
					}
		return produitRepo.save(produitEnBase);

	}
}
