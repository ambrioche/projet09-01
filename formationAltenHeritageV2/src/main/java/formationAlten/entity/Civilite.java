package formationAlten.entity;

public enum Civilite {
	M("Monsieur"),MME("Madame"),MLLE("mademoiselle");
	
	private String libelle;
	
	private Civilite(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

}
