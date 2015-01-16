import java.util.List;

import entite.Brin;
import recherche.RechercheInterface;
import recherche.RechercheNaive;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RechercheInterface recherche = new RechercheNaive();
		Brin b = new Brin("donnees/entitee.fasta");
		List<Integer> liste = recherche.chercherMotif("AT", b);
		System.out.println(b.getSequence());
		System.out.println(liste);
		
	}

}
