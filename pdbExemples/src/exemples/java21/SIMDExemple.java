package exemples.java21;

import java.util.Arrays;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorSpecies;

/**
 * Pour exécuter ce code, vous devez importer le module
 * jdk.incubator.vector
 * "java --add-modules jdk.incubator.vector"
 * 
 * Il s'agit d'un module en incubation depuis Java19
 */
public class SIMDExemple {
	public static void main(String[] args) {
		int length = 8; // nombre de double (64bits)
		double[] array1 = new double[length];
		double[] array2 = new double[length];
		double[] result = new double[length];

		// Initialise les vecteurs avec des valeurs aléatoires
		Arrays.setAll(array1, i -> Math.random());
		Arrays.setAll(array2, i -> Math.random());

		// Défini l'organisation des vecteurs (512bits = 64*8)  
		VectorSpecies<Double> species = DoubleVector.SPECIES_512;
		//transfert les données dans des vecteurs SIMD
		DoubleVector a = DoubleVector.fromArray(species, array1, 0);
		DoubleVector b = DoubleVector.fromArray(species, array2, 0);
		//8 additions  en une opération
		DoubleVector sum = a.add(b);
		//transfert le résultat dans un vecteur classique
		result = sum.toArray();
		//affichage
		System.out.println("Result: " + Arrays.toString(result));
	}
}
