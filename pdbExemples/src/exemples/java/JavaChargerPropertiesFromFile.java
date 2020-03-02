package exemples.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class JavaChargerPropertiesFromFile {

	public static void main(String[] args) {
		// Création d'une map de Properties
		Properties props = new Properties();
		// Spécifie un fichier dans les ressources
		String filename = System.getProperty("user.dir") + "/ressources/connexion.properties";
		// Charge les properties du fichier
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			props.load(br);
			System.out.println("Valeur de la clé url: "+props.getProperty("url"));
			System.out.println("Valeur de la clé url: "+props.getProperty("url","c:\\DefautValue\\"));
			props.putIfAbsent("url", "c:\\dbs\\");
			System.out.println("Valeur de la clé url: "+props.getProperty("url"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		System.out.println(props);
	}

}
