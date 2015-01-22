package recherche;

import java.util.List;

import entite.Brin;

/**
 * Classe abstraite représentant une recherche de motif.
 * Cette classe permet de factoriser l'implémentation de 
 * plusieurs algorithmes de recherche.
 * 
 * @author monbailly
 * @author verbaere
 *	
 */
public abstract class Recherche {

	protected Brin b;
	protected int N;
	protected boolean r, c, rc;
	
	/**
	 * Instancie une nouvelle recherche.
	 * Les booleen représente ici les options de recherche
	 * (ex : complémentaire, reverse, ...)
	 * @param b le brin à étudier
	 * @param n la taille des motifs à rechercher
	 * @param reverse 
	 * @param comp
	 * @param revComp
	 */
	public Recherche(Brin b, int n, boolean reverse, boolean comp,
			boolean revComp) {
		this.N = n;
		this.b = b;
		this.r = reverse;
		this.rc = revComp;
		this.c = comp;
	}

	/**
	 * Fonction abstraite permettant de rechercher une chaîne 
	 * donnée en utilisant l'un des algorithme vus en cours.
	 * @param motif
	 * @return
	 */
	public abstract List<Integer> chercherMotif(String motif);
	
	public String toString(){
		String retour = "";
		retour += "Séquence : " + b.getSequence();
		retour += "Options : \nr = " + r + "\nc = " + c + "\nrc = "
				+ rc;
		retour += "\nN = " + N ;
		return retour;
	}
}
