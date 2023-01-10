package eshop.restcontroller;

import java.lang.reflect.Field;
import java.time.LocalDate;
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
import formationAlten.entity.Client;
import formationAlten.jsonview.Views;
import formationAlten.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("")
	@JsonView(Views.Common.class)
	public List<Client> getAll(){
		return clientService.getAll();
	}
	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public Client getById(@PathVariable Long id) {
		return clientService.getById(id);
	}
	@GetMapping("/{id}/commandes")
	@JsonView(Views.ClienCommande.class)
	public Client getByIdWithCommades(@PathVariable Long id) {
		return clientService.getByIdWithCommande(id);
	}
	@GetMapping("/name/{nom}")
	@JsonView(Views.Common.class)
	public List<Client> getByName(@PathVariable String nom) {
		return clientService.getByNom(nom);
	}
	@GetMapping("/email/{email}")
	@JsonView(Views.Common.class)
	public List<Client> getByEmail(@PathVariable String email) {
		return clientService.getByNom(email);
	}
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	@JsonView(Views.ClienCommande.class)
	public Client create(@Valid @RequestBody Client client, BindingResult br) {
		Check.checkBindingResulHasError(br);
		return clientService.create(client);
	}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Common.class)
	public Client update(@Valid @RequestBody Client client, BindingResult br, @PathVariable Long id) {
		Check.checkBindingResulHasError(br);
		client.setId(id);
		return clientService.update(client);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clientService.delete(id);
	}
	private void setField(Object target, Class modelClass, String fieldName, Object value) {
		Field field = ReflectionUtils.findField(modelClass, fieldName);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, target, value);
	}
	@PatchMapping("/{id}")
	@JsonView(Views.Common.class)
	public Client patch(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		Client client = clientService.getById(id);

		fields.forEach((key, value) -> {
			if (key.equals("adresse")) {
				final Adresse adresse;

				if (client.getAdresse() != null) {
					adresse = client.getAdresse();
				} else {
					adresse = new Adresse();
				}
				((Map<String, Object>) value).forEach((k, v) -> {
					setField(adresse, Adresse.class, k, v);
				});
				client.setAdresse(adresse);
			} else if (key.equals("dtNaiss")) {
				client.setDateInscription(LocalDate.parse(value.toString()));
			} else {
				setField(client, Client.class, key, value);
			}
		});
		return clientService.update(client);
	}

	
}
