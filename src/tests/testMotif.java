package tests;

import entite.Motif;

/**
 *  Tests de création de motifs.
 */

public class testMotif {
	
	public static void main(String[] args) {
		// On construit les motif pour les tests
		Motif motiftest1 = new Motif("GAGATACA");
		Motif motiftest2 = new Motif("CGTA");
		// On applique la fonction SizeofMotif sur les motifs
		int tailleM1 = motiftest1.SizeofMotif();
		int tailleM2 = motiftest2.SizeofMotif();
		// On applique la fonction getMotif sur les motifs
		String Mot1 = motiftest1.getMotif();
		String Mot2 = motiftest2.getMotif();
		// On applique la fonction Reverse sur les motifs
		String revM1 = motiftest1.Reverse().getMotif();
		String revM2 = motiftest2.Reverse().getMotif();
		// On applique la fonction Complementary sur les motifs
		String compM1 = motiftest1.Complementary().getMotif();
		String compM2 = motiftest2.Complementary().getMotif();
		// On applique la fonction ReverseComplementary sur les motifs 
		String rcM1 = motiftest1.ReverseComplementary().getMotif();
		String rcM2 = motiftest2.ReverseComplementary().getMotif();
		// On applique la fonction SetMotif sur le motif1
		motiftest1.setMotif("TATA");
		String newMot1 = motiftest1.getMotif();
		
		// On vérifie les résultats :
		if (8 != tailleM1) 
            System.out.println("KO : Le motif1 doit avoir une taille de 8 (GAGATACA).");
		if (4 != tailleM2)
			 System.out.println("KO : Le motif2 doit avoir une taille de 4 (CGTA).");
		if (!"GAGATACA".equals(Mot1))
			 System.out.println("KO : Le motif1 doit être 'GAGATACA'.");
		if (!"CGTA".equals(Mot2))
			 System.out.println("KO : Le motif2 doit être 'CGTA'.");
		// tests de la méthode CharComplementary
		if (Motif.CharComplementary('G') != 'C' || Motif.CharComplementary('C') != 'G'
				|| Motif.CharComplementary('T') != 'A' || Motif.CharComplementary('A') != 'T') 
			System.out.println("KO : Il y a un problème avec la méthode CharComplementary.");
		
		if (!"ACATAGAG".equals(revM1))
			 System.out.println("KO : Le reverse du motif1 doit être 'ACATAGAG'.");
		if (!"ATGC".equals(revM2))
			 System.out.println("KO : Le reverse du motif2 doit être 'ATGC'.");
		if (!"CTCTATGT".equals(compM1))
			 System.out.println("KO : Le complémentaire du motif1 doit être 'CTCTATGT'.");
		if (!"GCAT".equals(compM2))
			 System.out.println("KO : Le complémentaire du motif2 doit être 'GCAT'.");
		if (!"TGTATCTC".equals(rcM1))
			 System.out.println("KO : Le reverse-complémentaire du motif1 doit être 'TGTATCTC'.");
		if (!"TACG".equals(rcM2))
			 System.out.println("KO : Le reverse-complémentaire du motif2 doit être 'TACG'.");
		
		if (!"TATA".equals(newMot1))
			 System.out.println("KO : Le motif1 doit être 'TATA' suite à l'application de setMotif.");
		
		System.out.println("tests OK");
		
	}
	
	
	
}
