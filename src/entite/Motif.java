package entite;

public abstract class Motif {

	protected String motif;

	public Motif(String motif) {
		super();
		this.motif = motif;
	}
	
	/**
	 * Retourne le motif (c'est à dire la chaine de caractère prise pour motif).
	 * 
	 * @return le motif
	 */
	public String getMotif() {
		return motif;
	}
	
	/**
	 * Retourne la taille du motif actuel.
	 * 
	 * @return la taille du motif.
	 */
	public int SizeofMotif() {
		return this.motif.length();
	}

}
