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
		
		//List avec une implémentation ArrayList (autres LinkedList,...)
		//Maintient l'ordre et accepte les doublons 
		//accès comme un vecteur par get()
		List<String> col=new ArrayList<>();
		col.add("Didier");
		col.add("Didier");
		col.add("Christophe");
		System.out.println("Size: "+col.size()+" Elem en position2 : "+col.get(2));
		System.out.println(col);
		
		//Création d'une liste avec Arrays.asList : Taille fixée après!!!
		//Utilisation des méthodes statiques de Collections
		List<String> prof=Arrays.asList("VO","DH","LM");
		Collections.sort(prof);
		System.out.println(prof);
		//(Java8) Appliquer une Lambda expression sur chaque élément
		// de la collection.
		prof.forEach(e-> System.out.print(e.toLowerCase()+" - "));
		System.out.println();
		//Utilisation d'un ensemble Set avec une impélmentation HashSet
		//Pas d'ordre et pas de doublon
		Set<String> ens= new HashSet<>();
		ens.add("Didier");
		ens.add("Didier");
		ens.add("Christophe");
		System.out.println(ens);
		System.out.println("Size: "+ens.size());
		
		//Dictionnaire  clé==> valeur
		//Pas de doublon pas d'ordre
		Map<String,String> dict= new HashMap<>();
		dict.put("VO", "Didier VOO");
		dict.put("VO", "Didier Van Oudenhove");
		dict.put("DH", "De Henau MA");
		System.out.println("Get VO: "+dict.get("VO"));
		System.out.println("Liste des Clès"+dict.keySet());
		System.out.println("Liste des valeurs"+dict.values());
		System.out.println(dict);
		
		//Classes Utilitaires
		
		//List non modifiable (pas ses éléments!)
		List<String> lCopie=Collections.unmodifiableList(prof);
		//List avec accès synchronisé
		List<String> lCopie2=Collections.synchronizedList(prof);
	}

}
