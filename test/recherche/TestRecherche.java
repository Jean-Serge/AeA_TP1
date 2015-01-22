package recherche;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestRecherche {

	private Recherche r;
	
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
		}
	}
	
}
