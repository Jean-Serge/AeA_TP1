package main;

import java.util.Random;

import recherche.Recherche;
import recherche.RechercheBoyerMoore;
import recherche.RechercheNaive;
import entite.Brin;

public class Temps {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Recherche rboyer1, rboyer2, rboyer3, rboyer4, rboyer5, rboyer6, rboyer7, rboyer8;
		Recherche rnaive1, rnaive2, rnaive3, rnaive4, rnaive5, rnaive6, rnaive7, rnaive8;
		long debut, fin;
		Brin b = new Brin("donnees/enorme.fasta");
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
		
		System.out.println("N = "+entier);
		
		long tps_boyer, tps_naif;
		debut = System.currentTimeMillis();
		rboyer1.rechercheComplete();
		fin = System.currentTimeMillis();
		tps_boyer = fin-debut;
		
		debut = System.currentTimeMillis();
		rnaive1.rechercheComplete();
		fin = System.currentTimeMillis();
		tps_naif = fin-debut;
		System.out.println("La recherche Boyer 1 a pris "+ tps_boyer +" ms contre " + tps_naif +" pour la recherche naïve.");
	}

}
