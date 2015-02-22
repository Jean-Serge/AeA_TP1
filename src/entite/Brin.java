package entite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import utils.Alea;

public class Brin {
	
	private String sequence;

	/** Constructeur Brin :
	 * Permet la création d'un brin d'ADN. Ce brin est représenté par une chaine de caractères
	 * composée de G,C,T et A.
	 * 
	 * @param filename le nom du fichier FASTA contenant la description du brin d'ADN a créer.
	 */
	public Brin(String filename) {
		this.sequence = Fasta2String(filename);
	}

	/** Permet l'extraction du brin d'ADN contenu dans un fichier FASTA.
	 * 
	 * @param filename le nom du fichier FASTA contenant l'ADN a extraire.
	 * @return la chaine de caractères associée.
	 */
	private String Fasta2String(String filename) {
		String ligne = "";
		// On essaye de lire dans le fichier.
		try{
			InputStream ips=new FileInputStream(filename); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			// lecture de  l'entête = inutile.
			br.readLine();
			String msg;
			// lecture de la sequence ADN
			while ((msg =br.readLine())!=null)
				ligne+= msg;
			br.close(); 
		}		
		catch (Exception e){
			// En cas d'echec, on imprime le message d'erreur.
			System.out.println(e.toString());
		}
		// On retourne le resultat.
		return ligne;
	}


	/** Retourne la taille de la séquence du brin d'ADN.
	 * 
	 * @return la taille du brin.
	 */
	public int SizeofSequence() {
		return this.sequence.length();
	}
	
	/** Retourne la séquence du brin d'ADN :
	 * Soit la chaine de caractère représentant ce brin.
	 * 
	 * @return une chaine de caractère composée de A,G,T et C.
	 */
	public String getSequence() {
		return sequence;
	}
	
	/**
	 * Permet de savoir si un motif est un Pré-Micro-ARN.
	 * @return vrai ou faux si le motif est un Pré-Micro-ARN
	 */
	public boolean estPreMicroARN() {
		if (this.sequence.length() > 69)
			return this.estPreMicroARN(this.sequence);
		else
			return false;
	}

	/**
	 * Permet de savoir si une chaine correspond au norme d'un Pré-Micro-ARN.
	 * @param motif le motif a tester
	 * @return vrai ou faux si le motif est un Pré-Micro-ARN
	 */
	private boolean estPreMicroARN(String motif) {
		return fonc(motif,0,0,0,0,0);
	}
	
	/**
	 * Fonction récursive testant les possibilités de parenthesage d'une sequence.
	 * Renvoie un booléen si la séquence respecte les normes des pré-MicroARN.
	 * @param seq la séquence à tester
	 * @param nb_parent_d le nombre de parenthèses (cad d'appariements) que la séquence à sur sa droite
	 * @param nb_parent_g le nombre de parenthèses (cad d'appariements) que la séquence à sur sa gauche
	 * @param nb_point_d le nombre de points (cad mismatchs) que la séquence à sur sa droite
	 * @param nb_point_g le nombre de points (cad mismatchs) que la séquence à sur sa gauche
	 * @param nb_appariements le nombre d'appariements que l'on a formé actuellement
	 * @return
	 */
	private static boolean fonc(String seq, int nb_parent_d, int nb_parent_g,
			int nb_point_d, int nb_point_g,int nb_appariements) {
		
		/* Si on a parcouru toute la chaine, alors on renvoie vrai si :
		 	- on a bien au moins 24 appariements
		 	- on a au detecter à la fin une boucle terminale de taille au plus 8
		 */
		if (seq.length() == 0)
			return (nb_appariements > 23) && (nb_point_d <= 4 || nb_point_g <= 4);
		
		/* Si il reste un élement dans la chaine alors on renvoie vrai si :
		 	- on a bien au moins 24 appariements
		 	- on a au detecter à la fin une boucle terminale de taille au plus 7		 
		 */
		if (seq.length() == 1)
			return (nb_appariements > 23) && !(nb_point_g >= 4 || nb_point_d >= 4);
		
		/* Si le premier caractère de la chaine et le dernier caractère sont appariés alors :
		 	- on applique la fonction sur la chaine sans les caractères appariés,
		 	et on incrémente le nombre de parenthèses à gauche et droite ainsi que
		 	le nombre d'appariements au total. On remet les compteurs de points à 0.
		 */
		if (Alea.appariement(seq.charAt(0),seq.charAt(seq.length()-1)))
			return fonc(seq.substring(1,seq.length()-1),nb_parent_d+1,nb_parent_g+1,0,0,nb_appariements+1);
		else
		/*
		 Sinon soit :
		  - il faut mettre un point sur la gauche de la chaine,
		  le nombre de parenthèses sur la gauche devant être supérieur à 3 et
		  le nombre de points sur la gauche ne devant être inférieur à 3.
		  - il faut mettre un point sur la droite de la chaine,
		  le nombre de parenthèses sur la droite devant être supérieur à 3 et
		  le nombre de points sur la droite ne devant être inférieur à 3.
		  - il faut mettre un point à gauche et à droite,
		  le nombre de parenthèses sur la droite et sur la gauche devant être supérieur à 3 et
		  le nombre de points sur la droite et sur la gauche ne devant être inférieur à 3.
		 */
			return (((nb_point_d>2 || nb_point_d==0) && nb_point_g<4 && fonc(seq.substring(0, seq.length()-1),0,nb_parent_g,nb_point_d+1,nb_point_g,nb_appariements) || (nb_parent_g>2 || nb_parent_g==0) && nb_point_g<4 && fonc(seq.substring(1),nb_parent_d,0,nb_point_d,nb_point_g+1,nb_appariements))
					|| (nb_point_g<4 && nb_point_d<4 && fonc(seq.substring(1, seq.length()-1),0,0,nb_point_d+1,nb_point_g+1,nb_appariements)) );
	}
	
	// TEST 
	public static void main(String[] argv) {
		Brin br = new Brin("donnees/pasarn.fasta");
		System.out.println("brin1 = "+br.estPreMicroARN()+" (false normalement)");
		Brin br2 = new Brin("donnees/arn.fasta");
		System.out.println("brin2 = "+br2.estPreMicroARN()+" (true normalement)");
	}


}

	