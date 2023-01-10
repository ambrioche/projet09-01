package formationAlten.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import formationAlten.dao.DaoAchat;
import formationAlten.dao.DaoAchatFonc;
import formationAlten.dao.DaoClient;
import formationAlten.dao.DaoClientFonc;
import formationAlten.dao.DaoCommande;
import formationAlten.dao.DaoCommandeFonc;
import formationAlten.dao.DaoFournisseur;
import formationAlten.dao.DaoFournisseurFonc;
import formationAlten.dao.DaoProduit;
import formationAlten.dao.DaoProduitFonc;

public class JpaContext {
	
	private static EntityManagerFactory emf = null;
	private static DaoFournisseur daoFournisseur = null;
	private static DaoClient daoClient = null;
	private static DaoProduit daoProduit = null;
	private static DaoCommande daoCommande = null;
	private static DaoAchat daoAchat = null;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(emf == null) {
			emf=Persistence.createEntityManagerFactory("baseAltenPourFormateurHeritage");
		}
		return emf;
	}
	
	public static void DestroyJpaContext() {
		if(emf != null) {
			emf.close();
			emf = null;
		}
	}
	
	public static DaoFournisseur getDaoFournisseur() {
		if(daoFournisseur==null) {
			daoFournisseur=new DaoFournisseurFonc();
		}
		return daoFournisseur;
	}
	
	public static DaoClient getDaoClient() {
		if(daoClient==null) {
			daoClient=new DaoClientFonc();
		}
		return daoClient;
	}
	
	public static DaoProduit getDaoProduit() {
		if(daoProduit==null) {
			daoProduit=new DaoProduitFonc();
		}
		return daoProduit;
	}
	
	public static DaoCommande getDaoCommande() {
		if(daoCommande==null) {
			daoCommande=new DaoCommandeFonc();
		}
		return daoCommande;
	}
	public static DaoAchat getDaoAchat() {
		if(daoAchat==null) {
			daoAchat=new DaoAchatFonc();
		}
		return daoAchat;
	}
}

