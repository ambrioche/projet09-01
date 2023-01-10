package formationAlten.dao;

import java.util.List;

import formationAlten.entity.Produit;

public interface DaoProduit extends DaoGeneric<Produit, Long> {
	public List<Produit> findByLibelle(String libelle);
}
