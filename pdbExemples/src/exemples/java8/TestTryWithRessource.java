package exemples.java8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class TestTryWithRessource {

	public static void main(String[] args) {
		String path = "c:/DBs/Texte.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			br.lines().forEach(s -> System.out.println(s));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

}
