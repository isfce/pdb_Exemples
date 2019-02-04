package exemples.java;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class JavaFonctionParam {
	/**
	 * Exemple de méthode avec un nombre de paramètres variable
	 * 
	 * @param a
	 *            0 à n paramètres entiers
	 * @return somme des entiers
	 */
	public static Long f1(Integer... a) {
		long s = 0L;
		// parcourir une collection
		for (int i : a) { // auto unboxing de Integrer vers int
			s += i;
		}
		return s; // autoBoxing de long en Long
	}

	/**
	 * Exemple avec des types primitifs les paramètres sont copiés dans des
	 * variables locales
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int f2(int a, int b) {
		a = a + b;// modifie uniquement la variable locale
		return a;
	}

	/**
	 * Exemple avec des types Objets les adresses des paramètres sont copiés mais
	 * pas les objets
	 * 
	 * @param v
	 */
	public static void f3( List<String> v) {
		v.add("Quatre");// modifie uniquement la variable locale
		
	}

	public static void main(String[] args) {
		// Appel une fonction à multiparamètres
		System.out.println(f1(4, 6, 8, 100));
		System.out.println(f1(2,3));
		// Appel d'une fonction avec copie des paramètres
		Integer i1 = 10;
		Integer i2 = 20;
		System.out.println("f2(i1,i2) :" + f2(i1, i2) + " i1= " + i1 + " i2= " + i2);

		// Appel d'une fonction avec copie des adresses des paramètres
		List<String> v=new ArrayList<>(); 
		v.addAll(Arrays.asList("Un","Deux","Trois"));
		System.out.println("V avant appel de f3 :" +v);
		f3(v);
		System.out.println("V après appel de f3 :" +v);
	}

}
