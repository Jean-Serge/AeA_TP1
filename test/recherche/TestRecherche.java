package recherche;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import utils.Alea;
import entite.Brin;

public class TestRecherche {

	private Recherche rboyer1, rboyer2, rboyer3, rboyer4, rboyer5, rboyer6,
			rboyer7, rboyer8;
	private Recherche rnaive1, rnaive2, rnaive3, rnaive4, rnaive5, rnaive6,
			rnaive7, rnaive8;

	@Before
	public void setUp() {
		Brin b = new Brin("donnees/entitee.fasta");
		String sequence = b.getSequence();
		Random r = new Random();
		int entier = r.nextInt(20) + 1;

		rboyer1 = new RechercheBoyerMoore(sequence, entier, false, false, false);
		rboyer2 = new RechercheBoyerMoore(sequence, entier, false, false, true);
		rboyer3 = new RechercheBoyerMoore(sequence, entier, false, true, false);
		rboyer4 = new RechercheBoyerMoore(sequence, entier, false, true, true);
		rboyer5 = new RechercheBoyerMoore(sequence, entier, true, false, false);
		rboyer6 = new RechercheBoyerMoore(sequence, entier, true, false, true);
		rboyer7 = new RechercheBoyerMoore(sequence, entier, true, true, false);
		rboyer8 = new RechercheBoyerMoore(sequence, entier, true, true, true);

		rnaive1 = new RechercheNaive(sequence, entier, false, false, false);
		rnaive2 = new RechercheNaive(sequence, entier, false, false, true);
		rnaive3 = new RechercheNaive(sequence, entier, false, true, false);
		rnaive4 = new RechercheNaive(sequence, entier, false, true, true);
		rnaive5 = new RechercheNaive(sequence, entier, true, false, false);
		rnaive6 = new RechercheNaive(sequence, entier, true, false, true);
		rnaive7 = new RechercheNaive(sequence, entier, true, true, false);
		rnaive8 = new RechercheNaive(sequence, entier, true, true, true);
	}

	/**
	 * Test de la fonction initMap. Cette fonction ajoute des entrées dans la
	 * map en fonction du texte lu, cela implique que la recherche de chaque clé
	 * dans le texte retourne une chaîne non vide.
	 */
	@Test
	public void testInitMap() {
		List<Integer> liste = new ArrayList<Integer>();

		for (String s : rnaive1.getResultats().keySet()) {
			assertNotSame(liste, rboyer1.chercherMotif(s));
			assertNotSame(liste, rnaive1.chercherMotif(s));
			assertNotSame(liste, rboyer2.chercherMotif(s));
			assertNotSame(liste, rnaive2.chercherMotif(s));
			assertNotSame(liste, rboyer3.chercherMotif(s));
			assertNotSame(liste, rnaive3.chercherMotif(s));
			assertNotSame(liste, rboyer4.chercherMotif(s));
			assertNotSame(liste, rnaive4.chercherMotif(s));
			assertNotSame(liste, rboyer5.chercherMotif(s));
			assertNotSame(liste, rnaive5.chercherMotif(s));
			assertNotSame(liste, rboyer6.chercherMotif(s));
			assertNotSame(liste, rnaive6.chercherMotif(s));
			assertNotSame(liste, rboyer7.chercherMotif(s));
			assertNotSame(liste, rnaive7.chercherMotif(s));
			assertNotSame(liste, rboyer8.chercherMotif(s));
			assertNotSame(liste, rnaive8.chercherMotif(s));
		}
	}

	/**
	 * Test de la fonction chercherMotif de BoyerMoore. En toute logique les
	 * resultats doivent être les mêmes que la recherche dite naïve.
	 */
	@Test
	public void testBoyerRechercheSimple() {
		String motif;
		for (int i = 0; i < 100000; i++) {
			motif = Alea.motifAlea();
			// On n'en teste qu'un, la recherche d'un motif ne prend pas en
			// compte les options.
			assertEquals("motif :" + motif, rboyer1.chercherMotif(motif),
					rnaive1.chercherMotif(motif));
		}
	}

	/**
	 * Test de la fonction rechercheComplete de BoyerMoore. En toute logique les
	 * resultats doivent être les mêmes que la recherche dite naïve.
	 */
	@Test
	public void testBoyerRechercheComplete() {
		rboyer1.rechercheComplete();
		rnaive1.rechercheComplete();
		rboyer2.rechercheComplete();
		rnaive2.rechercheComplete();
		rboyer3.rechercheComplete();
		rnaive3.rechercheComplete();
		rboyer4.rechercheComplete();
		rnaive4.rechercheComplete();
		rboyer5.rechercheComplete();
		rnaive5.rechercheComplete();
		rboyer6.rechercheComplete();
		rnaive6.rechercheComplete();
		rboyer7.rechercheComplete();
		rnaive7.rechercheComplete();
		rboyer8.rechercheComplete();
		rnaive8.rechercheComplete();

		assertEquals(rboyer1.resultats, rnaive1.resultats); // resultats OK
		assertEquals(rboyer2.resultats, rnaive2.resultats); // resultats OK
		assertEquals(rboyer3.resultats, rnaive3.resultats); // resultats OK
		assertEquals(rboyer4.resultats, rnaive4.resultats); // resultats OK

		assertEquals(rboyer5.resultats, rnaive5.resultats); // resultats OK
		assertEquals(rboyer6.resultats, rnaive6.resultats); // resultats OK
		assertEquals(rboyer7.resultats, rnaive7.resultats); // resultats OK
		assertEquals(rboyer8.resultats, rnaive8.resultats); // resultats OK

	}

}
