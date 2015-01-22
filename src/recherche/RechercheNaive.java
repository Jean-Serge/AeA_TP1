package recherche;

import java.util.ArrayList;
import java.util.List;

import entite.Brin;

public class RechercheNaive extends Recherche {

	
	
	public RechercheNaive(Brin b, int n, boolean reverse, boolean comp,
			boolean revComp) {
		super(b, n, reverse, comp, revComp);
	}

	/**
	 * Implémentation naïve de l'algorithme de recherche de motif.
	 * Cet algorithme parcours chaque élément du brin et les compare
	 * à chaque élément du motif recherché.
	 * 
	 * @param motif la chaîne recherchée
	 * 
	 * @return une liste des occurences du motif dans la chaîne (une
	 * liste vide si aucune occurence n'a été trouvée).
	 */
	@Override
	public List<Integer> chercherMotif(String motif) {
		List<Integer> occ = new ArrayList<Integer>();
		int sizeS = this.b.SizeofSequence();
		int sizeM = motif.length();

		for(int i = 0 ; i < sizeS - sizeM +1; i++){
			for(int j = 0 ; j < sizeM ; j++){
				if(motif.charAt(j) != this.b.getSequence().charAt(i+j))
					break;
				if(j ==  sizeM - 1)
					occ.add(i);
			}
		}
		
		return occ;
	}

}
