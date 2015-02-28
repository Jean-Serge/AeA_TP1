package detection;

import java.util.ArrayList;
import java.util.List;

import recherche.RechercheBoyerMoore;
import entite.Brin;
import entite.MotifGenome;

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
		// Pré-Micro-ARN (on prend le plus petit que l'on trouve):
		int[] coord = new int[2];
		
		
		for (int i=0; i < ind_fin-ind_dep+1; i++) {
			for (int j=0; j < ind_fin-ind_dep+1; j++) {
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
			}
			// On passe au 100 caractères suivants (on suppose que les pré-MiRNA peuvent :
			this.debut++;
			this.fin++;

			// On prend garde de ne pas dépasser le nombre de caractères de la séquence
			if (this.fin >= this.sequence.length())
				this.fin = this.sequence.length()-1;
	
		}
		
		return list;
		
	}
	
	/**
	 * Main pour tester :
	 * @param argv l'ARN messager à hybrider sur l'un des préMiRNA (optionnel)
	 */
	public static void main(String[] argv) {
		// On charge le chromosome 13 :
		Brin br = new Brin("donnees/chromosome.fasta");
		// On créé une nouvelle detection de pré-MiRNA :
		DetectionPreMiRNA det = new DetectionPreMiRNA(br);
		
		System.out.println("Le chromosome 13 contient :");
		ArrayList<int[]> result = det.getAllPreMiRNA();
		int size = result.size();
		System.out.println(size+" préMiRNA(s).");
		
		// l'utilisateur souhaite connaitre le Pré-Micro-ARN ayant un micro-ARN 
		// pouvant s'hybrider avec un ARN-messager passé en argument :
		
		if (argv.length == 1) {
			
			System.out.println();
			// On charge l'ARN-messager
			Brin messager = new Brin(argv[0]);
			MotifGenome motif = new MotifGenome(messager.getSequence().substring(0, 8));
			// Le micro-ARN doit commencer par ce motif :
			MotifGenome complement = motif.Complementary();
			
			// On va utiliser Boyer-Moore (TP1) pour retrouver l'indice de ce motif dans tous les pré-MiRNA
			RechercheBoyerMoore boyer;
			List<Integer> res;
			String arn_max = "";
			
			for (int i=0; i < size; i++) {
				// On parcours toute la liste en utilisant Boyer-Moore
				boyer = new RechercheBoyerMoore(br.getSequence().substring(result.get(i)[0], result.get(i)[1]+1), 0, false, false, false);
				res = boyer.chercherMotif(complement.getMotif());
				
				for (int x : res) {
					// Pour chaque resultat on regarde si l'indice est compris en 10 (9) et 15 (14).
					if (x > 9 && x < 15) {
						String hybride = br.getSequence().substring(result.get(i)[0], result.get(i)[1]+1);
						// On va prendre le pré-MiRNA de longueur maximale (les autres étant "contenus" dans celui-ci)
						if (hybride.length() > arn_max.length()) {
							arn_max = hybride;
						}
					}
				}
				
			}
			if (!arn_max.equals("")) {
				System.out.println("le messager s'hybride avec le micro-ARN du pré-micro-ARN suivant :");
				System.out.println(arn_max);
			}
		}
		
	}
	
}
