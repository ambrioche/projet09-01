package formationAlten.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import formationAlten.jsonview.Views;







@MappedSuperclass
public abstract class Compte {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqCompte")
	@JsonView(Views.Common.class)
	private Long id;
	@Column(name = "last_name")
	@JsonView(Views.Common.class)
	private String nom;
	@Column(name = "email")
	@JsonView(Views.Common.class)
	private String email;
	@Embedded
	@JsonView(Views.Common.class)
	private Adresse adresse;
	
	public Compte() { 
		
	}

	public Compte(String nom, String email, Adresse adresse) {
		super();
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	

}
