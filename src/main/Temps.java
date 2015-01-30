package main;

import java.util.Random;

import recherche.Recherche;
import recherche.RechercheBoyerMoore;
import entite.Brin;

public class Temps {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Recherche recherche;
		long debut, fin;
		Brin b = new Brin("donnees/enorme.fasta");
		String sequence = b.getSequence();
		System.out.println("Taille du brin : "+sequence.length());
		Random r = new Random();
		int entier = r.nextInt(20) + 1;
		entier = 20;
		recherche = new RechercheBoyerMoore(sequence, entier, true, true, true);

		
		System.out.println("N = "+entier);
		
		long tps_boyer;
		debut = System.currentTimeMillis();
		recherche.rechercheComplete();
		fin = System.currentTimeMillis();
		tps_boyer = fin-debut;
		
		System.out.println("La recherche a pris "+ tps_boyer +" ms.");
	}

}
