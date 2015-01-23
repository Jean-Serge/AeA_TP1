package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @author monbailly
 * @author verbaere
 *
 */
public class DotPlotWriter {
	
	private PrintWriter out;
	private Map<String, List<Integer>> results;
	private String sequence;
	
	
	/**
	 * @param file
	 * @param result
	 */
	public DotPlotWriter(String file,Map<String, List<Integer>> result,String sequence) {

		try {
			File fichier = new File(file) ;
			this.out = new PrintWriter(new FileWriter(fichier));	
			this.results = result;
			this.sequence = sequence;
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 */
	public void printResults() {
		for (String s : this.results.keySet()) {
			List<Integer> list = this.results.get(s);
			for (Integer i : list) {
				for (Integer j : list) {
					out.println(i+" "+j);
				}
			}
		}
		out.close() ;
	}
	
}
