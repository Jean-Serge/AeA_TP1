package entite;

import utils.Nussinov;
/**
 * 
 * @author thibaud
 * @author monbailly
 *
 */
public class PreMicroARN extends Brin{

	//TODO : documentation !
	
	/**
	 * 
	 * @param filename
	 */
	public PreMicroARN(String filename) {
		super(filename);
	}
	
	public boolean isPreMicroARN() {
		Nussinov nussinov = new Nussinov(this.getSequence());
		nussinov.executeAlgo();
		int[][] results = nussinov.getResults();
		int nb_parent = results[0][results.length-1];
		
		if (nb_parent < 24 && this.getSequence().length() < 101)
			return false;
			
		int i_dep = 0;
		int j_dep = results.length-1;
		
		int nb_diag = 0;
		int nb_shift_down = 0;
		int nb_shift_left = 0;
		
		boolean drapeau = true;
		
		while (drapeau && nb_parent != 0) {
			if (results[i_dep][j_dep] == results[i_dep][j_dep-1]
					&& results[i_dep][j_dep] != results[i_dep+1][j_dep]) {
				if (nb_diag < 3 || nb_shift_left == 3)
					drapeau = false;
				nb_diag = 0;
				nb_shift_down = 0;
				nb_shift_left++;
				
				j_dep--;
			}
			else if (results[i_dep][j_dep] == results[i_dep+1][j_dep]
					&& results[i_dep][j_dep] != results[i_dep][j_dep-1]) {
				if (nb_diag < 3 || nb_shift_down == 3)
					drapeau = false;
				nb_diag = 0;
				nb_shift_left = 0;
				nb_shift_down++;
				
				i_dep++;
			}
			else if (results[i_dep][j_dep] == results[i_dep+1][j_dep]
					&& results[i_dep][j_dep] == results[i_dep][j_dep-1]) {
				if (nb_diag < 3 || nb_shift_down == 3) {
					if (nb_diag < 3 || nb_shift_left == 3)
						drapeau = false;
					nb_diag = 0;
					nb_shift_down = 0;
					nb_shift_left++;
					
					j_dep--;
				}
				else {
					nb_diag = 0;
					nb_shift_left = 0;
					nb_shift_down++;
					
					i_dep++;
				}
			}
			else {
				i_dep++;
				j_dep--;
				nb_diag++;
				nb_shift_left = 0;
				nb_shift_down = 0;
				nb_parent--;
			}
		}
		if (j_dep - i_dep < 8)
			return false;
		
		return drapeau;
		
	}
	
	
	public static void main(String[]argv) {	
		PreMicroARN pmarn = new PreMicroARN("donnees/arn.fasta");
		System.out.println(pmarn.isPreMicroARN());
		PreMicroARN pmarn2 = new PreMicroARN("donnees/pasarn.fasta");
		System.out.println(pmarn2.isPreMicroARN());
	}

}
