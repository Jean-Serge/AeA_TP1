package tests;

import junit.framework.*;
import org.junit.Test;
import entite.Motif;

/**
 *  Tests de création de motifs.
 */

public class testMotif {
	
	// On construit les motif pour les tests
	Motif motiftest1 = new Motif("GAGATACA");
	Motif motiftest2 = new Motif("CGTA");
	
	@Test
	public void TestCharComplementary() {
		// tests de la méthode CharComplementary
		Assert.assertEquals("Le complémentaire de G est C.",'C',Motif.CharComplementary('G'));
		Assert.assertEquals("Le complémentaire de C est G.",'G',Motif.CharComplementary('C'));
		Assert.assertEquals("Le complémentaire de A est T.",'T',Motif.CharComplementary('A'));
		Assert.assertEquals("Le complémentaire de T est A.",'A',Motif.CharComplementary('T'));
	}
	
	@Test
	public void TestSizeOf() {
		// On applique la fonction SizeofMotif sur les motifs
		int tailleM1 = motiftest1.SizeofMotif();
		int tailleM2 = motiftest2.SizeofMotif();
		
		// Tests :
		Assert.assertEquals("Le motif1 doit avoir une taille de 8 (GAGATACA).",8,tailleM1);

		Assert.assertEquals("Le motif1 doit avoir une taille de 4 (CGTA).",4,tailleM2);
	}
	
	@Test
	public void TestGetMotif() {
		// On applique la fonction getMotif sur les motifs
		String Mot1 = motiftest1.getMotif();
		String Mot2 = motiftest2.getMotif();
		
		// Tests :
		Assert.assertEquals("Le motif1 doit être 'GAGATACA'.","GAGATACA",Mot1);

		Assert.assertEquals("Le motif2 doit être 'CGTA'.","CGTA",Mot2);
	}
	
	@Test
	public void TestReverse() {
		// On applique la fonction Reverse sur les motifs
		String revM1 = motiftest1.Reverse().getMotif();
		String revM2 = motiftest2.Reverse().getMotif();
		
		// Tests :
		Assert.assertEquals("Le reverse du motif1 doit être 'ACATAGAG'.","ACATAGAG",revM1);
		
		Assert.assertEquals("Le reverse du motif2 doit être 'ATGC'.","ATGC",revM2);
	}
	
	@Test
	public void TestComplementary() {
		// On applique la fonction Complementary sur les motifs
		String compM1 = motiftest1.Complementary().getMotif();
		String compM2 = motiftest2.Complementary().getMotif();
		
		// Tests :
		Assert.assertEquals("Le complémentaire du motif1 doit être 'CTCTATGT'.","CTCTATGT",compM1);
		
		Assert.assertEquals("Le complémentaire du motif2 doit être 'GCAT'.","GCAT",compM2);
	}
	
	@Test
	public void TestReverseComplementary() {
		// On applique la fonction ReverseComplementary sur les motifs 
		String rcM1 = motiftest1.ReverseComplementary().getMotif();
		String rcM2 = motiftest2.ReverseComplementary().getMotif();
		
		// Tests :
		Assert.assertEquals("Le reverse-complémentaire du motif1 doit être 'TGTATCTC'.","TGTATCTC",rcM1);
		
		Assert.assertEquals("Le reverse-complémentaire du motif2 doit être 'TACG'.","TACG",rcM2);
	}
	
	@Test
	public void TestSetMotif() {
		// On applique la fonction SetMotif sur le motif1
		motiftest1.setMotif("TATA");
		String newMot1 = motiftest1.getMotif();
		

		Assert.assertEquals("Le motif1 doit être 'TATA' suite à l'application de setMotif.","TATA",newMot1);
				
	}
	
	
	
}
