package exemples.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class javaDate {

	public static void main(String[] args) {
		Date d1 = new Date(); // date courante
		System.out.println("Date actuelle : " + d1);
		// Format
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Date actuelle formatée : " + sdf.format(d1));
		
		// Création d'une date
		String uneDate = "29/01/2018";
		try {
			Date d2 = sdf.parse(uneDate);
			System.out.println("Date non formatée : " + d2);
			System.out.println("Date formatée : " + sdf.format(d2));
		} catch (ParseException e) {
			System.out.println("String uneDate: " + uneDate + " erronée");
		}
		// Création d'une date mal formée
		String uneBadDate = "brol";
		Date d3;
		try {
			d3 = sdf.parse(uneBadDate);
			System.out.println("Date d3 : " + sdf.format(d3));
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			System.out.println("String uneBadDate: " + uneBadDate + " erronée");
		}
	}

}
