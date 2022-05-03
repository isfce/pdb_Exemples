package exemples.java08;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class J8 {
	public static void main(String[] s) {
		System.out.println("============== Auto-Boxing et UnBoxing ===============");
		// *****************************************************
		// Exemple d'auto-boxing
		int i;
		Integer oi;
		oi = 4; // autoboxing d'un type primitif vers une instance de Integer
		i = oi; // unboxing d'un objet vers un type primitif
		// à la place de
		oi = new Integer(5);
		i = oi.intValue();
		// *****************************************************
		// Exemple d'une collection de type vecteur et utilisation de
		// l'interface List
		System.out.println("==========Différentes façons de trier une liste ===============");
		List<Integer> v = new ArrayList<>();
		v.add(4);
		v.add(2);
		/*
		 * Collections.sort(v,new Comparator<Integer>() {
		 * 
		 * @Override public int compare(Integer o1, Integer o2) { return
		 * o1.intValue()-o2.intValue(); } });
		 */
		// Exemple d'une lambda expression pour trier un vecteur
		// Sol1 avant j8
		Collections.sort(v);
		// Sol2 en précisant un comparateur
		Collections.sort(v, ( Integer o1, Integer o2) -> o1 - o2);
		// Sol3 réutilise un comparateur de la classe Integer
		Collections.sort(v, Integer::compare);
		// Sol4 Directement sur l'objet
		v.sort(Integer::compare);
		v.sort((o1,o2)->o1-o2);//ou encore

		// Ecriture du vecteur
		System.out.println(v);

		System.out.println("============== LocalDate et LocalTime ===============");
		// Gestion des dates:
		LocalDate date = LocalDate.of(2014, 3, 18);
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		boolean leap = date.isLeapYear();
		System.out.println(year);
		System.out.println(day);
		System.out.println(dow);
		System.out.println(len + "  " + leap);

		LocalTime time = LocalTime.of(13, 45, 20);
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();

		LocalDate date2 = LocalDate.parse("2014-03-18");
		LocalTime time2 = LocalTime.parse("13:45:20");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.of(2014, 3, 18);
		String formattedDate = date1.format(formatter);
		LocalDate date3 = LocalDate.parse(formattedDate, formatter);

		LocalDate lc = LocalDate.now();
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println(lc.format(sdf));

		System.out.println("=============Exemple Stream ====================");
		// Exemples d'utilisation de stream
		List<Integer> vv = Arrays.asList(10, 2, 23, 11);
		System.out.println("LIST stream " + vv.stream().max(Integer::compare).get());
		System.out.println("LIST stream " + vv.stream().sorted(Integer::compare).collect(Collectors.toList()));

		System.out.println("======== Steam Initialisation d'un vecteur====");
		// vecteur initialisé de 1 à 100
		long[] vl = LongStream.rangeClosed(1, 100).toArray();

		// test compare
		// Comparator<Object> cpr=Comparator.comparing();
		// Affiche tous les nbrs pairs de v1
		List<Integer> v1 = Arrays.asList(1, 3, 5, 6);
		v1.stream().forEach(System.out::print);
		System.out.println();
		List<Integer> v2 = v1.stream().filter(j -> j % 2 == 0).collect(Collectors.toList());
		System.out.println(v2);

		// Optional ==> permet d'envelopper un objet null ou pas
		//Très bon lien sur Optional:
		//https://dzone.com/articles/using-optional-correctly-is-not-optional
		System.out.println("=============Optional======================");
		
		// elem est à null ou pas 1 chance sur 2
		String elem = null;
		if (Math.random() * 10 < 5)
			elem = "Didier";
		// création d'un objet optional en fonction qu'elem soit à null ou pas
		Optional<String> oS1 = Optional.ofNullable(elem);
		// exécute un code en fonction que l'elem existe
		if (oS1.isPresent()) {
			System.out.println("Elem :" + oS1.get());
		} else
			System.out.println("ELem : null");
		// Version plus courte
		System.out.println("Elem :" + oS1.orElse("null"));

		// applique une fonction sur l'élément s'il est présent
		oS1.ifPresent(System.out::println);

		// Enveloppe un objet non null dans un optional
		Optional<String> oS2 = Optional.of("Hello Optional");
		// Création d'un optional avec la valeur null
		// oS2=Optional.empty();
		// Retourne l'élément s'il est présent sinon génère une exception
		System.out.println(oS2.orElseThrow(IllegalStateException::new));
		
		System.out.println("=============String Join ======================");
		//Exemple 1		
		 List maListe = Arrays.asList("Bruxelles", "Paris", "NewYork");
	        String joined1 = String.join("==>", maListe);
	        System.out.println("Joined String : " + joined1);
	    //Exemple 2
	        StringJoiner joined2 = new StringJoiner(";");
	        joined2.add("Bruxelles");
	        joined2.add("Paris");
	        joined2.add("NewYork");
		System.out.println("Joined String : " + joined2);
		//Exemple3
		StringJoiner joined3 = new StringJoiner(";").add("Bruxelles").add("Paris").add("NewYork");
		System.out.println("Joined String : " + joined3);
	}
}