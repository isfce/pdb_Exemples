package exemples.java8;

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
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

public class J8 {
	//Optional
	public void execute1() {

	    Optional<String> maybeString = Optional.empty();//Optional.of("foo");

	    String newString = maybeString.map(this::runIfExist).orElse(runIfEmpty());

	    System.out.println(newString);

	}

	private String runIfExist(String str) {

	    System.out.println("only run if optional is filled ");

	    return str;

	}

	private String runIfEmpty() {

	    System.out.println("only run if empty");

	    return "empty";

	}
	
	public static void main(String[] s) {
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
		Collections.sort(v, (Integer o1, Integer o2) -> o1.intValue() - o2.intValue());

		// Ecriture du vecteur
		System.out.println(v);

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

		// Exemples d'utilisation de stream
		List<Integer> vv = Arrays.asList(10, 2, 23, 11);
		System.out.println("LIST stream " + vv.stream().max(Integer::compare).get());
		System.out.println("LIST stream " + vv.stream().sorted(Integer::compare).collect(Collectors.toList()));
		
		//vecteur initialisé de 1 à 1000
		long[] vl = LongStream.rangeClosed(1, 1000).toArray();
		
		// test constr
		Supplier<String> sst = String::new;
		String sfsst = sst.get();
		JOptionPane.showMessageDialog(null, sfsst);
		
		
		// test compare
		// Comparator<Object> cpr=Comparator.comparing();
        // Affiche tous les nbrs pairs de v1
		List<Integer> v1 = Arrays.asList(1, 3, 5, 6);
		v1.stream().forEach(System.out::print);
		
		List<Integer> v2 = v1.stream().filter(j -> j % 2 == 0).collect(Collectors.toList());
		System.out.println(v2);
		
		//Optionnal
		System.out.println("=============Optional======================");
		J8 j8=new J8();
		j8.execute1();
		
		
		
		
	}
}