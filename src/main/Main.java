package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import entite.Brin;
import recherche.Recherche;
import recherche.RechercheBoyerMoore;
import recherche.RechercheNaive;
import utils.DotPlotWriter;
import utils.Tools;

public class Main {

	// TODO affiche les différentes options prises en charge par le programme.
	public static void usage() {

	}

	public static Recherche parseArgs(String[] args) {
		int N = -1;
		boolean reverse, comp, revComp;
		reverse = comp = revComp = false;
		String path = null, algo = null;

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("--")) {
				algo = args[i];
			} else if (args[i].startsWith("-")) {
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
		if (null == algo)
			return new RechercheBoyerMoore(b.getSequence(), N, reverse, comp,
					revComp);
		else {
			switch (algo) {
			case "--naif":
				return new RechercheNaive(b.getSequence(), N, reverse, comp,
						revComp);
			case "--boyer-moore":
				return new RechercheBoyerMoore(b.getSequence(), N, reverse, comp,
						revComp);
			default:
				System.out.println("Veuillez spécifier un algorithme valide.");
				System.exit(1);
			}
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Recherche r;
		r = Main.parseArgs(args);
		
		long debut = System.currentTimeMillis();
		r.rechercheComplete();
		long fin = System.currentTimeMillis();
		System.out.println("La recherche a pris " + (fin-debut) + " millisecondes.");
		DotPlotWriter dp = new DotPlotWriter("resultats.dotplot", r);
		dp.printResults();

//		 System.out.println(r.getResultatsAsString());
		// System.out.println("\n\n==================================================================================================\n\n");
		// System.out.println(recherche2.getResultatsAsString());
	}

}
