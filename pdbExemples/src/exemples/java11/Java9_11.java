package exemples.java11;

import static java.util.Map.entry;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Java9_11 {

	// Interface avec des méthodes privées
	private interface Test {
		default void m1() {
			affiche("appel de m1");
		};

		default void m2() {
			affiche("appel de m2");
		};

		private void affiche(String s) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		System.out.println("******** Interface ****************");
		/* Test de l'interface privée */
		// instantiation à partir d'une interface
		Test testI = new Test() {
		};
		testI.m1();// ==> affiche "appel de m1"
		testI.m2();// ==> affiche "appel de m2"

		System.out.println("******** LIST ****************");
		/*
		 * Initialisation d'une "List", "Set", "Map" non modifiable Structure immutable,
		 * serializable, null value not allow,
		 */
		List<String> l1 = List.of("Didier", "Marie-Ange", "Joël", "Luc");
		try {
			l1.add("Loulou");
		} catch (Exception e) {
			System.err.println("LA LISTE EST IMMUTABLE!!");
		}
		System.out.println("******** SET ****************");
		/*
		 * Initialisation d'un ensemble non modifiable
		 */
		Set<String> s1 = Set.of("Didier", "Marie-Ange", "Joël", "Luc");
		try {
			s1.add("Loulou");
		} catch (Exception e) {
			System.err.println("L'ensemble EST IMMUTABLE!!");
		}

		System.out.println("******** MAP ****************");
		/*
		 * Une MAP nécessite une paire clé-valeur pour chaque entrée voici 2 façons
		 * d'initialiser une Map non modifiable
		 */
		// Map Version1
		Map<Integer, String> m1 = Map.of(1, "Didier", 2, "Marie-Ange", 3, "Joël", 4, "Luc");
		try {
			m1.put(5, "John");
		} catch (Exception e) {
			System.err.println("La map EST IMMUTABLE!!");
		}
		// Map Version2:
		Map<Integer, String> m2 = Map.ofEntries(entry(1, "Didier"), entry(2, "Alain"));

		/*
		 * Optional JAVA9
		 * 
		 */
		System.out.println("******** Optional ****************");
		String texte = Math.random() >= 0.5 ? "Hello" : null;
		Optional<String> o1 = Optional.ofNullable(texte);
		o1.ifPresentOrElse((e) -> System.out.printf("L'élément %s est Là \n", e),
				() -> System.err.println("L'élément n'est Là"));
		;

		Optional<String> result = o1.or(() -> Optional.of("texte par Défaut"));
		System.out.println(result.get());
		/*
		 * Stream Exemples pour takeWhile et dropWhile
		 */
		
		List<Integer> datas = Arrays.asList(2, 4, 6, 8, 9, 10, 12);
		System.out.println("Données pour les 2 exemples. Ici la liste doit être triée sinon autre résultat");
		System.out.println(datas);
		System.out.println("******** STREAM TakeWhile ****************");
		// Arrête le flux dès que la condition n'est plus validée
		datas.stream().takeWhile(n -> n % 2 == 0).forEach((i) -> System.out.printf("%d , ", i));
		System.out.println();
		System.out.println("******** STREAM DropWhile ****************");
		// élimine les éléments tantque la condition est validée
		datas.stream().dropWhile(n -> n % 2 == 0).forEach((i) -> System.out.printf("%d , ", i));
		
		/*
		 * var uneVariable ( JAVA10 )
		 * Utilisation de variables qui seront typées lors de l'assignation
		 */
		System.out.println();
		System.out.println("Local Variable type inference (Java10)");
		int i=25;
		String nom="Didier";
		var maVar1=i;
		maVar1=12;
		var maVar2=nom;
		System.out.println(maVar1+"  "+maVar2);
		
		
	}
}
