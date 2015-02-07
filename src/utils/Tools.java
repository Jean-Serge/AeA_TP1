package utils;

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

}
