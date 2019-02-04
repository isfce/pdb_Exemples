package exemples.java;

public class JavaEqualsEgalite {
	//Exemple qui montre l'optimisation faite par la JRE pour
	//les 100 premiers(0..99) nombres entiers Integer
	//Et surtout de comparer des objets avec Equals et non ==
	public static void main(String[] args) {
      Integer i1=10;
      Integer i2=10;
            
      if (i1==i2) System.out.println("YES i1=i2");
      else  System.out.println("???? Pourquoi i1<>i2");
      Integer i11=1000;
      Integer i22=1000;
      if (i11==i22) System.out.println("YES i11=i22");
      else  System.out.println("???? Pourquoi i11<>i22");
      
      //Voici comment faire
      System.out.println("Avec Equals");
      if (i1.equals(i2)) System.out.println("YES i1=i2");
      else  System.out.println("???? Pourquoi i1<>i2");
   
      if (i11.equals(i22)) System.out.println("YES i11=i22");
      else  System.out.println("???? Pourquoi i11<>i22");
          
	}
}
