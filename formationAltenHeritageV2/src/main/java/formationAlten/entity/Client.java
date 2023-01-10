package formationAlten.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.AttributeOverride;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

import formationAlten.jsonviews.Views;

@Entity
@AttributeOverride(name = "adresse.numero", column = @Column(name="customer_number", length = 50))
@AttributeOverride(name = "adresse.rue", column = @Column(name="customer_street", length = 255))
@AttributeOverride(name = "adresse.codePostal", column = @Column(name="customer_zip_code", length = 50))
@AttributeOverride(name = "adresse.ville", column = @Column(name="customer_city", length = 255))
@AttributeOverride(name = "id", column = @Column(name="customer_id"))
@AttributeOverride(name = "nom", column = @Column(name="customer_last_name"))
@AttributeOverride(name = "email", column = @Column(name="customer_email"))
@Table(name="customer")
@SequenceGenerator(name = "seqCompte", sequenceName = "customer_id_seq", initialValue = 1, allocationSize = 1)
public class Client extends Compte {
	@Column(name = "first_name")
	@JsonView(Views.Common.class)
	private String prenom;
	@Column(name = "register_date")
	@JsonView(Views.Common.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateInscription;
	@Column(name = "civility")
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Common.class)
	private Civilite civilite;
	@JsonView(Views.ClienCommande.class)
	@OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
	private List<Commande> commandes;

	
	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Client() {
		
	}

	public Client(String nom, String email, Adresse adresse, String prenom, LocalDate dateInscription,
			Civilite civilite) {
		super(nom, email, adresse);
		this.prenom = prenom;
		this.dateInscription = dateInscription;
		this.civilite = civilite;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
	

	public Adresse getAdresse() {
		return super.getAdresse();
	}
	

}
