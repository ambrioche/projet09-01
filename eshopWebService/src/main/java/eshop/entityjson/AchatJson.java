package eshop.entityjson;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;

import com.fasterxml.jackson.annotation.JsonView;

import formationAlten.entity.AchatKey;
import formationAlten.entity.Produit;
import formationAlten.jsonview.Views;

public class AchatJson {
	@JsonView(Views.AchatJsonProduit.class)
	private Produit produit;
	@JsonView(Views.Common.class)
	private int quantite;
	
	
	public AchatJson() {
		super();
	}
	
	public AchatJson(Produit produit, int quantite) {
		super();
		this.produit = produit;
		this.quantite = quantite;
	}

	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
}
