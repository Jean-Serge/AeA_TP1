package recherche;

import java.util.List;

import entite.Brin;

public abstract class Recherche {

	protected Brin b;
	protected int N;
	protected boolean r, c, rc;
	
	public Recherche(Brin b, int n, boolean reverse, boolean comp,
			boolean revComp) {
		this.N = n;
		this.b = b;
		this.r = reverse;
		this.rc = revComp;
		this.c = comp;
	}

	public abstract List<Integer> chercherMotif(String motif);
	
	public String toString(){
		String retour = "";
		retour += "Séquence : " + b.getSequence();
		retour += "Options : \nr = " + r + "\nc = " + c + "\nrc = "
				+ rc;
		retour += "\nN = " + N ;
		return retour;
	}
}
