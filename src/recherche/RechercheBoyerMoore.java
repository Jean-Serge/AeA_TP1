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

	protected int[] BonSuffix;
	
	/**
	 * Instancie une nouvelle recherche. Les booleen représente ici les options
	 * de recherche (ex : complémentaire, reverse, ...)
	 * 
	 * @param b le brin à étudier sous forme de chaine de caractères
	 * @param n la taille des motifs à rechercher
	 * @param reverse booléen, vaut true si l'option 'reverse' doit être actif
	 * @param comp booléen, vaut true si l'option 'complémentaire' doit être actif
	 * @param revComp booléen, vaut true si l'option 'reverse-complementaire' doit être actif
	 */
	public RechercheBoyerMoore(String b, int n, boolean reverse, boolean comp,
			boolean revComp) {
		super(b, n, reverse, comp, revComp);
	}
	
	/**
	 * Remplie la table des bons suffixes selon la méthode Boyer-Moore.
	 * @param motif le motif sous forme de chaine de caractères
	 */
	public void remplirBonSuffix(String motif) {
		int sizeM = motif.length();
		
		// Initialisation :
		this.BonSuffix = new int[sizeM];
		this.BonSuffix[sizeM-1] = 1;
		this.BonSuffix[0] = sizeM;
		
		// On ajoute les valeurs situées entre l'indice 1 et sizeM-2 de droite à gauche :
		for (int i = sizeM-2; i > 0; i--) {
			// u = le mot a chercher :
			String u = motif.substring(i+1);
			// index = la premiere occurence de u dans le mot (cad l'occurence la plus à droite) :
			int index = motif.lastIndexOf(u,sizeM-2)+u.length()-1;
			// le "mauvais suffixe" :
			String suf = motif.substring(i,sizeM);
			
			// tant que l'on croise un mauvais suffixe alors on recalcule l'index de la prochaine occurence de u.
			while (index != -1 && motif.substring(0,index+1).endsWith(suf)) {	
				index = motif.lastIndexOf(u,index-suf.length());
				// si l'on trouve une occurence il faut rajouter la longueur du mot - 1 pour calculer l'index dans le mot
				if (index != -1)
					index += u.length()-1;
			}
			// Pour finir on ecrit la valeur de BonSuffix :
			if (index == -1)
				this.BonSuffix[i] = sizeM;
			else {
				this.BonSuffix[i] = sizeM-1-index;
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
		this.remplirBonSuffix(motif);
		
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
				 - On calcule le nouvel indice de départ
				 */
				depart += sizeM;
				occ.add(depart-i-sizeM+1);
			}
			else {
				// Sinon il faut recalculer l'indice avec la table BonSuffix
				depart += this.BonSuffix[sizeM-1-i];
				drapeau = true;
			}
			
			i = 0;
		}
			
		return occ;
	
	}
	
}