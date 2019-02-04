package exemples.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class JavaChargerPropertiesFromFile {

	public static void main(String[] args) {
		//Création d'une map de  Properties
		Properties props = new Properties();
		//Spécifie un fichier dans les ressources
		String filename = System.getProperty("user.dir") + "/ressources/connexion.properties";
		//Charge les properties du fichier
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			props.load(br);
			//Si une clé n'est pas spécifiée, on la rajoute
			if (!props.containsKey("url"))
				props.setProperty("url", "c:\\dbs\\");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		System.out.println(props);
	}

}
