package detection;

import java.util.ArrayList;

import entite.Brin;

/**
 * Classe permettant la detection de tous les préMiRNA dans une séquence.
 * @author verbaere
 * @author monbailly
 *
 */
public class DetectionPreMiRNA {

	private String sequence;
	private int debut;
	private int fin;
	
	/**
	 * Constructeur de recherche de préMiRNA.
	 * @param br le brin ou la recherche doit s'effectuer
	 */
	public DetectionPreMiRNA(Brin br) {
		this.sequence = br.getSequence();
		this.debut = 0; // initialement le pas est de 100, donc on commence la recherche
		this.fin = 99; // à lindice 0 et 99.
	}
	
	
	/**
	 * Retourne les coordonnées du Pré-Micro-ARN contenu dans la chaine (s'il y en a un).
	 * @param ind_dep l'indice de la chaine ou il faut débuter la recherche
	 * @param ind_fin l'indice de la chaine ou il faut stopper la recherche
	 * @return la coordonnées ou null
	 */
	public int[] contientPreMiRNA(int ind_dep, int ind_fin) {
		
		MaxParenthesage nuss = new MaxParenthesage(this.sequence.substring(ind_dep, ind_fin+1));
		nuss.executeAlgo();
		
		int[][] results = nuss.getResults();
		int larg = ind_fin-ind_dep;
		int lon = 0;
			
		// Parcours du tableau de resultat afin de calculer la longueur de la boucle terminale :
		while (larg > 0 && results[larg][ind_fin-ind_dep] == 0)
			larg--;
		while (lon < (ind_fin-ind_dep) && results[larg][lon] == 0)
			lon++;
				
		// On vérifie que la boucle terminale est inférieure à 9 :
		if (lon-1-larg > 8)
			return null;

		// Parcours du nombre d'appariements pour savoir si la chaine contient un
		// Pré-Micro-ARN :
		int[] coord = new int[2];
		
			for (int i=0; i < ind_fin-ind_dep+1; i++) {
				for (int j=ind_fin-ind_dep; j > 0; j--) {
				// Il faut avoir 24 appariements au moins
				if (results[i][j] >= 24) {
					coord[0] = this.debut+i;
					coord[1] = this.debut+i+j;
					return coord;
				}
			}
		}
		
		return null;
	}
	
	public ArrayList<int[]> getAllPreMiRNA() {
		int[] unresult;
		ArrayList<int[]> list = new ArrayList<int[]>();
		
		while (this.fin-this.debut > 68) {
			// On regarde entre les positions debut et fin si on a un préMiRNA
			unresult = this.contientPreMiRNA(this.debut, this.fin);
			if (unresult != null) {
				// si on a un resultat alors on l'ajoute dans la liste
				list.add(unresult);
				// On met a jour les indices :
				this.debut = unresult[1]+1;
				this.fin = this.debut + 99;
			}
			else {
				// sinon on passe au 100 caractères suivants :
				this.debut++;
				this.fin++;
			}

			// On prend garde de ne pas dépasser le nombre de caractères de la séquence
			if (this.fin >= this.sequence.length())
				this.fin = this.sequence.length()-1;
	
		}
		
		return list;
		
	}
	
	/**
	 * Main pour tester :
	 * @param argv
	 */
	public static void main(String[] argv) {
		Brin br = new Brin("donnees/chromosome.fasta");
		DetectionPreMiRNA det = new DetectionPreMiRNA(br);
		System.out.println("Le chromosome 13 contient :");
		System.out.println(det.getAllPreMiRNA().size()+" préMiRNA(s).");
		
	}
	
}
