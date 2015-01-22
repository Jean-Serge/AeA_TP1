package recherche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entite.Motif;

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

	private List<String> dejaVu;
	protected String sequence;
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
	public Recherche(String s, int n, boolean reverse, boolean comp,
			boolean revComp) {
		this.N = n;
		this.sequence = s;
		this.r = reverse;
		this.rc = revComp;
		this.c = comp;
		this.resultats = new HashMap<String, List<Integer>>();
		this.initMap();
		this.dejaVu = new ArrayList<String>();
	}

	/**
	 * Initialise la map en y ajoutant une clé pour chaque motif de taille N
	 * trouvé en parcourant le texte. On suppose N < taille de la chaîne
	 */
	public void initMap() {
		String motif;

		// Ajout chaque chaîne de N caractère de la séquence dans la Map
		for (int i = 0; i < this.sequence.length() - N; i++) {
			motif = sequence.substring(i, i + N);
			this.resultats.put(motif, new ArrayList<Integer>());
		}
	}

	public void trierEntrees() {
		Motif mot;

		// On fusionne les listes
		for (String s : this.resultats.keySet()) {
			mot = new Motif(s);

			if (this.r)
				fusionnerEntrees(mot.getMotif(), mot.Reverse().getMotif());
			if (this.c)
				fusionnerEntrees(mot.getMotif(), mot.Complementary().getMotif());
			if (this.rc)
				fusionnerEntrees(mot.getMotif(), mot.ReverseComplementary().getMotif());
		}
	}

	public void fusionnerEntrees(String m1, String m2) {
		if(m1.equals(m2))
			return;
		
		List<Integer> nouvelle = new ArrayList<Integer>();

		for(Integer i : this.resultats.get(m1)){
			if(!nouvelle.contains(i))
				nouvelle.add(i);
		}
		for(Integer i : this.resultats.get(m2)){
			if(!nouvelle.contains(i))
				nouvelle.add(i);
		}

		this.resultats.put(m1, nouvelle);
		this.resultats.put(m2, nouvelle);
	}

	/**
	 * Effectue une recherche de chaque motif de la Map dans le texte et met à
	 * jour la liste d'occurence correspondante.
	 */
	public void rechercheComplete() {
		for (String s : this.resultats.keySet()) {
			this.resultats.put(s, chercherMotif(s));
		}

		// TODO à améliorer
		this.creerEntreesManquantes();
		this.creerEntreesManquantes();
		this.creerEntreesManquantes();

		this.trierEntrees();
		
		for (String s : resultats.keySet())
			System.out.println(s + " : " + resultats.get(s));
	}

	private void creerEntreesManquantes() {
		Map<String, List<Integer>> m = new HashMap<String, List<Integer>>(
				this.resultats);
		Motif mot;

		for (String s : m.keySet()) {
			mot = new Motif(s);
			if (this.c
					&& !this.resultats.containsKey(mot.Complementary()
							.getMotif()))
				this.resultats.put(mot.Complementary().getMotif(),
						new ArrayList<Integer>());

			if (this.r && !this.resultats.containsKey(mot.Reverse().getMotif()))
				this.resultats.put(mot.Reverse().getMotif(),
						new ArrayList<Integer>());

			if (this.rc
					&& !this.resultats.containsKey(mot.ReverseComplementary()
							.getMotif()))
				this.resultats.put(mot.ReverseComplementary().getMotif(),
						new ArrayList<Integer>());
		}
	}

	public Map<String, List<Integer>> getResultats() {
		return resultats;
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
		retour += "Séquence : " + sequence;
		retour += "\nOptions : \nr = " + r + "\nc = " + c + "\nrc = " + rc;
		retour += "\nN = " + N;
		return retour;
	}
}
