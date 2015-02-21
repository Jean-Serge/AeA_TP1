package entite;

public class MotifGenome extends Motif {

	/**
	 * Constructeur Motif : Permet la création d'un motif. Ce motif doit être
	 * une chaine de caractères composée de G,C,T et A.
	 * 
	 * @param texte la chaine de caractère prise pour motif.
	 */
	public MotifGenome(String texte) {
		super(texte);
	}

	/**
	 * Permet de changer le motif actuel par un autre passé en paramètre de la
	 * fonction.
	 * 
	 * @param motif le nouveau motif.
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/**
	 * Retourne le motif complémentaire. Pour rappel, le complémentaire de A
	 * (Adénine) et T (Thymine) et G (Guanine) et C (Cytosine).
	 * 
	 * @return le motif complémentaire du motif actuel.
	 */
	public MotifGenome Complementary() {
		String comp = "";
		for (int i = 0; i < this.SizeofMotif(); i++)
			comp += Character.toString(CharComplementary(this.motif.charAt(i)));
		return new MotifGenome(comp);
	}

	/**
	 * Retourne le motif "reverse". La chaine de caractères du motif "reverse"
	 * est en fait la chaine de caractères lu dans le sens contraire (c'est à
	 * dire de droite à gauche).
	 * 
	 * @return le motif "reverse" du motif actuel.
	 */
	public MotifGenome Reverse() {
		StringBuffer rev = new StringBuffer(this.getMotif());
		return new MotifGenome(rev.reverse().toString());
	}

	/**
	 * Retourne le motif "reverse-complémentaire". C'est l'application à la fois
	 * du "reverse" et du complémentaire.
	 * 
	 * @return le motif "reverse-complémentaire" du motif actuel.
	 */
	public MotifGenome ReverseComplementary() {
		return this.Reverse().Complementary();
	}

	/**
	 * Retourne le complémentaire du nucléotide passé en paramètre.
	 * 
	 * @param c le nucléotide a convertir (A,G,C ou T).
	 * @return son complémentaire.
	 */
	public static char CharComplementary(char c) {
		switch (c) {
		case 'A':
			return 'T';
		case 'T':
			return 'A';
		case 'G':
			return 'C';
		case 'C':
			return 'G';
		default:
			return '/';
		}
	}
	
	/**
	 * Permet de savoir si un motif est un Pré-Micro-ARN.
	 * @return vrai ou faux si le motif est un Pré-Micro-ARN
	 */
	public boolean estPreMicroARN() {
		if (this.motif.length() > 69)
			return this.isNormal(this.motif);
		else
			return false;
	}

	/**
	 * Permet de savoir si une chaine correspond au norme d'un Pré-Micro-ARN.
	 * @param motif le motif a tester
	 * @return vrai ou faux si le motif est un Pré-Micro-ARN
	 */
	private boolean isNormal(String motif) {
		return fonc(motif,0,0,0,0);
	}
	
	/**
	 * 
	 * @param seq
	 * @param nb_parent_d
	 * @param nb_parent_g
	 * @param nb_point_d
	 * @param nb_point_g
	 * @return
	 */
	private static boolean fonc(String seq, int nb_parent_d, int nb_parent_g,
			int nb_point_d, int nb_point_g) {

		if (seq.length() == 1 || seq.length() == 0)
			return true;
		if ((nb_point_d == 4 || nb_point_g == 4) && seq.length() != 0)
			return false;
		else if (nb_point_d == 4 || nb_point_g == 4)
			return true;
		if (appariement(seq.charAt(0),seq.charAt(seq.length()-1)))
			return fonc(seq.substring(1,seq.length()-1),nb_parent_d+1,nb_parent_g+1,0,0);
		else
			return (((nb_point_d>2 || nb_point_d==0) && nb_point_g<4 && fonc(seq.substring(0, seq.length()-1),0,nb_parent_g,nb_point_d+1,nb_point_g) || (nb_parent_g>2 || nb_parent_g==0) && nb_point_g<4 && fonc(seq.substring(1),nb_parent_d,0,nb_point_d,nb_point_g+1))
					|| (nb_point_g<4 && nb_point_d<4 && fonc(seq.substring(1, seq.length()-1),0,0,nb_point_d+1,nb_point_g+1)) );
	}
	
	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static boolean appariement(char c1, char c2) {
		switch (c1) {
		case 'A':
			if (c2 == 'T')
				return true;
			else
				return false;
		case 'C':
			if (c2 == 'G')
				return true;
			else
				return false;
		case 'G':
			if (c2 == 'C' || c2 == 'T')
				return true;
			else
				return false;
		default:
			if (c2 == 'A' || c2 == 'G')
				return true;
			else 
				return false;			
		}
	}
	
	// TEST DE fonc
	public static void main(String[] argv) {
		System.out.println(fonc("TGCTTCCGGCCTGTTCCCTGAGACCTCAAGTGTGAGTGTACTATTGATGCTTCACACCTGGGCTCTCCGGGTACCAGGACGGTTTGAGCA",0,0,0,0));
	}

}
