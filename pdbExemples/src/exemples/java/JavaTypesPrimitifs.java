package exemples.java;

public class JavaTypesPrimitifs {

	// Affiche une matrice
	public static void afficheTableau(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			{
				for (int j = 0; j < m[i].length; j++)
					System.out.print(m[i][j] + " ");
				System.out.println();
			}
		}
	}

	/**
	 * Chaque type primitif possède une classe associée: byte ==> Byte, int ==>
	 * Integer,... L'AutoBoxing facilite la conversion d'un type primitif vers sa
	 * classe associée: Integer i1=45; L'Unboxing permet facilite l'assignation d'un
	 * objet vers son type primitif: int i2=i1;(i1 étant un objet Integer)
	 * 
	 */
	public static void main(String[] args) {
		/**
		 * Les entiers (complément à 2) 8,16,32,64
		 * 
		 */
		// Byte 8 bits ==> Byte
		byte b1 = 127, b2 = -128, b3 = 0x7F, b4 = 0x7_F, b5 = 0b01111111, b6 = Byte.MAX_VALUE;
		// Entier 16 bits ==> Short
		short e1 = 3267, e2 = -32768, e3 = 0x7FFF, e4 = Short.MAX_VALUE;
		// Entier 32 bits ==>Integer
		int i1 = 78, i2 = 0x7FFFFFFF, i3 = 0xFFFFFFFF, // i3==-1;
				i4 = Integer.MAX_VALUE;
		// Entier 64 bits ==> Long
		long l1 = 78L, l2 = 0x7FFF_FFFF_FFFF_FFFFL, // Constante Long doit se terminer par L
				l3 = Long.MAX_VALUE;

		// Entier 128 bits et plus ==> BigInteger
		// !!pas de type primitif

		/**
		 * Les réels 32 et 64 bits en IEEE_754
		 */
		// Réel 32 bits
		float f1 = 2.78f, // une constante float doit se terminer par f
				f2 = Float.POSITIVE_INFINITY, f3 = Float.NEGATIVE_INFINITY, f4 = Float.MAX_VALUE;

		// Double 64 bits
		double d1 = 2.78, d2 = +2E-5, d3 = Double.NEGATIVE_INFINITY, d4 = Double.POSITIVE_INFINITY,
				d5 = Double.MAX_VALUE;
		/**
		 * Les caractères
		 */
		// Caractères en Unicode
		char c1 = 'A', c2 = '\u0065';// c2==c1

		/**
		 * Booléen
		 */
		boolean t1 = true, t2 = false, t3 = t1 != t2; // résultat d'un test

		// Constantes avec final et par convention en majuscule
		// et séparation avec _
		final int TAILLE_X = 21;

		// Vecteurs
		int[] v = new int[10];
		System.out.println("Vecteur v taille: " + v.length);
		for (int i = 0; i < v.length; i++)
			System.out.print(v[i]);
		System.out.println();

		// Vecteurs 2 Dim
		int[][] m1 = new int[5][3];
		System.out.println("Tableau m1");
		afficheTableau(m1);
		// Vecteur 2 dim initialisé
		int[][] m2 = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println("Tableau m2");
		afficheTableau(m2);

	}

}
