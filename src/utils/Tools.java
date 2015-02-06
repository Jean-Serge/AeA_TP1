package utils;

import java.util.Random;

/**
 * Classe proposant des fonctions statiques utilisable dans le projet.
 * 
 * @author monbailly
 * 
 */
public class Tools {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	/**
	 * Cette fonction permet de savoir si une chaîne de caractères est un
	 * nombre. Ne permet de lire que des entiers.
	 * 
	 * @param s
	 *            la chaîne à observer
	 * @return true si la chaine est un nombre, false sinon
	 */
	public static boolean isInteger(String s) {
		if (s.isEmpty())
			return false;

		// Vérifie si chaque caractère de la chaîne est un digit
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}
		return true;
	}

	/**
	 * Cette fonction permet de créer un motif de taille et valeur aléatoires.
	 * 
	 * @return le motif aléatoire crée
	 */
	public static String motifAlea() {
		// On créé une taille comprise entre 1 et 20.
		Random rand = new Random();
		int lenght = rand.nextInt(19) + 1;

		String motif = "";

		// Pour chaque lettre du motif on ajoute un nucléotide au hasard.
		for (int i = 0; i < lenght; i++) {
			int letter = rand.nextInt(3);
			motif += int2Nucleo(letter);
		}

		return motif;
	}

	/**
	 * Cette fonction convertie un entier passé en paramètre en nucléotide
	 * (G,C,A ou T).
	 * 
	 * @param letter
	 *            l'indice du nucléotide
	 * @return le nucléotide indicé par l'entier entré en paramètre
	 */
	private static String int2Nucleo(int letter) {
		switch (letter) {
		case 0:
			return "A";
		case 1:
			return "T";
		case 2:
			return "G";
		case 3:
			return "C";
		default:
			return "";
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
		int val = new Random().nextInt(3);

		switch (c) {
		case 'C':
			switch (val) {
			case 0:
				return "C";
			case 1:
				return "A";
			default:
				return "U";
			}
		case 'A':
			switch (val) {
			case 0:
				return "C";
			case 1:
				return "G";
			default:
				return "A";
			}
		case 'G':
			switch (val) {
			case 0:
				return "A";
			default:
				return "G";
			}
		default:
			switch (val) {
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
		int val = new Random().nextInt(3);

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
	public static String genPreMicroARN() {
		Random rand = new Random();
		int taille = 70 + rand.nextInt(31);
		int debut = 10 + rand.nextInt(6);

		String pmARN = "";
		for (int i = 0; i < debut; i++)
			pmARN += int2Nucleo(rand.nextInt(4));

		// On ajoute le micro ARN (en rouge pour Thibaud)
		pmARN += Tools.ANSI_RED;
		pmARN += genMicroARN();
		pmARN += Tools.ANSI_RESET;
		
		//	On bourre jusque la moitié de la chaîne		
		for (int i = pmARN.length(); i < taille / 2; i++)
			pmARN += int2Nucleo(rand.nextInt(4));

		pmARN = pmARN.replace('T', 'U');
		
		// 	---------- Gestion des Appariements ---------
		int nbApp = 24 + rand.nextInt(27);
//		pmARN.

		return pmARN;
	}

	/**
	 * 
	 * @return
	 */
	private static String genMicroARN() {
		Random rand = new Random();
		String retour = "";
		int taille = 20 + rand.nextInt(4);
		for (int i = 0; i < taille; i++)
			retour += int2Nucleo(rand.nextInt(4));
		return retour;
	}

	public static void main(String[] argv) {
		String s;
		// s = genMicroARN();
		// System.out.println(s+" ["+s.length()+"]");

		s = genPreMicroARN();
		System.out.println(s + " [" + s.length() + "]");
	}
}
