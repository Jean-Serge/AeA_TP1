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
		out.println("x=[0.."+(this.sequence.length()-1)+"]");
		out.println("y=[0.."+(this.sequence.length()-1)+"]");
		for (String s : this.results.keySet()) {
			List<Integer> list = this.results.get(s);
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					out.println("plot("+i+","+j+",'pb')");
				}
			}
		}
		out.println("text(x,y)");
		out.close() ;
	}
}
