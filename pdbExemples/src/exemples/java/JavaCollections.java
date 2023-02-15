package exemples.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaCollections {

	public static void main(String[] args) {
		/**********************************
		 * Listes
		 ***********************************/
		// Liste non modifiable
		List<Integer> v1 = List.of(1, 2, 5);
		System.out.println(v1);
		// Liste modifiable mais de taille fixe
		List<Integer> v2 = Arrays.asList(1, 2, 5, 6);
		v2.set(0, 10);
		System.out.println(v2);

		// Liste vide extensible
		List<Integer> v3 = new ArrayList<>();
		v3.addAll(Arrays.asList(21, 33, 2, 9));
		System.out.println(v3);

		List<Integer> v = new ArrayList<>();
		v.addAll(Arrays.asList(10, 4, -3, 9));
		
		//Ajout de nouveaux éléments
		v.add(1, 99);//Ajoute un nouvel élément et décale les autres vers la droite
		v.addAll(1, Arrays.asList(1, 2, 3));//Ajoute une liste d'éléments
		//Suppression
		v.remove(2);//retire l'élément d'indice 2
		v.remove((Integer) (-3));//retire l'élément -3
		v.removeIf(x -> x<5);//rétire les éléments répondant à une condition
		
		//Vérifie si la liste contient un élément
		System.out.println(v.contains(9)); 
		//Récupère l'élément d'indice spécifié
		System.out.println(v.get(0));
		
		//Remplace l'élément d'indice indiqué
	    v.set(0, 55);
		
		//Trier une liste
		v.sort(Integer::compare);//Tri en spécifiant un compateur
	
		Collections.sort(v);//Tri avec le comparateur des éléments
		System.out.println(v);
		
		//Création d'une sous-liste
		v2 = v.subList(1, 3);
		System.out.println(v2);

		/* 
		 * Parcourir une liste 
		 */
		// Parcourir une liste version classique
		for (int i = 0; i < v.size(); i++)
			System.out.print(v.get(i) + " , ");
		System.out.println();
		
		// Parcourir avec une boucle "For each"
		for (Integer elem : v)
			System.out.print(elem + " , ");
		System.out.println();

		// Avec un itérateur
		var iter = v.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next() + " , ");
		}
		System.out.println();
		
		//Classes utitaires
		//Liste non modifiable (pas ses éléments s'ils sont mutables!)
		List<Integer> lCopie=Collections.unmodifiableList(v);
		//Liste avec accès synchronisé
		List<Integer> lCopie2=Collections.synchronizedList(v);
		/**********************************
		 * ENSEMBLES
		 ***********************************/
		// Ensemble non modifiable
		Set<Integer> ens1 = Set.of(1, 2, 3, 5, 8, 13);
		Set<Integer> ens2 = Set.of(1, 2, 4, 8, 16, 32);
		// ens modifiable non initialisé
		Set<Integer> e0 = new HashSet<>();
		// ens modifiable initialisé
		Set<Integer> e1 = new HashSet<>(ens1);
		Set<Integer> e2 = new HashSet<>(Arrays.asList(3, 6, 9, 12, 15));

		System.out.println(e0 + " " + e1 + " " + e2);
		System.out.println(e1.size());
		// Appartenance
		System.out.println(e2.contains(16));
		// Intersection
		e1.retainAll(ens2);
		System.out.println(e1);
		// exclusion
		e1.removeAll(ens2);
		System.out.println(e1);
		
		// Ajout d'un élément (null est autorisé avec HashSet)
		e1.add(64);
		//Retire l'élément
		e1.remove(64);
		
		/*
		 * Parcourir une liste
		 */
		// Parcourir avec ForEach
		for (Integer e : e2) {
			System.out.print(e + ",");
		}
		System.out.println();

		// Parcourir avec ForEach Lambda
		e2.forEach(e -> System.out.print(e + ","));
		System.out.println();

		// Parcourir avec un itérateur
		var iter1 = e2.iterator();
		while (iter1.hasNext()) {
			System.out.print(iter1.next() + ",");
		}
		System.out.println();

		/**********************************
		 * Dictionnaire
		 ***********************************/
		// Map non modifiable v1
		Map<Integer, String> codesHttp1 = Map.of(200, "Succès", 301, "Indirection", 404, "Ressource non trouvée");
		// Map non modifiable v2
		Map<Integer, String> codesHttp2 = Map.ofEntries(Map.entry(200, "Succès"), Map.entry(301, "Indirection"),
				Map.entry(404, "Ressource non trouvée"));

		// Map modifiable
		Map<Integer, String> codesHttp3 = new HashMap<>();
		
		//Ajouter des éléments
		codesHttp3.put(200, "Succès");
		codesHttp3.put(301, "Indirection");
		codesHttp3.put(404, "Ressource non trouvée");
		
		//Récupérer un élément
		String res1=codesHttp1.get(200);
		System.out.println(res1);
		  //Si la clé n'existe pas fourni une valeur par défaut
		String res2=codesHttp1.getOrDefault(007, "James");
		System.out.println(res2);
		
		//Remplacer un élément existant
		codesHttp1.replace(200, "Tout va bien");

		// Ensemble des clés
		System.out.println(codesHttp3.keySet());
		// Ensemble des entrées
		System.out.println(codesHttp3.entrySet());
		// Collection des valeurs
		System.out.println(codesHttp3.values());
			
	}

}
