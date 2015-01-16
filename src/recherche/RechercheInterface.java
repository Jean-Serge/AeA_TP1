package recherche;

import java.util.List;

import entite.Brin;

public interface RechercheInterface {

	public List<Integer> chercherMotif(String motif, Brin b);
}
