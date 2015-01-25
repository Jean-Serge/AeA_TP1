package utils;

import java.util.Random;

/**
 * Classe proposant des fonctions statiques utilisable 
 * dans le projet.
 * @author monbailly
 *
 */
public class Tools {

	/**
	 * Cette fonction permet de savoir si une chaîne de
	 * caractères est un nombre.
	 * Ne permet de lire que des entiers.
	 * @param s la chaîne à observer
	 * @return true si la chaine est un nombre, false sinon
	 */
	public static boolean isInteger(String s){
		if(s.isEmpty())
			return false;
		
		// Vérifie si chaque caractère de la chaîne est un digit
		for(int i = 0 ; i < s.length() ; i++){
			if(!Character.isDigit(s.charAt(i)))
				return false;
		}
		return true;
	}
	
	/**
	 * Cette fonction permet de créer un motif de taille et valeur aléatoires.
	 * @return le motif aléatoire crée
	 */
	public static String motifAlea() {
		// On créé une taille comprise entre 1 et 20.
		Random rand = new Random();
		int lenght = rand.nextInt(19)+1;
		
		String motif = "";
		
		// Pour chaque lettre du motif on ajoute un nucléotide au hasard.
		for (int i=0; i<lenght; i++) {
			int letter = rand.nextInt(3);
			motif += int2Nucleo(letter);
		}
		
		return motif;
	}

	/**
	 * Cette fonction convertie un entier passé en paramètre en nucléotide (G,C,A ou T).
	 * @param letter l'indice du nucléotide
	 * @return le nucléotide indicé par l'entier entré en paramètre
	 */
	private static String int2Nucleo(int letter) {
		switch (letter) {
			case 0: return "A";
			case 1: return "T";
			case 2: return "G";
			case 3: return "C";
			default: return "";
		}
	}
}
