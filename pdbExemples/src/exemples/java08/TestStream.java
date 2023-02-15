package exemples.java08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestStream {
	public static void main(String[] args) {
		System.out.println("*********** Stream For Each *****************");
		// Parcours une liste pour appliquer une opération sur chacun de ses éléments
		List<Integer> maListe = new ArrayList<>(Arrays.asList(1, 2, 6, 9, 4));
		maListe.stream().forEach(e -> System.out.print(e + " "));
		System.out.println();// version
		System.out.println("*********** Stream Filtre les éléments *****************");
		// Filtre les éléments d'un flux
		maListe.stream().filter(i -> i > 3).forEach(e -> System.out.print(e + " "));
		System.out.println();
		System.out.println("*********** Stream Transforme une liste en Map *****************");
		/*
		 * Transforme une liste d'article en une Map 
		 * dont la clé est le code de l'article 
		 */
		List<Article> listeArt = new ArrayList<>();
		listeArt.add(new Article("COCAL", "CocaLight", 2.5));
		listeArt.add(new Article("STEAK", "Steak maison 250Gr", 16.0));
		Map<String, Article> mapArt = listeArt.stream().collect(Collectors.toMap(Article::getCode, a -> a));
		System.out.println(mapArt.get("STEAK"));
		System.out.println("*********** Crée une liste observable JavaFX à partir d'une Map *****************");
		//Crée une liste observable à partir d'une MAP
		ObservableList<String> listeObs = FXCollections
				.observableList(mapArt.keySet().stream().sorted().collect(Collectors.toList()));
		listeObs.stream().forEach(e -> System.out.print(e + " "));
		System.out.println("*********** Stream génère une suite de 5 valeurs entières aléatoires *****************");
		// Génère une suite de valeur
		IntStream.generate(new Random()::nextInt).limit(5).forEach(System.out::println);

	}
}
//Classe Article pour l'exemple
 class Article {
	String code;
	String nom;
	Double prix;

	public Article(String code, String nom, Double prix) {
		super();
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}

	public String getCode() {
		return code;
	}

	public String getNom() {
		return nom;
	}

	public Double getPrix() {
		return prix;
	}
}