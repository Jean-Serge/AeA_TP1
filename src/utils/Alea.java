package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * 
 * @author verbaere
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
	 * @return
	 */
	private static String[] genAppariements() {

		String[] retour = new String[2];
		Random rand = new Random();
		int i = rand.nextInt(4);

		switch (i) {
		case 0:
			retour[0] = "A";
			retour[1] = "T";
			break;
		case 1:
			retour[0] = "G";
			if (rand.nextInt(2) == 0)
				retour[1] = "C";
			else
				retour[1] = "T";
			break;
		case 2:
			retour[0] = "T";
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
	private static String nonAppariements(char c) {
		int val = new Random().nextInt(3);

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
			retour[1] = nonAppariements('C');
			break;
		default:
			retour[0] = "T";
			retour[1] = nonAppariements('T');
			break;
		}
		return retour;
	}
	
	private static boolean nonInterdit(int indice,int taille,ArrayList<int[]> interdits) {
		boolean bool = true;
		int x;
		int y;
		int i=0;
		while (i< interdits.size() && bool) {
			x = interdits.get(i)[0];
			y = interdits.get(i)[1];
			if ((indice >= x && indice <= y) ||
					((indice-1+taille) >= x && (indice-1+taille) <= y)) {
				bool = false;
			}
			i++;
		}
		return bool;
		
	}
	
	private static TreeMap<Integer,Integer> getpoints(int nb, String chaine, int dep_boucle, int fin_boucle) {
		TreeMap<Integer,Integer> retour = new TreeMap<Integer,Integer>();
		ArrayList<int[]> interdits = new ArrayList<int[]>();
		int[] start = {0,2};
		int[] end = {chaine.length()-3,chaine.length()-1};
		int[] boucle = {dep_boucle-3,fin_boucle+3};
		interdits.add(end);
		interdits.add(start);
		interdits.add(boucle);
		
		Random rand = new Random();
		int taille;
		int nb_points = nb;
		int indice;
		
		while (nb_points !=0) {
			taille = rand.nextInt(3)+1;
			indice = rand.nextInt(chaine.length());
			if (nb_points - taille >= 0) {
				if (nonInterdit(indice,taille,interdits)) {
					int[] nouveau = {indice-3,indice+taille+2};
					interdits.add(nouveau);
					retour.put(indice, taille);
					nb_points -= taille;
				}
			}
		}

		return retour;
	}

	private static String genParenthesage() {
		Random rand = new Random();
		int longueur =rand.nextInt(31) + 70;
		int boucle_terminale = rand.nextInt(8)+1;
		int nb_points = rand.nextInt(19)+1;
		if (longueur%2 == 1) {
			nb_points++;
		}
		int nb_couple = (longueur-nb_points-boucle_terminale)/2;
						
		String arn = "";
		
		for (int i=0; i< boucle_terminale; i++)
			arn += ".";
		
		for (int i=0; i< nb_couple; i++)
			arn = "("+ arn + ")";
		
		//System.out.println("long : "+longueur+"\nboucle : "+boucle_terminale+"\ncouple : "+nb_couple+"\npoints : "+nb_points);
		
		TreeMap<Integer,Integer> tab = getpoints(nb_points,arn,nb_couple,nb_couple+boucle_terminale-1);
		String mini = "";
		int depose = 0;

		Iterator<Integer> i = tab.keySet().iterator();
		int cle;
		while (i.hasNext())
		{
			cle = (int)i.next();
			for (int j = 0 ; j<tab.get(cle); j++)
				mini += ".";
			arn = arn.substring(0,cle+depose) + mini +arn.substring(cle+depose);
			depose += mini.length();
			mini = "";
		}

		return arn;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String genPreMicroARN() {
		String parent = genParenthesage();
		StringBuffer sb = new StringBuffer(parent);
		System.out.println(parent);
		int ouv = sb.indexOf("(");
		int fer = sb.lastIndexOf(")");
		String[] aux;
		while (ouv != -1) {
			aux = genAppariements();
			sb.setCharAt(ouv, aux[0].charAt(0));
			sb.setCharAt(fer, aux[1].charAt(0));
			ouv = sb.indexOf("(");
			fer = sb.lastIndexOf(")");
		}
		
		int dep = 0;
		int fin = parent.length()-1;
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
		return sb.toString();
	}
	
	public static String genMicroARN() {
		Random rand = new Random();
		int taille = 20 + rand.nextInt(4);
		int indice = 10 + rand.nextInt(6);
		String pmarn = genPreMicroARN();
		System.out.println(pmarn);
		return pmarn.substring(indice, indice+taille);
	}

	public static void main(String[] argv) {
		System.out.println(genMicroARN());
	}
}
