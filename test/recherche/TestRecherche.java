package recherche;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestRecherche {

	private Recherche r;
	private Recherche rboyer;
	
	@Before
	public void setUp(){
		r = new RechercheNaive("TACTACTAAACGGATC", 2, true, false, false);
	}
	
	/**
	 * Test de la fonction initMap.
	 * Cette fonction ajoute des entrées dans la map en fonction
	 * du texte lu, cela implique que la recherche de chaque clé 
	 * dans le texte retourne une chaîne non vide.
	 */
	@Test
	public void testInitMap(){
		for(String s : r.getResultats().keySet()){
			assertNotSame(new ArrayList<Integer>(), r.chercherMotif(s));
			assertNotSame(new ArrayList<Integer>(), rboyer.chercherMotif(s));
		}
	}
	
	/**
	 * Test de la fonction chercherMotif de BoyerMoore.
	 * En toute logique les resultats doivent être les mêmes que la recherche dite naïve.
	 */
	@Test
	public void testBoyer(){
		rboyer = new RechercheBoyerMoore("TACTACTAAACGGATC", 2, true, false, false);
		assertEquals(rboyer.chercherMotif("A"), r.chercherMotif("A"));
		assertEquals(rboyer.chercherMotif("AGG"), r.chercherMotif("AGG"));
		assertEquals(rboyer.chercherMotif("AC"), r.chercherMotif("AC"));

	}
	
}
