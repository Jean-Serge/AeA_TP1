package entite;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import entite.Brin;

/**
 *  Tests de création de brins d'ADN.
 */

public class testBrin {
	
	// On construit les brins pour les tests
	private Brin brintest1;
	private Brin brintest2;
	
	@Before
	public void setUp(){
		brintest1 = new Brin("donnees/test1.fasta");
		brintest2 = new Brin("donnees/test2.fasta");
	}
	
	@Test
	public void TestConstructeur() {
		// On applique la fonction getSequence sur les brins
		String SeqB1 = brintest1.getSequence();
		String SeqB2 = brintest2.getSequence();
		
		// Tests :
		assertEquals("Le brin d'ADN 'test1.fasta' doit être 'GATACACA'.","GATACACA",SeqB1);
		
		assertEquals("Le brin d'ADN 'test2.fasta' doit être 'TATATATATATA'.","TATATATATATA",SeqB2);
	}
	
	@Test 
	public void TestTaille() {
		// On applique la fonction SizeofSequence sur les brins
		int tailleB1 = brintest1.SizeofSequence();
		int tailleB2 = brintest2.SizeofSequence();
		
		// Tests :
		assertEquals("Le brin d'ADN 'test1.fasta' doit avoir une taille de 8 (GATACACA).",8,tailleB1);
		
		assertEquals("Le brin d'ADN 'test2.fasta' doit avoir une taille de 12 (TATATATATATA).",12,tailleB2);

		
	}
	
	
	
}
