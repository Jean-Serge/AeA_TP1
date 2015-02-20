package utils;

/**
 * Classe effectuant l'algorithme de Nussinov.
 * @author thibaud
 * @author monbailly
 *
 */
public class Nussinov {

	private String sequence;
	
	private int[][] tab;
	
	
	/**
	 * Constructeur Nussirov (algo de comptage de parenthèses)
	 * @param seq la séquence à analyser
	 */
	public Nussinov(String seq) {
		this.sequence = seq;
		this.tab = new int[seq.length()][seq.length()];
	}
	
	/**
	 * Execute l'algorithme de Nussinov (On cherche a maximiser le nombre d'appariements).
	 */
	public void executeAlgo() {
		// Initialisation du tableau pour la programmation dynamique :
		for (int i=0; i< this.sequence.length()-1; i++) {
			this.tab[i][i] = 0;
			this.tab[i+1][i] = 0;
		}
		this.tab[this.tab.length-1][this.tab.length-1] = 0;
		
		// Début de l'algo de Nussinov :
		
		int case1;
		int case2;
		int case3;
		int case4;
		
		for (int sup=1; sup < this.tab.length; sup++) {
			for (int i=0; i<this.tab.length-sup; i++) {
				int j = i+sup;
				case1 = this.tab[i+1][j-1] + this.w(this.sequence.charAt(i), this.sequence.charAt(j));
				case2 = this.tab[i+1][j];
				case3 = this.tab[i][j-1];
				case4 = this.case4(i,j);
				
				this.tab[i][j] = this.max(case1,case2,case3,case4);
			}
		}
	}
	
	private int case4(int i, int j) {
		int max = 0;
		for (int k=i+1; k<j ; k++) {
			if ((this.tab[i][k] + this.tab[k+1][j]) >= max)
				max = this.tab[i][k] + this.tab[k+1][j];
		}
		return max;
	}

	/**
	 * Retourne la valeur maximale entre quatre valeurs.
	 * @param cas1
	 * @param cas2
	 * @param cas3
	 * @param cas4
	 * @return la plus grande valeur des quatres.
	 */
	private int max(int cas1, int cas2, int cas3, int cas4) {
		int max1 = Math.max(cas1,cas2);
		int max2 = Math.max(cas3,cas4);
		return Math.max(max1,max2);
	}

	/**
	 * Affiche la matrice de programmation dynamique associée à l'algorithme.
	 */
	public void printTab() {
		for (int i=0; i< this.tab.length; i++) {
			for (int j=0; j< this.tab.length; j++)
				System.out.print(this.tab[i][j]);
			System.out.println();
		}
	}
	
	public int w(char c1, char c2) {
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
	
	public int[][] getResults() {
		return this.tab;
	}
	
	public static void main(String[] argv) {
		Nussinov nus = new Nussinov("TGCTTCCGGCCTGTTCCCTGAGACCTCAAGTGTGAGTGTACTATTGATGCTTCACACCTGGGCTCTCCGGGTACCAGGACGGTTTGAGCA");
		nus.executeAlgo();
		nus.printTab();
	}
	
}
