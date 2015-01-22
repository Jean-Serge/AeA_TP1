package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DotPlotWriter {
	
	public DotPlotWriter(String file,List<Integer> result) {

		try {
			
			File fichier = new File(file) ;
			PrintWriter out;
			out = new PrintWriter(new FileWriter(fichier+".dotplot"));
			out.write("Test") ;
			out.println() ;
			out.write("Dotplot") ;
			out.close() ; //Ferme le flux du fichier
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
			
		}

	}
	
	
}
