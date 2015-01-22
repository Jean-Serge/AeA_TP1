package recherche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entite.Brin;

/**
 * Classe abstraite représentant une recherche de motif. Cette classe permet de
 * factoriser l'implémentation de plusieurs algorithmes de recherche.
 * 
 * Les résultats de la recherche d'un motif sont stockés dans une liste. Les
 * résultats de la recherche complète sont stockés dans une map associant à un
 * motif sa liste d'occurences.
 * 
 * @author monbailly
 * @author verbaere
 * 
 */
public abstract class Recherche {

	protected Brin b;
	protected int N;
	protected boolean r, c, rc; // Options de recherche
	protected Map<String, List<Integer>> resultats;

	/**
	 * Instancie une nouvelle recherche. Les booleen représente ici les options
	 * de recherche (ex : complémentaire, reverse, ...)
	 * 
	 * @param b
	 *            le brin à étudier
	 * @param n
	 *            la taille des motifs à rechercher
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
		this.resultats = new HashMap<String, List<Integer>>();
	}

	/**
	 * Initialise la map en y ajoutant une clé pour chaque motif
	 * de taille N trouvé en parcourant le texte.
	 * On suppose N < taille de la chaîne
	 */
	public void initMap(){
		String motif;
		
		// Ajout chaque chaîne de N caractère de la séquence dans la Map
		for(int i = 0 ; i < this.b.SizeofSequence() - N ; i++){
			motif = b.getSequence().substring(i, i+N);
			this.resultats.put(motif, new ArrayList<Integer>());
		}
	}
	
	/**
	 * Effectue une recherche de chaque motif de la Map dans le texte
	 * et met à jour la liste d'occurence correspondante.
	 */
	public void rechercheComplete(){
		this.initMap();
		
		for(String s: this.resultats.keySet()){
			this.resultats.put(s, chercherMotif(s));
		}
		
		for(String s : this.resultats.keySet())
			System.out.println(s + " : " + this.resultats.get(s));
	}
	
	/**
	 * Fonction abstraite permettant de rechercher une chaîne donnée en
	 * utilisant l'un des algorithme vus en cours.
	 * 
	 * @param motif
	 * @return
	 */
	public abstract List<Integer> chercherMotif(String motif);

	public String toString() {
		String retour = "";
		retour += "Séquence : " + b.getSequence();
		retour += "\nOptions : \nr = " + r + "\nc = " + c + "\nrc = " + rc;
		retour += "\nN = " + N;
		return retour;
	}
}
