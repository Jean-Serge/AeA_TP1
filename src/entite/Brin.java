package entite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Brin {
	
	private String sequence;

	public Brin(String filename) {
		this.sequence = Fasta2String(filename);
	}

	private String Fasta2String(String filename) {
		String ligne = "";
		try{
			InputStream ips=new FileInputStream(filename); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			/* lecture de  l'entête = inutile */
			ligne=br.readLine();
			/* lecture de la sequence ADN qui est sur une seule ligne */
			ligne=br.readLine();
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return ligne;
	}


	public int SizeofSequence() {
		return this.sequence.length();
	}
	
	public String getSequence() {
		return sequence;
	}
	
	

}

	