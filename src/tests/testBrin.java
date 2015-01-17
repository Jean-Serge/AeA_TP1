package tests;

import entite.Brin;

/**
 *  Tests de création de brins d'ADN.
 */

public class testBrin {
	
	public static void main(String[] args) {
		// On construit les brins pour les tests
		Brin brintest1 = new Brin("donnees/test1.fasta");
		Brin brintest2 = new Brin("donnees/test2.fasta");
		// On applique la fonction SizeofSequence sur les brins
		int tailleB1 = brintest1.SizeofSequence();
		int tailleB2 = brintest2.SizeofSequence();
		// On applique la fonction getSequence sur les brins
		String SeqB1 = brintest1.getSequence();
		String SeqB2 = brintest2.getSequence();
		
		// On vérifie les résultats :
		if (8 != tailleB1) 
            System.out.println("KO : Le brin d'ADN 'test1.fasta' doit avoir une taille de 8 (GATACACA).");
		if (12 != tailleB2)
			 System.out.println("KO : Le brin d'ADN 'test2.fasta' doit avoir une taille de 12 (TATATATATATA).");
		if (!"GATACACA".equals(SeqB1))
			 System.out.println("KO : Le brin d'ADN 'test1.fasta' doit être 'GATACACA'.");
		if (!"TATATATATATA".equals(SeqB2))
			 System.out.println("KO : Le brin d'ADN 'test2.fasta' doit être 'TATATATATATA'.");
		
		System.out.println("tests OK");
		
	}
	
	
	
}
