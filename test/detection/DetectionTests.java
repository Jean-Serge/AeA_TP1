package detection;

import static org.junit.Assert.*;
import entite.Brin;
import generateur.GenerateurPreMiRNA;

import org.junit.Test;


/**
 * Classe de tests pour la detection des PréMiRNAs
 * @author verbaere
 * @author monbailly
 *
 */
public class DetectionTests {

	@Test
	public void TestDetection() {
		
		GenerateurPreMiRNA generateur = new GenerateurPreMiRNA();
		DetectionPreMiRNA detecteur;
		Brin brin;
		
		for (int i=0; i<100; i++) {
			generateur.genPreMicroARN();
			brin = new Brin("donnees/generation.fasta");
			detecteur = new DetectionPreMiRNA(brin);
			int[] res = detecteur.contientPreMiRNA(0, brin.SizeofSequence()-1);
			assertNotNull(res);
		}
	}
	
}
