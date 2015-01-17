package entite;

public class Motif {
	
	private String motif;
	
	/** Constructeur Motif :
	 * Permet la création d'un motif. Ce motif doit être une chaine de caractères
	 * composée de G,C,T et A.
	 * 
	 * @param texte la chaine de caractère prise pour motif.
	 */
	public Motif(String texte) {
		this.motif = texte;
	}

	/** Retourne le motif (c'est à dire la chaine de caractère prise pour motif).
	 * 
	 * @return le motif
	 */
	public String getMotif() {
		return motif;
	}

	/** Permet de changer le motif actuel par un autre passé en paramètre de la
	 * fonction.
	 * 
	 * @param motif le nouveau motif.
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	/** Retourne la taille du motif actuel.
	 * 
	 * @return la taille du motif.
	 */
	public int SizeofMotif() {
		return this.motif.length();
	}
	
	/** Retourne le motif complémentaire.
	 * Pour rappel, le complémentaire de A (Adénine) et T (Thymine)
	 * et G (Guanine) et C (Cytosine).
	 * 
	 * @return le motif complémentaire du motif actuel.
	 */
	public Motif Complementary() {
		String comp = "";
		for (int i=0; i< this.SizeofMotif(); i++)
			comp += Character.toString(CharComplementary(this.motif.charAt(i)));
		return new Motif(comp);
	}
	
	/** Retourne le motif "reverse".
	 * La chaine de caractères du motif "reverse" est en fait la chaine de 
	 * caractères lu dans le sens contraire (c'est à dire de droite à gauche).
	 * 
	 * @return le motif "reverse" du motif actuel.
	 */
	public Motif Reverse() {
		StringBuffer rev = new StringBuffer(this.getMotif());
		return new Motif(rev.reverse().toString());
	}
	
	/** Retourne le motif "reverse-complémentaire".
	 * C'est l'application à la fois du "reverse" et du complémentaire.
	 * 
	 * @return le motif "reverse-complémentaire" du motif actuel.
	 */
	public Motif ReverseComplementary() {		
		return this.Reverse().Complementary();
	}
	
	/** Retourne le complémentaire du nucléotide passé en paramètre.
	 * 
	 * @param c le nucléotide a convertir (A,G,C ou T).
	 * @return son complémentaire.
	 */
	public static char CharComplementary(char c) {
		switch (c) {
		case 'A': return 'T';
		case 'T': return 'A';
		case 'G': return 'C';
		case 'C': return 'G';
		default: return '/';
		}
	}
	
}
