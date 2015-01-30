package entite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	
	

}

	