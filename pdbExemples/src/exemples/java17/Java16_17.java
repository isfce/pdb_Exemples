package exemples.java17;

import java.text.NumberFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class Java16_17 {
	static // Text Blocks: permet de définir un texte sur plusieurs lignes
	String texte = """
			  {
			    "ecole": "ISFCE",
			    "adresse": "Rue Joseph Buedts, 14"
			    "commune": "Etterbeek"
			  }
              """;// la position permet d'influencer le nbr d'espaces

	private static void loterieOld(Integer i) {
		switch (i) {
		case 1, 2:
			System.out.println("Félicitation $$$$");
			break;
		case 5:
			System.out.println("Vous avez gagné un bon de 10€");
			break;
		default:
			System.out.println("Pas de chance");
			
		}
	}

//enum
	public enum Operation {
		CREATE, UPDATE, DELETE, RESEARCH, INSERT
	};

	private static void traitement2(Operation operation) {
		switch (operation) {
		case CREATE, INSERT -> System.out.println("Traitement Ajout");
		case UPDATE -> System.out.println("Traitement Mise à jour");
		case DELETE -> System.out.println("Traitement Mise à jour");
		default -> System.out.println("Traitement autre");
		}
	}

//Swith sans break 
	private static void loterie1(Integer i) {
		switch (i) {
		case 1, 2 -> System.out.println("Félicitation $$$$");
		case 5 -> System.out.println("Vous avez gagné un bon de 10€");
		default -> System.out.println("Pas de chance");
		}
	}

//switch qui retourne une valeur
	private static void loterie2(Integer i) {
		String res = switch (i) {
		case 1, 2 -> {
			yield "Vous avez gagné un bon de 10€";
		}
		case 5 -> {
			yield "Félicitation, vous gagnez 100€";
		}
		default -> "Pas de chance";
		};
		System.out.println(res);
	}
//Record

	public enum Couleur {
		NOIR, ROUGE
	};

	public enum Forme {
		PIQUE(Couleur.NOIR), TREFFLE(Couleur.NOIR), 
		CARREAU(Couleur.ROUGE), COEUR(Couleur.ROUGE);

		private Couleur couleur;

		Forme(Couleur couleur) {
			this.couleur = couleur;
		}

		public Couleur getCouleur() {
			return couleur;
		}

	};

	public record Carte(Forme forme, int valeur) {
		Carte next() {
			return new Carte(forme, valeur + 1);
		}
	};

	public static void main(String[] args) {
		System.out.println(texte);
		System.out.println("123");
		loterie1(5);
		loterie1(1);
		loterie1(8);
		loterie2(5);
		loterie2(1);
		loterie2(8);
		loterieOld(5);
		loterieOld(1);
		loterieOld(8);
		// Carte
		Carte coeurAs = new Carte(Forme.COEUR, 1);
		Carte treffle8 = new Carte(Forme.TREFFLE, 8);
		System.out.println(coeurAs);
		System.out.println(treffle8);
		System.out.println(treffle8.next());
		
		Couleur coul1=Couleur.ROUGE;
		Couleur coul2=Couleur.NOIR;
		System.out.println("COUL "+ coul1.name()+" ordre: "+coul1.ordinal());
		System.out.println("COUL " + coul2.name() + " ordre: " + coul2.ordinal());

		// InstanceOf et typecasting
		Object o = coeurAs;
		if (o instanceof Carte c)
			System.out.println("o est bien une carte: " + c);

		// Format Nombre SHORT LONG
		NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.forLanguageTag("FR"), NumberFormat.Style.SHORT);
		System.out.println(fmt.format(1000));
		System.out.println(fmt.format(100000));
		System.out.println(fmt.format(1000000));
		NumberFormat fmt2 = NumberFormat.getCompactNumberInstance(Locale.forLanguageTag("FR"), NumberFormat.Style.LONG);
		System.out.println(fmt2.format(1000));
		System.out.println(fmt2.format(100000));
		System.out.println(fmt.format(1000000));
		// Format Day periods
		var dtf = DateTimeFormatter.ofPattern("B").withLocale(Locale.forLanguageTag("FR"));
		System.out.println(dtf.format(LocalTime.of(8, 0)));
		System.out.println(dtf.format(LocalTime.of(13, 0)));
		System.out.println(dtf.format(LocalTime.of(20, 0)));
		System.out.println(dtf.format(LocalTime.of(0, 0)));
		System.out.println(dtf.format(LocalTime.of(1, 0)));
		// Stream to List
		Stream<String> sliste = Stream.of("ISFCE", "ECOLE", "INFORMATIQUE");
		List<String> liste = sliste.toList();
		System.out.println(liste);
	}
}
