package exemples.java21;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Java21 {

	public static void sequencedCollections() {
		// Sequenced Set
		LinkedHashSet<String> profs = new LinkedHashSet<>();
		profs.add("VO");
		profs.add("WAF");
		profs.add("HUG");
		System.out.println("Premier arrivé: " + profs.getFirst());
		System.out.println("Dernier arrivé: " + profs.getLast());
		profs.addLast("MBA");
		System.out.println(profs);
		// MAp
		LinkedHashMap<Integer, String> profs2 = new LinkedHashMap<>();
		profs2.put(1, "VO");
		profs2.put(2, "WAF");
		profs2.put(3, "HUG");
		System.out.println("Premier arrivé: " + profs2.firstEntry());
		System.out.println("Dernier arrivé: " + profs2.lastEntry());
		profs2.putLast(4, "MBA");
		System.out.println(profs2);
	}

	/**
	 * 
	 */
	private static void virtualThread() {
		// Les Threads virtuels
		// Lancer une tâche
		Thread.ofVirtual().start(() -> {
			System.out.println(" ==>THREAD Ceci est le Thread Virtuel: " + Thread.currentThread().threadId());
		});
	}

//Exemple de record matching
	private static void recordMatch() {
		record Prof(String nom, String prenom) {
		}
		;
		record Etudiant(String nom, String prenom) {
		}
		;
		Prof vo = new Prof("VO", "Didier");
		Etudiant et = new Etudiant("Et1", "Prénom");
		Object o = Math.random() < 0.5 ? vo : et;
		// Exemple avec un if instanceof
		if (o instanceof Prof(String nom, String prenom))
			System.out.println("Voici mon Nom de prof: " + nom);
		else
			System.out.println("Je ne suis pas un prof");
		// Exemple avec un switch
		System.out.println(switch (o) {
		case Prof(var nom, var prenom) -> ("Professeur " + nom);
		case Etudiant(var nom, var prenom) -> ("Etudiant " + nom);
		default -> throw new IllegalArgumentException("Unexpected value: " + o);
		});

	}
/**
 * Cette preview a été supprimée dans JDK 22
 * 
 */
/*	private static void templateProcesseur() {
		// Permet de créer des Strings contenant des paramètres \{expression}
		String nom = "VO";
		String ecole = "ISFCE";
		String txt1 = STR."Mon surnom est  \{nom}";
		System.out.println(txt1);
		String txt2 = STR."La somme de 2+3 = \{2+3}";
		System.out.println(txt2);
		String txt3 = STR."On est le \{DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now())}";
				System.out.println(txt3);
		String txt4 = STR."nom: \{nom} " + STR."ISFCE \{LocalDate.now()}";
				System.out.println(txt4);
		String txt5 = STR."""
			{
				"nom":    "\{nom}",
				"ecole":   "\{ecole}",
				"année": "2024"
			}
				""";
		System.out.println(txt5);
		String txt6 = FormatProcessor.FMT." 2/3 donnera %5.2f\{2/3.0} et on est le %Td\{LocalDate.now()} du mois %tm/%td/%ty\{LocalDate.now()}";
				System.out.println(txt6);
	}
*/
	public static void main(String[] args) {
		// Thread Virtuel
		virtualThread();
		// Collections
		sequencedCollections();
		// Record matching
		recordMatch();
		
		// Enter to close
//		Scanner scan = new Scanner(System.in);
//		System.out.println("Entrez une lettre et return");
//		scan.next();
		System.out.println("FIN: ");
//		scan.close();
	}

}
