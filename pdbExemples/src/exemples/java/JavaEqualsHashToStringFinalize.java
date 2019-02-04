package exemples.java;

import java.util.HashSet;
import java.util.Set;

class Chien {
	

	private String nom;
	private Integer age;

	public Chien(String nom, Integer age) {
		super();
		this.nom = nom;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Chien [nom=" + nom + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chien other = (Chien) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Destruction du chien "+nom);
		super.finalize();
	}
}

public class JavaEqualsHashToStringFinalize {

	public static void main(String[] args) {

		Chien c1 = new Chien("Rex", 5);
		Chien c2 = new Chien("Rex", 5);
		System.out.println(c1.equals(c2));
		Set<Chien> ens= new HashSet<>();
		ens.add(c1);ens.add(c2);
		System.out.println(ens);
		
		
		Chien c3 = new Chien("Polux", 2);
		System.out.println(c3);
		c3=null;//Perte de Polux
		
		System.gc();//Force le garbage Collector à travailler
		//Suppression de Rex c1
		ens.remove(c1);// supprime de l'ensemble
		c1=null;// perte de toutes les adresses de c1
		System.gc();//Force le garbage Collector à travailler
		//Pause de 5s
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
		
		
	}
}
