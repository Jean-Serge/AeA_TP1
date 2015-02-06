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
	
	/**
	 * @return
	 */
	public static String[] genAppariements() {
		
		String[] retour = new String[2];
		Random rand = new Random();
		int i = rand.nextInt(4);
		
		switch (i) {
		case 0:
			retour[0] = "A";
			retour[1] = "U";
			break;
		case 1:
			retour[0] = "G";
			if (rand.nextInt(2) == 0)
				retour[1] = "C";
			else
				retour[1] = "U";
			break;
		case 2:
			retour[0] = "U";
			if (rand.nextInt(2) == 0)
				retour[1] = "A";
			else
				retour[1] = "G";
			break;
		default:
			retour[0] = "C";
			retour[1] = "G";
			break;
		}
		return retour;
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public static String nonAppariements(char c) {
		int val  = new Random().nextInt(3);
		
		switch (c) {
		case 'C':
			switch(val) {
			case 0:
				return "C";
			case 1:
				return "A";
			default:
				return "U";
			}
		case 'A':
			switch(val) {
			case 0:
				return "C";
			case 1:
				return "G";
			default:
				return "A";
			}
		case 'G':
			switch(val) {
			case 0:
				return "A";
			default:
				return "G";
			}
		default:
			switch(val) {
			case 0:
				return "U";
			default:
				return "C";
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static String[] nonAppariements() {
		String[] retour = new String[2];
		int val  = new Random().nextInt(3);
		
		switch (val) {
		case 0:
			retour[0] = "A";
			retour[1] = nonAppariements('A');
			break;
		case 1:
			retour[0] = "G";
			retour[1] = nonAppariements('G');
			break;
		case 2:
			retour[0] = "C";
			retour[1] = nonAppariements('U');
			break;
		default:
			retour[0] = "U";
			retour[1] = nonAppariements('U');
			break;
		}
		return retour;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String genPreMicoARN() {
		Random rand = new Random();
		int taille = 70 + rand.nextInt(31);
		//TODO
		return null;
	}
}
