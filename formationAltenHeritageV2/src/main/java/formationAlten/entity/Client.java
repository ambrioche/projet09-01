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

import formationAlten.jsonview.Views;

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
	@JsonView(Views.Common.class)
	@Column(name = "first_name")
	private String prenom;
	@JsonView(Views.Common.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "register_date")
	private LocalDate dateInscription;
	@JsonView(Views.Common.class)
	@Column(name = "civility")
	@Enumerated(EnumType.STRING)
	private Civilite civilite;
	@JsonView(Views.CommandeByClient.class)
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
