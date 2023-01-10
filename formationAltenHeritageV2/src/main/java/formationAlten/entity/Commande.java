package formationAlten.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

import formationAlten.jsonview.Views;

@Entity
@Table(name="orders")
@SequenceGenerator(name = "seqCommande", sequenceName = "order_seq_id", initialValue = 1, allocationSize = 1)
public class Commande {
	@JsonView(Views.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCommande")
	@Column(name = "order_number")
	private Long numero;
	@JsonView(Views.Common.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "order_date")
	private LocalDate date;
	@JsonView(Views.CommandeByClient.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_customer_id", foreignKey = @ForeignKey(name = "fk_order_customer_id"))
	private Client client;
	/*@ManyToMany
	*@JoinTable(name = "order_details",
	*	joinColumns = @JoinColumn(name = "order_details_order_number", foreignKey = @ForeignKey(name = "fk_order_details_order_number")),
	*	inverseJoinColumns = @JoinColumn(name = "order_details_product_id"), foreignKey = @ForeignKey (name = "order_details_product_id"))
	*private Set<Produit> achats;
	*/
	@JsonView(Views.CommandeWithAchats.class)
	@OneToMany(mappedBy = "id.commande")
	private List<Achat> achats;
	
	public Commande() {
		super();
	}

	public Commande(LocalDate date, Client client) {
		super();
		this.date = date;
		this.client = client;
	}

	public Commande(LocalDate date, Client client, List<Achat> achats) {
		super();
		this.date = date;
		this.client = client;
		this.achats = achats;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}
	
	
	
	

}
