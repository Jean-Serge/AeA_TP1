package main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import entite.Brin;
import recherche.Recherche;
import recherche.RechercheNaive;
import utils.Tools;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = -1;
		boolean reverse, comp, revComp;
		reverse = comp = revComp = false;
		String path = null;

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				// Options de recherche
				switch (args[i]) {
				case "-r":
					reverse = true;
					break;
				case "-c":
					comp = true;
					break;
				case "-rc":
					revComp = true;
					break;
				default:
					System.out.println("Le paramètre " + args[i]
							+ " est invalide.");
					break;
				}
			} else if (Tools.isInteger(args[i])) {
				// On a trouvé un N
				N = Integer.parseInt(args[i]);
				if (N <= 0) {
					System.out.println("N ne peut être négatif ou nul.");
					System.exit(1);
				}
			} else {
				// Chemin du fichier fasta
				try {
					new FileInputStream(args[i]);
				} catch (FileNotFoundException e) {
					System.out.println("Le fichier spécifié n'existe pas.");
					System.exit(1);
				}
				path = args[i];
			}
		}

		// On vérifie qu'un entier a été spécifié
		if (N == -1) {
			System.out
					.println("Veuillez spécifier la longueur de la chaîne recherchée.");
			System.exit(1);
		}
		// On vérifie qu'un chemin a été spécifié
		if (path == null) {
			System.out.println("Veuillez indiquer le fichier fasta à lire.");
			System.exit(1);
		}

		// On instancie la recherche voulue
		Brin b = new Brin(path);
		Recherche recherche = new RechercheNaive(b.getSequence(), N, reverse, comp, revComp);
		System.out.println(recherche);

		recherche.rechercheComplete();
		// List<Integer> liste = recherche.chercherMotif("AT", b);
		// System.out.println(b.getSequence());
		// System.out.println(liste);

	}

}
