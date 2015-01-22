package utils;
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
}
