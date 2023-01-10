package eshop.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import eshop.util.Check;
import formationAlten.entity.Adresse;
import formationAlten.entity.Fournisseur;
import formationAlten.jsonview.Views;
import formationAlten.service.FournisseurService;

@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurRestController {

	@Autowired
	private FournisseurService fournisseurService;

	@GetMapping("")
	@JsonView(Views.Common.class)
	public List<Fournisseur> getAll() {
		return fournisseurService.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public Fournisseur getById(@PathVariable Long id) {
		return fournisseurService.getById(id);
	}
	@GetMapping("/{nom}")
	@JsonView(Views.FournisseurByNom.class)
	public Fournisseur getByNom(@PathVariable String nom) {
		return fournisseurService.getByNom(nom);
	}
	@GetMapping("/{contact}")
	@JsonView(Views.FournisseurByContact.class)
	public Fournisseur getByContact(@PathVariable String contact) {
		return fournisseurService.getByContact(contact);
	}
	
	@GetMapping("/{email}")
	@JsonView(Views.FournisseurByEmail.class)
	public Fournisseur getByEmail(@PathVariable String email) {
		return fournisseurService.getByEmail(email);
	}

	@GetMapping("/{id}/produits")
	@JsonView(Views.FournisseurWithProduits.class)
	public Fournisseur getByIdWithProduits(@PathVariable Long id) {
		return fournisseurService.getByIdWithProduits(id);
	}

	@PostMapping("")
	@JsonView(Views.Common.class)
	public Fournisseur create(@Valid @RequestBody Fournisseur fournisseur, BindingResult br) {
		Check.checkBindingResulHasError(br);
		return fournisseurService.create(fournisseur);
	}

	@PutMapping("/{id}")
	@JsonView(Views.Common.class)
	public Fournisseur update(@Valid @RequestBody Fournisseur fournisseur, BindingResult br, @PathVariable Long id) {
		Check.checkBindingResulHasError(br);
		fournisseur.setId(id);
		return fournisseurService.update(fournisseur);
	}
	

	@PatchMapping("/{id}")
	@JsonView(Views.Common.class)
	public Fournisseur patch(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		Fournisseur fournisseur = fournisseurService.getById(id);

		fields.forEach((key, value) -> {
			if (key.equals("adresse")) {
				final Adresse adresse;

				if (fournisseur.getAdresse() != null) {
					adresse = fournisseur.getAdresse();
				} else {
					adresse = new Adresse();
				}
				((Map<String, Object>) value).forEach((k, v) -> {
					setField(adresse, Adresse.class, k, v);
				});
				fournisseur.setAdresse(adresse);
			} else {
				setField(fournisseur, Fournisseur.class, key, value);
			}
		});
		return fournisseurService.update(fournisseur);
	}

	private void setField(Object target, Class modelClass, String fieldName, Object value) {
		Field field = ReflectionUtils.findField(modelClass, fieldName);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, target, value);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		fournisseurService.delete(id);
	}
}