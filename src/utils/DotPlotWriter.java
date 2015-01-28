package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import recherche.Recherche;

/** Classe permettant la création d'un fichier txt contenant les resultats d'une recherche
 * et d'un fichier .plot permettant la création d'un graphique gnuplot.
 * @author monbailly
 * @author verbaere
 *
 */
public class DotPlotWriter {
	
	private PrintWriter out; // PrintWriter du fichier txt
	private PrintWriter plot; // PrintWriter du fichier plot
	private Recherche recherche;
	private String path;
	
	
	/** Contructeur du Créateur de fichier de resultats.
	 * @param file le nom des fichiers a créer
	 * @param result la recherche dont on veut exploter les resultats
	 */
	public DotPlotWriter(String file, Recherche r) {

		try {
			// On tente d'ouvrir un fichier texte
			File fichier = new File(file+".txt") ;
			this.out = new PrintWriter(new FileWriter(fichier));
			// On tente d'ouvrir un fichier plot
			File fichier2 = new File(file+".plot") ;
			this.plot = new PrintWriter(new FileWriter(fichier2));
			
			this.path = file;
			this.recherche = r;
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Impression des resultats dans les fichiers plot et txt.
	 */
	public void printResults() {
		// Pour chaque valeur stockée dans la liste des résultats on créé une ligne 
		// avec abscisses ordonnées dans le fichier txt
		for (String s : this.recherche.getResultats().keySet()) {
			List<Integer> list = this.recherche.getResultats().get(s);
			for (Integer i : list) {
				for (Integer j : list) {
						out.println(i+" "+j);
				}
			}
		}
		// On écrit le fichier plot associé
		this.printPlot();
		out.close() ;
	}
	
	/**
	 * Création d'un fichier pour gnuplot pour tracer un grapique
	 */
	public void printPlot() {
		plot.println("set term png;");
		
		plot.println("set xrange [0:"+(this.recherche.getSequence().length()-1)+"];");
		plot.println("set yrange[0:"+(this.recherche.getSequence().length()-1)+"];");
		
		plot.println("set output '"+this.path+"';");
		plot.println("plot '"+this.path+".txt' using 1:2 title 'Recherche pour N="+this.recherche.getN()+"' with points;");
		
		plot.close() ;
	}
	
}
