package detection;

/**
 * Classe calculant dynamiquement le nombre maximum d'appariements.
 * 
 * @author verbaere
 * @author monbailly
 *
 */
public class MaxParenthesage {

	private String sequence; // Sequence ADN
	
	private int[][] tab; // Tableau contenant le maximum d'appariements possibles
	private int[][] tab_points; // Tableau contenant le nombre de points (mismatch)
	private int[][] tab_parents; // Tableau contenant le nombre de parentheses (match)
	
	
	/**
	 * Constructeur
	 * @param seq la séquence à analyser
	 */
	public MaxParenthesage(String seq) {
		this.sequence = seq;
		this.tab = new int[seq.length()][seq.length()];
		this.tab_points = new int[seq.length()][seq.length()];
		this.tab_parents = new int[seq.length()][seq.length()];
	}
	
	/**
	 * Maximise le nombre d'appariements par lots de trois minimum et en limitant
	 * le nombre de mismatch à trois maximum (hors boucle terminale).
	 */
	public void executeAlgo() {
						
		int diag;
		int bas;
		int gauche;
		
		// On remplit le tableau de diagonale en diagonale (gauche vers la droite).
		for (int sup=1; sup < this.tab.length; sup++) {
			for (int i=0; i<this.tab.length-sup; i++) {
				int j = i+sup;
				
				/* On calcule les trois valeurs possibles :
				 * 		- Nombre d'appariements pour seq[1+i][j-1] + w(i,j) 
				 * 			avec w valant 1 si seq[i] appariée à seq[j], 0 sinon
				 * 		- Nombre d'appariements pour seq[i+1][j] 
				 * 		- Nombre d'appariements pour seq[i][j-1]
				 */
				diag = this.tab[i+1][j-1] + w(this.sequence.charAt(i), this.sequence.charAt(j));
				bas = this.tab[i+1][j];
				gauche = this.tab[i][j-1];

				/* On attribut pour tab[i][j] le max des valeurs possibles
				* Attention ce n'est pas un max traditionnel, il y a des contraintes 
				* spécifiques liées au mismatch et au match.
				* */
				this.max(diag,bas,gauche,i,j);
			}
		}
	}
	
	
	/**
	 * Fonction se chargeant d'attribuer la valeur de la case tab[i][j].
	 * @param diag le nombre d'appariements à la case tab[i+1][j-1] + w(i,j)
	 * @param bas le nombre d'appariements à la case tab[i+1][j]
	 * @param gauche le nombre d'appariements à la cas tab[i][j-1]
	 * @param i l'indice de la ligne à remplir dans tab
	 * @param j l'indice de la colonne à remplir dans tab
	 */
	private void max(int diag, int bas, int gauche, int i, int j) {

		if (diag >= bas && diag >= gauche) {
			
			// Si "diag" à un score supérieur ou égal aux deux autres alors on attribut ce score à tab[i][j]
			this.tab[i][j] = diag;

			if (w(this.sequence.charAt(i), this.sequence.charAt(j)) == 1) {
				// si w(i,j) vaut 1, on a un match (appariement)
				this.tab_parents[i][j] = this.tab_parents[i+1][j-1] + 1; // du coup on rajoute un parenthesage
				this.tab_points[i][j] = 0; // S'il y avait des mismatch alors on remet le compteur à zéro
			}
			else if (this.tab_parents[i+1][j-1] > 2 || this.tab_points[i+1][j-1] < 3){

				if (this.tab_parents[i+1][j-1] != 0 || this.tab_points[i+1][j-1] !=0) {
					// Sinon alors on rajoute un mismatch (si c'est possible : nb_mismatch < 3)
					this.tab_points[i][j] = this.tab_points[i+1][j-1]+1;
				}
				
				//  SINON TOUT A ZERO, on est bloqué pour cette configuration là !
			}
		}
		else {
			// Si c'est soit la case sur la gauche de tab[i][j], soit celle du dessous
			// alors on regarde si leurs configurations permettent ou non de poursuivre si l'on a un éventuel mismatch :
			boolean bas_ok = this.tab_points[i+1][j] < 3 && (this.tab_parents[i+1][j] > 2 || this.tab_parents[i+1][j] == 0);
			boolean gauche_ok = this.tab_points[i][j-1] < 3 && (this.tab_parents[i][j-1] > 2 || this.tab_parents[i][j-1] == 0);
		
			if (bas > gauche && bas_ok) {
				// Si la case en dessous de tab[i][j] à un score plus grand et qu'on est pas bloqué,
				// Alors on attribut à tab[i][j] ce score, on rajoute un mismatch et on met à 0 le compteur de parenthesage
				this.tab[i][j] = bas;
				this.tab_parents[i][j] = 0;
				this.tab_points[i][j] = this.tab_points[i+1][j]+1;
			}
			else if (bas > gauche && !bas_ok) {
				if (gauche_ok) {
					// Si la case en dessous de tab[i][j] à un score plus grand mais qu'on est bloqué, alors que la case de gauche non,
					// Alors on attribut à tab[i][j] le score de la case de gauche, on rajoute un mismatch et on met à 0 le compteur de parenthesage
					this.tab[i][j] = gauche;
					this.tab_parents[i][j] = 0;
					this.tab_points[i][j] = this.tab_points[i][j-1]+1;
				}
				
				//  SINON TOUT A ZERO, on est bloqué pour cette configuration là !
			}
			else if (gauche > bas && gauche_ok) {
				// Si la case à gauche de tab[i][j] à un score plus grand et qu'on est pas bloqué,
				// Alors on attribut à tab[i][j] ce score, on rajoute un mismatch et on met à 0 le compteur de parenthesage
				this.tab[i][j] = gauche;
				this.tab_parents[i][j] = 0;
				this.tab_points[i][j] = this.tab_points[i][j-1]+1;
			}
			else if (gauche > bas && !gauche_ok) {
				if (bas_ok) {
					// Si la case à gauche de tab[i][j] à un score plus grand mais qu'on est bloqué, alors que la case du dessous non,
					// Alors on attribut à tab[i][j] le score de la case du dessous, on rajoute un mismatch et on met à 0 le compteur de parenthesage
					this.tab[i][j] = bas;
					this.tab_parents[i][j] = 0;
					this.tab_points[i][j] = this.tab_points[i+1][j]+1;
				}
				
				//  SINON TOUT A ZERO, on est bloqué pour cette configuration là !
			}
			else if (bas == gauche && gauche_ok && bas_ok) {
				// Cas ou l'on a les case de gauche et du dessous avec un même score et qu'il n'y a pas de blocage
				// La priorité est à la case ayant le moins de mismatch
				if (this.tab_points[i][j-1] < this.tab_points[i+1][j]) {
					// s'il y a moins de mismatch pour la case sur la gauche de tab[i][j]
					// Alors on attribut son score à tab[i][j] ...
					this.tab[i][j] = gauche;
					this.tab_parents[i][j] = 0;
					this.tab_points[i][j] = this.tab_points[i][j-1]+1;
				}
				else {
					// s'il y a moins de mismatch pour la case du dessous de tab[i][j]
					// Alors on attribut son score à tab[i][j] ...
					this.tab[i][j] = bas;
					this.tab_parents[i][j] = 0;
					this.tab_points[i][j] = this.tab_points[i+1][j]+1;
				}
			}
			else if (bas == gauche && bas_ok && !gauche_ok) {
				// Si la configuration de la case gauche est bloqué, pas le choix
				// On attribut le score de l'autre case à tab[i][j] ...
				this.tab[i][j] = bas;
				this.tab_parents[i][j] = 0;
				this.tab_points[i][j] = this.tab_points[i+1][j]+1;
			}
			else if (bas == gauche && gauche_ok && !bas_ok) {
				// Si la configuration de la case du dessous est bloqué, pas le choix
				// On attribut le score de l'autre case à tab[i][j] ...
				this.tab[i][j] = gauche;
				this.tab_parents[i][j] = 0;
				this.tab_points[i][j] = this.tab_points[i][j-1]+1;
			}
			else {
				//  SINON TOUT A ZERO, on est bloqué pour cette configuration là !
			}
		}
	}

	
	/**
	 * Affiche les matrices de programmation dynamique associées à l'algorithme.
	 */
	public void printTab() {
		System.out.println("----- resultats --------\n");

		for (int i=0; i< this.tab.length; i++) {
			for (int j=0; j< this.tab.length; j++)
				System.out.print(this.tab[i][j]+"\t");
			System.out.println();
		}
		
		System.out.println("\n----- parentheses --------\n");

		for (int i=0; i< this.tab.length; i++) {
			for (int j=0; j< this.tab.length; j++)
				System.out.print(this.tab_parents[i][j]+"\t");
			System.out.println();
		}
		
		System.out.println("\n----- points --------\n");

		for (int i=0; i< this.tab.length; i++) {
			for (int j=0; j< this.tab.length; j++)
				System.out.print(this.tab_points[i][j]+"\t");
			System.out.println();
		}
	}
	
	
	/**
	 * Retourne 1 si c1 et c2 sont appariées et 0 sinon.
	 * @param c1 une première nucléotide
	 * @param c2 une seconde nucléotide
	 * @return 0 ou 1
	 */
	public static int w(char c1, char c2) {
		switch (c1) {
		case 'A':
			if (c2 == 'T')
				return 1;
			else
				return 0;
		case 'C':
			if (c2 == 'G')
				return 1;
			else
				return 0;
		case 'G':
			if (c2 == 'C' || c2 == 'T')
				return 1;
			else
				return 0;
		default:
			if (c2 == 'A' || c2 == 'G')
				return 1;
			else 
				return 0;			
		}
	}
	
	
	/**
	 * Retourne le tableau de resultats
	 * @return le tableau contenant les resultats sur le nombre maximum d'appariements
	 */
	public int[][] getResults() {
		return this.tab;
	}
	
	/**
	 * Retourne le nombre d'appariements successifs contenu dans le tableau
	 * des appariements aux coordonnées i,j
	 * @param i numéro de ligne
	 * @param j numéro de colonne
	 * @return le nombre d'appariements successifs à la case i,j
	 */
	public int getAppariements(int i, int j) {
		return this.tab_parents[i][j];
	}
	
	
	/**
	 * Retourne le nombre de nucléotides successifs ignoré contenu dans le tableau
	 * des "points" aux coordonnées i,j
	 * @param i numéro de ligne
	 * @param j numéro de colonne
	 * @return le nombre de nucléotides successivement ignorées à la case i,j
	 */
	public int getIgnore(int i, int j) {
		return this.tab_points[i][j];
	}
		
}
