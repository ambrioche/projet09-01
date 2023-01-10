package eshop.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import eshop.util.Check;
import formationAlten.entity.Fournisseur;
import formationAlten.entity.Produit;
import formationAlten.jsonviews.ProduitsViews;
import formationAlten.service.ProduitService;

@RestController
@RequestMapping("/api/produit")
public class ProduitRestController {
	
	@Autowired
	private ProduitService produitService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	@JsonView(ProduitsViews.Common.class)
	private Produit create(@Valid @RequestBody Produit produit, BindingResult br) {
		Check.checkBindingResulHasError(br);
		return produitService.create(produit);
	}
	
	@PutMapping("/{id}")
	@JsonView(ProduitsViews.Common.class)
	public Produit update(@Valid @RequestBody Produit produit, BindingResult br, @PathVariable Long id) {
		Check.checkBindingResulHasError(br);
		produit.setId(id);
		return produitService.update(produit);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@JsonView(ProduitsViews.Common.class)
	public void delete(@PathVariable Long id) {
		produitService.delete(id);
	}
	
	@GetMapping("/{id}")
	@JsonView(ProduitsViews.Common.class)
	public Produit getById(@PathVariable Long id) {
		return produitService.getById(id);
	}
	
	@GetMapping("/{libelle}")
	@JsonView(ProduitsViews.Common.class)
	public List<Produit> getByLibelle(@PathVariable String libelle) {
		return produitService.getByLibelle(libelle);
	}
	
	@GetMapping("/{fournisseur}")
	@JsonView(ProduitsViews.Common.class)
	public List<Produit> getByFournisseur(@PathVariable Fournisseur fournisseur) {
		return produitService.getByFournisseur(fournisseur);
	}
}
