package exemples.java;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JavaWhileFor {
	public static void main(String[] args) {

		int a = 1, b = 2;

		// if
		if (a < b)
			a = a + 1;
		else
			b = b - 1;

		// Switch
		switch (a) {
		case 1:
			System.out.println("a=1");
			break;
		case 2:
			System.out.println("a=2");
		default:
			System.out.println("a<>1 et 2");
		}

		// Opérateur ?:
		System.out.println(a < b ? "a est plus petit" : "b est plus petit ou égal à a");

		// while1
		do {
			a = a + 2;
			b = b + 1;
		} while (a < b);
		// while2
		while (a < b) {
			a = a + 2;
			b = b + 1;
		}

		// For
		for (int i = 0; i < 10; i++)
			System.out.print(i + " ");
		System.out.println();

		// For each
		List<String> liste = Arrays.asList("Didier", " Alain");
		for (String s : liste)
			System.out.print(s + " ");
		System.out.println();

		// Iterateur
		Iterator<String> iter = liste.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println();
	}
}
