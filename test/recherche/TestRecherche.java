package recherche;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import utils.Tools;
import entite.Brin;

public class TestRecherche {

	private Recherche r;
	private Recherche rboyer;
	
	@Before
	public void setUp(){
		r = new RechercheNaive("TACTACTAAACGGATC", 2, true, false, false);
		rboyer = new RechercheBoyerMoore("TACTACTAAACGGATC", 2, true, false, false);
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
	public void testBoyerRechercheSimple(){
		Brin b = new Brin("donnees/entitee.fasta");
		String sequence = b.getSequence();
		
		Recherche rboyer1 = new RechercheBoyerMoore(sequence, 2, true, false, false);
		Recherche rboyer2 = new RechercheBoyerMoore(sequence, 2, false, false, false);
		Recherche rboyer3 = new RechercheBoyerMoore(sequence, 2, false, true, false);
		Recherche rboyer4 = new RechercheBoyerMoore(sequence, 2, false, false, true);
		
		Recherche rnaive1 = new RechercheNaive(sequence, 2, false, false, false);
		Recherche rnaive2 = new RechercheNaive(sequence, 2, false, false, false);
		Recherche rnaive3 = new RechercheNaive(sequence, 2, false, true, false);
		Recherche rnaive4 = new RechercheNaive(sequence, 2, false, false, true);
		
		for (int i=0; i<100000; i++) {
			String motif = Tools.motifAlea();
			assertEquals("motif :"+motif,rboyer1.chercherMotif(motif), rnaive1.chercherMotif(motif));
			assertEquals("motif :"+motif,rboyer2.chercherMotif(motif), rnaive2.chercherMotif(motif));
			assertEquals("motif :"+motif,rboyer3.chercherMotif(motif), rnaive3.chercherMotif(motif));
			assertEquals("motif :"+motif,rboyer4.chercherMotif(motif), rnaive4.chercherMotif(motif));
		}
	}
	
	/**
	 * Test de la fonction rechercheComplete de BoyerMoore.
	 * En toute logique les resultats doivent être les mêmes que la recherche dite naïve.
	 */
	@Test
	public void testBoyerRechercheComplete(){
		Brin b = new Brin("donnees/entitee.fasta");
		String sequence = b.getSequence();
		
		Random rand = new Random();
		
		for (int i=0; i< 1000; i++) {
			int entier = rand.nextInt(19)+1;
			
			Recherche rboyer1 = new RechercheBoyerMoore(sequence, entier, true, false, false);
			Recherche rboyer2 = new RechercheBoyerMoore(sequence, entier, false, false, false);
			Recherche rboyer3 = new RechercheBoyerMoore(sequence, entier, false, true, false);
			Recherche rboyer4 = new RechercheBoyerMoore(sequence, entier, false, false, true);
			
			Recherche rnaive1 = new RechercheNaive(sequence, entier, false, false, false);
			Recherche rnaive2 = new RechercheNaive(sequence, entier, false, false, false);
			Recherche rnaive3 = new RechercheNaive(sequence, entier, false, true, false);
			Recherche rnaive4 = new RechercheNaive(sequence, entier, false, false, true);
			
			rboyer1.rechercheComplete();
			rnaive1.rechercheComplete();
			rboyer2.rechercheComplete();
			rnaive2.rechercheComplete();
			rboyer3.rechercheComplete();
			rnaive3.rechercheComplete();
			rboyer4.rechercheComplete();
			rnaive4.rechercheComplete();
			
			//assertEquals(rboyer1.resultats,rnaive1.resultats);
			assertEquals(rboyer2.resultats,rnaive2.resultats);
			//assertEquals(rboyer3.resultats,rnaive3.resultats);
			//assertEquals(rboyer4.resultats,rnaive4.resultats);

		}

	}
}
