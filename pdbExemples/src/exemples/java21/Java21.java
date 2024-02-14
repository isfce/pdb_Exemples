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
