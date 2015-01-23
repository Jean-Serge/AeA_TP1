package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import recherche.Recherche;

/**
 * @author monbailly
 * @author verbaere
 *
 */
public class DotPlotWriter {
	
	private PrintWriter out;
	private PrintWriter plot;
	private Recherche recherche;
	private String path;
	
	
	/**
	 * @param file
	 * @param result
	 */
	public DotPlotWriter(String file, Recherche r) {

		try {
			File fichier = new File(file+".txt") ;
			this.out = new PrintWriter(new FileWriter(fichier));
			
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
	 * 
	 */
	public void printResults() {
		for (String s : this.recherche.getResultats().keySet()) {
			List<Integer> list = this.recherche.getResultats().get(s);
			for (Integer i : list) {
				for (Integer j : list) {
					if(i == j)
						out.println(i+" "+j);
				}
			}
		}
		this.printPlot();
		out.close() ;
	}
	
	public void printPlot() {
		plot.println("set term png;");
		
		plot.println("set xrange [0:"+(this.recherche.getSequence().length()-1)+"];");
		plot.println("set yrange[0:"+(this.recherche.getSequence().length()-1)+"];");
		
		plot.println("set output '"+this.path+"';");
		plot.println("plot '"+this.path+".txt' using 1:2 title 'Recherche pour N="+this.recherche.getN()+"' with points;");
		
		plot.close() ;
	}
	
}
