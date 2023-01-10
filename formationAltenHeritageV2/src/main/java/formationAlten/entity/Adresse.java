package formationAlten.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

import formationAlten.jsonview.Views;


@Embeddable
public class Adresse {
	@JsonView(Views.Common.class)
	private String numero;
	
	@Column(name = "street")
	@JsonView(Views.Common.class)
	private String rue;
	@JsonView(Views.Common.class)
	private String codePostal;
	@JsonView(Views.Common.class)
	private String ville;
	
	public Adresse () {
		
	}

	public Adresse(String numero, String rue, String codePostal, String ville) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}
