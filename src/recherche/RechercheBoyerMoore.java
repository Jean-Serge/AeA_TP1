package recherche;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/**
 * Classe implémentant l'interface Recherche. La recherche du motif se fait selon
 * la méthode Boyer-Moore.
 * 
 * Les résultats de la recherche d'un motif sont stockés dans une liste. Les
 * résultats de la recherche complète sont stockés dans une map associant à un
 * motif sa liste d'occurences.
 * 
 * @author monbailly
 * @author verbaere
 * 
 */
public class RechercheBoyerMoore extends Recherche {

	protected int[] BonSuffixe;
	
	/**
	 * Instancie une nouvelle recherche. Les booleen représente ici les options
	 * de recherche (ex : complémentaire, reverse, ...)
	 * 
	 * @param b le brin à étudier sous forme de chaine de caractères
	 * @param n la taille des motifs à rechercher
	 * @param reverse booléen, vaut true si l'option 'reverse' doit être active
	 * @param comp booléen, vaut true si l'option 'complémentaire' doit être active
	 * @param revComp booléen, vaut true si l'option 'reverse-complementaire' doit être active
	 */
	public RechercheBoyerMoore(String b, int n, boolean reverse, boolean comp,
			boolean revComp) {
		super(b, n, reverse, comp, revComp);
	}
	
	/**
	 * Remplie la table des bons suffixes selon la méthode Boyer-Moore.
	 * @param motif le motif sous forme de chaine de caractères
	 */
	public void remplirBonSuffixe(String mot) {
		String motif = "";
		int sizeM = mot.length();
		boolean drapeau = true;
		
		// On ajoute des 'D' avant le motif pour être tranquille lors de nos comparaisons en début de motif
		for (int x=0; x < sizeM-1; x++)
			motif += "D";
		motif += mot;
		
		// Initialisation :
		this.BonSuffixe = new int[sizeM];
		this.BonSuffixe[sizeM-1] = 1;
		
		int y = sizeM-2;
		while (mot.charAt(y) == mot.charAt(sizeM-1))
			this.BonSuffixe[sizeM-1] += 1;
		
		// On ajoute les valeurs situées entre l'indice 0 et sizeM-2 de droite à gauche :
		for (int i = sizeM-2; i >= 0; i--) {
			// u = le mot a chercher :
			String u = motif.substring(i+sizeM);
			
			
			// index = la premiere occurence de u dans le mot (cad l'occurence la plus à droite) :
			int index = motif.lastIndexOf(u,motif.length()-1);
			// le "mauvais suffixe" :
			String suf = motif.substring(i+sizeM-1,2*(sizeM)-1);

			// tant que l'on croise un mauvais suffixe alors on recalcule l'index de la prochaine occurence de u.
			while (index != -1 && drapeau) {
				
				if (index != -1 && !motif.substring(index-1,index+u.length()).endsWith(suf)) {
					index += u.length()-1;
					drapeau = false;
					// Si l'on croise une occurence alors on met a jour l'index et on change le drapeau
				}

				if (drapeau) {
					index = motif.lastIndexOf(u,index-1);
				}
			}
			drapeau = true;
			// Pour finir on ecrit la valeur de BonSuffix :
			if (index == -1) {
				int retranchement = sizeM-1;
				// Si on trouve pas d'occurence, on fait attention de ne pas oublier que le motif peut avoir des bords !
				while (retranchement > 0 && !mot.substring(0,retranchement).equals(mot.substring(sizeM-retranchement,sizeM))) {
					retranchement--;
				}
				this.BonSuffixe[i] = sizeM-retranchement;
			}
			else {
				this.BonSuffixe[i] = motif.length()-index-1;
			}
		}
	}

	/**
	 * Fonction permettant de rechercher une chaîne donnée en
	 * utilisant l'algorithme Boyer-Moore.
	 * 
	 * @param motif le motif recherché
	 * @return les occurences du motif dans la séquence
	 */
	public List<Integer> chercherMotif(String motif) {
		List<Integer> occ = new ArrayList<Integer>();
		int sizeS = this.sequence.length();
		int sizeM = motif.length();
		
		boolean drapeau = true; // drapeau : vaut 'false' quand la comparaison motif-séquence doit passer à la prochaine occurence possible
		int depart = sizeM-1; // on va commencer la comparaison à l'indice sizeM-1 (comparaison par la droite)
		int i=0; // variable qui décrémentera l'indice 'depart' afin de comparer toutes les lettres du motifs

		// On initialise la table 'GoodSuffix' :
		this.remplirBonSuffixe(motif);
		
		//for (int j=0; j< sizeM; j++)
		//	System.out.println(this.BonSuffix[j]);
		
		// tant que l'indice ne dépasse pas la taille de la séquence
		while (depart <= sizeS-1) {
			// Si le mot correspond (pour l'instant) et que i est plus petit que la taille du motif
			while (i < sizeM && drapeau) {
				// On compare le caractère Sequence[depart-i] et Motif[sizeMotif-1-i]
				if (this.sequence.charAt(depart-i) == motif.charAt(sizeM-1-i))
					i++; // Si identique alors on passe au suivant (on incrémente i)
				else
					drapeau = false; // sinon inutile de continuer la boucle
			}
			
			if (drapeau) {
				/* Si drapeau est toujours vrai, c'est que l'on a trouvé une occurence de motif :
				 - On ajoute l'indice du mot dans la liste des resultats
				 - On calcule le nouvel indice de départ = +1
				 */
				depart += 1;
				occ.add(depart-sizeM);
			}
			else {
				// Sinon il faut recalculer l'indice avec la table BonSuffix
				depart += this.BonSuffixe[sizeM-1-i];
				drapeau = true;
			}
			
			i = 0;
		}
			
		return occ;
	
	}
	
}