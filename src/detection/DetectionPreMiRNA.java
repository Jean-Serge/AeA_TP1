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
		
		// Parcours du nombre d'appariements pour savoir si la chaine contient un
		// Pré-Micro-ARN (on prend le plus grand que l'on trouve):
		int[] coord = new int[2];
		
		
		for (int i=0; i < ind_fin-ind_dep+1; i++) {
			for (int j=ind_fin-ind_dep; j >= 0; j--) {
			// Il faut avoir 24 appariements au moins
				if (results[i][j] >= 24 && j-i >= 69  && this.debut+i+j < this.sequence.length() && nuss.getAppariements(i, j) > 2) {
					coord[0] = this.debut+i;
					coord[1] = this.debut+i+j;
					if (VerifieBoucleTerminale(i,j,nuss))
						return coord;
				}
			}
		}
		
		return null;
	}
	
	
	/**
	 * Permet de vérifier la longueur de la boucle terminale de la séquence génomique
	 * en effectuant un BackTrace depuis les coordonnees i,j de la table de programmation dynamique.
	 * @param i
	 * @param j
	 * @param maxp le resultat de l'algorithme de maximisation d'appariements
	 * @return
	 */
	private boolean VerifieBoucleTerminale(int i, int j, MaxParenthesage nuss) {
		int[][] tabAppariements = nuss.getResults();
		int x = i;
		int y = j;
		// On déroule la diagonale jusqu'au dernier appariements, cela nous donne les indices de la boucle terminale
		while (tabAppariements[x][y] != 0 && x < tabAppariements.length-1) {
			int nb_parents = nuss.getAppariements(x, y);
			int nb_points = nuss.getIgnore(x, y);

			if (nb_parents != 0 && nb_points == 0) {
				x += nb_parents;
				y -= nb_parents;
			}
			else {
				int nb_points_g = nuss.getIgnore(x, y-1);
				
				if (nb_points-1 == nb_points_g) {
						y -= 1;
				}
				else {
						x += 1;
				}
			}

		}
		// Quand on a récuperé ces indices il est facile de savoir si la boucle terminale fait une bonne longueur
		return (y-x+1 <= 8);
	}


	/**
	 * Retourne tout les pré-Micro-ARN d'une séquence génomique.
	 * @return une liste avec les coordonnées des pré-Micro-ARN
	 */
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
			
			for (int i=0; i < size; i++) {
				// On parcours toute la liste en utilisant Boyer-Moore
				boyer = new RechercheBoyerMoore(br.getSequence().substring(result.get(i)[0], result.get(i)[1]+1), 0, false, false, false);
				res = boyer.chercherMotif(complement.getMotif());
				
				for (int x : res) {
					// Pour chaque resultat on regarde si l'indice est compris en 10 (9) et 15 (14).
					if (x > 9 && x < 15) {
						String hybride = br.getSequence().substring(result.get(i)[0], result.get(i)[1]+1);
						System.out.println("le messager s'hybride avec le micro-ARN du pré-micro-ARN suivant :");
						System.out.println(hybride);
					}
				}
				
			}
		}
	}
	
}
