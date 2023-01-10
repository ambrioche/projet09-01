package formationAlten.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@AttributeOverride(name = "adresse.numero", column = @Column(name="supplier_number", length = 50))
@AttributeOverride(name = "adresse.rue", column = @Column(name="supplier_street", length = 255))
@AttributeOverride(name = "adresse.codePostal", column = @Column(name="supplier_zip_code", length = 50))
@AttributeOverride(name = "adresse.ville", column = @Column(name="supplier_city", length = 255))
@AttributeOverride(name = "id", column = @Column(name="supplier_id"))
@AttributeOverride(name = "nom", column = @Column(name="supplier_last_name"))
@AttributeOverride(name = "email", column = @Column(name="supplier_email"))
@Table(name="Supplier")
@SequenceGenerator(name = "seqCompte", sequenceName = "supplier_id_seq", initialValue = 69, allocationSize = 2)
public class Fournisseur extends Compte {
	@Column(name = "contact")
	private String contact;
	
	//@OneToOne(mappedBy = "fournisseur")
	//private Produit produit;
	
	@OneToMany(mappedBy = "fournisseur")
	private List<Produit> listeProduits;
	
	public Fournisseur() {
		
	}

	public Fournisseur(String nom, String email, Adresse adresse, String contact) {
		super(nom, email, adresse);
		this.setContact(contact);
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public Adresse getAdresse() {
		return super.getAdresse();
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	
}
