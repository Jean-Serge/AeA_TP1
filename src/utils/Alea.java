package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

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
	private static String[] genAppariements() {

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
	private static String nonAppariements(char c) {
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
	 * Fonction qui permet de savoir si l'on peut placer à un indice de départ 'indice'
	 * un mot d'une taille 'taille' sachant un dictionnaire d'interdits.
	 * @param indice l'indice de départ du mot a placer
	 * @param taille la taille du mot a placer
	 * @param interdits le dictionnaire des positions interdites.
	 * @return
	 */
	private static boolean nonInterdit(int indice,int taille,ArrayList<int[]> interdits) {
		boolean bool = true;
		int x;
		int y;
		int i=0;
		// tant qu'on a pas trouvé qu'il est impossible de placer un tel mot et tant
		// qu'on a pas atteint la fin du dictionnaire :
		while (i< interdits.size() && bool) {
			x = interdits.get(i)[0];
			y = interdits.get(i)[1];
			// On regarde si les coordonnées du mot a placer coincide avec les 
			// coordonnées des interdits (si oui : false -> interdit de le placer ici !)
			if ((indice >= x && indice <= y) ||
					((indice-1+taille) >= x && (indice-1+taille) <= y)) {
				bool = false;
			}
			i++;
		}
		return bool;
		
	}
	
	/**
	 * Retourne une liste de coordonnées. On souhaite placer 'nb' points dans la chaine.
	 * On ne peut pas avoir plus de 3 points à coté, ni moins de 3 parentheses.
	 * @param nb le nombre de points à placer
	 * @param chaine la chaine de caractère ou l'on veut placer les points
	 * @param dep_boucle l'indice de départ de la boucle terminale
	 * @param fin_boucle l'indice de fin de la boucle terminale
	 * @return une liste de points (indice de départ et taille (1,2 ou 3)
	 */
	private static TreeMap<Integer,Integer> getpoints(int nb, String chaine, int dep_boucle, int fin_boucle) {
		TreeMap<Integer,Integer> retour = new TreeMap<Integer,Integer>();
		ArrayList<int[]> interdits = new ArrayList<int[]>();
		int[] start = {0,2};
		int[] end = {chaine.length()-3,chaine.length()-1};
		int[] boucle = {dep_boucle-3,fin_boucle+3};
		// Les points ne peuvent pas être placer au début, à la fin ni même dans la boucle terminale :
		interdits.add(end);
		interdits.add(start);
		interdits.add(boucle);
		
		Random rand = new Random();
		int taille;
		int nb_points = nb;
		int indice;
		
		// Pour le nombre de points a placer :
		for (int i=0; i< nb_points; i++) {
			taille = rand.nextInt(3)+1;
			indice = rand.nextInt(chaine.length());
			if (nb_points - taille >= 0) {
				// si les coordonnées de la chaine des points est correctement placée :
				if (nonInterdit(indice,taille,interdits)) {
					int[] nouveau = {indice-3,indice+taille+2};
					// Alors les coordonnées sont maintenant interdites pour les autres qui suivent :
					interdits.add(nouveau);
					// On ajoute les coordonnées dans la liste des points
					retour.put(indice, taille);
					nb_points -= taille;
				}
			}
		}

		return retour;
	}

	/**
	 * Permet de retourner un parenthesage selon la norme des Pré-micros ARN.
	 * @return une chaine de caractères composée de '.' et de ')','('.
	 */
	private static String genParenthesage() {
		Random rand = new Random();
		// On génère une longueur de pré-micro ARN aléatoirement :
		int longueur =rand.nextInt(31) + 70;
		// On génère un nombre de couples (appariements) aléatoirement :
		int nb_couple = rand.nextInt(longueur/2 - 24)+ 24;
		// On génère la taille de la boucle terminale aléatoirement :
		int boucle_terminale = rand.nextInt(8)+1;
		// On génère le nombre de "points" dans la boucle (hors boucle terminale) :
		int nb_points = longueur - boucle_terminale - 2*nb_couple;
						
		String arn = "";
		
		// On remplit la boucle terminale de points :
		for (int i=0; i< boucle_terminale; i++)
			arn += ".";
		
		// On ajoute les parenthesages :
		for (int i=0; i< nb_couple; i++)
			arn = "("+ arn + ")";
		
		// On cherche des coordonnées pour les points a placer dans la chaine :
		TreeMap<Integer,Integer> tab = getpoints(nb_points,arn,nb_couple,nb_couple+boucle_terminale-1);
		String mini = "";
		int depose = 0;

		// On parcours la liste des points et on les placent dans la chaine
		Iterator<Integer> i = tab.keySet().iterator();
		int cle;
		while (i.hasNext())
		{
			cle = (int)i.next();
			// Il faut faire attention, quand on dépose un point il faut mettre
			// a jour les coordonnées des autres points générés après dans la liste.
			for (int j = 0 ; j<tab.get(cle); j++)
				mini += ".";
			arn = arn.substring(0,cle+depose) + mini +arn.substring(cle+depose);
			depose += mini.length();
			mini = "";
		}
		// On renvoie le tout :
		return arn;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String genPreMicroARN() {
		// On génère un parenthesage possible de pré-micro-ARN :
		String parent = genParenthesage();
		StringBuffer sb = new StringBuffer(parent);
		System.out.println(parent);
		
		int ouv = sb.indexOf("(");
		int fer = sb.lastIndexOf(")");
		String[] aux;
		// On cherche tout les couples '(' et ')' et on dépose à leurs places
		// des appariements :
		while (ouv != -1) {
			aux = genAppariements();
			sb.setCharAt(ouv, aux[0].charAt(0));
			sb.setCharAt(fer, aux[1].charAt(0));
			ouv = sb.indexOf("(");
			fer = sb.lastIndexOf(")");
		}
		
		int dep = 0;
		int fin = parent.length()-1;
		// On se charge maintenant de remplacer les points de la chaine
		// en faisant attention de ne pas ajouter des appariements en plus !
		while (dep <= fin) {
			if (sb.charAt(dep) == '.' && sb.charAt(fin) == '.') {
				aux = nonAppariements();
				sb.setCharAt(dep, aux[0].charAt(0));
				sb.setCharAt(fin, aux[1].charAt(0));
				dep++;
				fin--;
			}
			else if (sb.charAt(dep) == '.') {
				sb.setCharAt(dep, nonAppariements(sb.charAt(fin)).charAt(0));
				dep++;
			}
			else if (sb.charAt(fin) == '.') {
				sb.setCharAt(fin, nonAppariements(sb.charAt(dep)).charAt(0));
				fin--;
			}
			else {
				fin--;
				dep++;
			}
		}
		// On renvoie le resultat :
		return sb.toString();
	}

}
