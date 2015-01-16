package tests;

import entite.Brin;

public class testBrin {
	
	public static void main(String[] args) {
		Brin brin = new Brin("donnees/entitee.fasta");
		System.out.println(brin.getSequence());
	}
	
	
	
}
