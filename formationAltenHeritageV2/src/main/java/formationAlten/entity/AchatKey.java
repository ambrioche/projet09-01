package formationAlten.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AchatKey implements Serializable {
	@ManyToOne
	@JoinColumn(name = "order_detail_order_number", foreignKey = @ForeignKey(name = "fk_order_detail_order_number"))
	private Commande commande;
	@ManyToOne
	@JoinColumn(name = "order_detail_order_id", foreignKey = @ForeignKey(name = "fk_order_detail_order_id"))
	private Produit produit;
	
	public AchatKey() {
		
	}

	public AchatKey(Commande commande, Produit produit) {
		super();
		this.commande = commande;
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commande == null) ? 0 : commande.hashCode());
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
		AchatKey other = (AchatKey) obj;
		if (commande == null) {
			if (other.commande != null)
				return false;
		} else if (!commande.equals(other.commande))
			return false;
		return true;
	}
	
	
}
