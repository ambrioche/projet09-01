//package formationAlten.entityJson;
//
//import javax.persistence.Column;
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//
//import com.fasterxml.jackson.annotation.JsonView;
//
//import formationAlten.entity.Produit;
//import formationAlten.jsonview.Views;
//
//@Entity
//@SequenceGenerator(name = "seqAchatJson", sequenceName = "achat_json_seq_id", initialValue = 1, allocationSize = 1)
//public class AchatJson {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAchatJson")
//	@JsonView(Views.Common.class)
//	private Long id;
//	@JsonView(Views.AchatJsonProduit.class)
//	private Produit produit;
//	@JsonView(Views.Common.class)
//	private int quantite;
//	
//	
//	
//	
//	public AchatJson() {
//		super();
//	}
//	
//	public AchatJson(Produit produit, int quantite) {
//		super();
//		this.produit = produit;
//		this.quantite = quantite;
//	}
//
//	public Produit getProduit() {
//		return produit;
//	}
//	public void setProduit(Produit produit) {
//		this.produit = produit;
//	}
//	public int getQuantite() {
//		return quantite;
//	}
//	public void setQuantite(int quantite) {
//		this.quantite = quantite;
//	}
//	
//	
//}
