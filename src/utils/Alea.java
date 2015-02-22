package utils;

import java.util.Random;

/**
 * Classe de génération aléatoire.
 * @author verbaere
 * @author monbailly
 *
 */
public class Alea {
	
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
	 * @param letter l'indice du nucléotide
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
	 * Permet de générer deux nucléotides aléatoirement appariées.
	 * @return un tableau contenant deux nucléotides appariées
	 */
	public static String[] genAppariements() {

		String[] retour = new String[2];
		Random rand = new Random();
		
		// On genère un nombre aléatoirement entre 0 et 3 :
		int i = rand.nextInt(4);

		switch (i) {
		case 0: // si i vaut 0 alors la premiere nucléotide est A, la deuxième est
			// forcément T.
			retour[0] = "A";
			retour[1] = "T";
			break;
		case 1: // si i vaut 1 alors la première nucléotide est G.
			retour[0] = "G";
			// On a deux choix pour la deuxième nucléotide :
			if (rand.nextInt(2) == 0)
				retour[1] = "C";
			else
				retour[1] = "T";
			break;
		case 2: // si i vaut 2 alors la première nucléotide est T.
			retour[0] = "T";
			// On a deux choix pour la deuxième nucléotide :
			if (rand.nextInt(2) == 0)
				retour[1] = "A";
			else
				retour[1] = "G";
			break;
		default: // Sinon dernier couple possible C et G.
			retour[0] = "C";
			retour[1] = "G";
			break;
		}
		return retour;
	}

	/**
	 * Retourne une nucléotide non appariéé à la nucléotide passée en paramètre.
	 * @param c la nucléotide dont on veut connaitre un non-appariement.
	 * @return le non-appariement pour la nucléotide.
	 */
	public static String nonAppariements(char c) {
		int val = new Random().nextInt(3);
		// Il s'agit là de switcher c et de selon un nombre tiré aléatoirement choisir un cas possible.
		switch (c) {
		case 'C':
			switch (val) {
			case 0:
				return "C";
			case 1:
				return "A";
			default:
				return "T";
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
				return "T";
			default:
				return "C";
			}
		}
	}
	
	/**
	* Permet de générer deux nucléotides non appariées aléatoirement.
	* @return le couple de nucléotide appariée.
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
			retour[1] = nonAppariements('C');
			break;
		default:
			retour[0] = "T";
			retour[1] = nonAppariements('T');
			break;
		}
		return retour;
	}
	
	/**
	 * Permet de vérifier si deux nucléotides sont appariées.
	 * @param c1 la première nucléotide à comparer
	 * @param c2 la seconde nucléotide à comparer
	 * @return vrai si les nucléotides sont appariées | faux sinon
	 */
	public static boolean appariement(char c1, char c2) {
		switch (c1) {
		case 'A':
			if (c2 == 'T')
				return true;
			else
				return false;
		case 'C':
			if (c2 == 'G')
				return true;
			else
				return false;
		case 'G':
			if (c2 == 'C' || c2 == 'T')
				return true;
			else
				return false;
		default:
			if (c2 == 'A' || c2 == 'G')
				return true;
			else 
				return false;			
		}
	}
	
}
