package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DotPlotWriter {
	
	private PrintWriter out;
	private List<Integer> results;
	
	
	public DotPlotWriter(String file,List<Integer> result) {

		try {
			File fichier = new File(file) ;
			this.out = new PrintWriter(new FileWriter(fichier));			
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void printResults() {
		// blabla
		out.close() ;
	}
}
