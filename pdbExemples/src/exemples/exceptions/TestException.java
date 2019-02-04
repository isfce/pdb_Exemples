package exemples.exceptions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class TestException {
	static Set<String> listeDBs = new HashSet<String>(Arrays.asList("H2", "H2MEM", "FIREBIRD"));

	// throws propage l'exception à l'appelant
	public static String getURL(String nomDB) throws ExceptionVO {
		if (!listeDBs.contains(nomDB))
			// Crée et propage une eception VO
			throw new ExceptionVO("Base de données non traitée");
		switch (nomDB) {
		case "H2":
			return "jdbc:h2:";
		case "H2MEM":
			return "jdbc:h2:mem";
		case "FIREBIRD":
			return "jdbc:firebirdsql:localhost/3050:";
		default:
			return null;
		}
	}

	public static void main(String[] args) {

		try {
			System.out.println(getURL("HBROL"));
		} catch (ExceptionVO e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

}
