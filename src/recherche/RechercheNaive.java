package recherche;

import java.util.ArrayList;
import java.util.List;

import entite.Brin;

public class RechercheNaive implements RechercheInterface {

	
	@Override
	public List<Integer> chercherMotif(String motif, Brin b) {
		List<Integer> occ = new ArrayList<Integer>();
		int sizeS = b.SizeofSequence();
		int sizeM = motif.length();

		for(int i = 0 ; i < sizeS - sizeM +1; i++){
			for(int j = 0 ; j < sizeM ; j++){
				if(motif.charAt(j) != b.getSequence().charAt(i+j))
					break;
				if(j ==  sizeM - 1)
					occ.add(i);
			}
		}
		
		return occ;
	}

}
