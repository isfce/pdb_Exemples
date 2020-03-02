package exemples.exceptions;

import javax.swing.JOptionPane;

public class TestException {
	// throws propage l'exception à l'appelant
	public static String getURL(String nomDB) throws ExceptionVO  {
		switch (nomDB) {
		case "H2":
			return "jdbc:h2:";
		case "H2MEM":
			return "jdbc:h2:mem";
		case "FIREBIRD":
			return "jdbc:firebirdsql:localhost/3050:";
		default:
			throw new ExceptionVO("Base de données non traitée", nomDB);
		}
	}

	public static void main(String[] args) {

		try {
			System.out.println(getURL("H2"));
			System.out.println(getURL("HBROL"));//déclenche l'exception
			System.out.println(getURL("H2MEM"));//sera jamais exécuté
		} catch (ExceptionVO e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"  data: "+e.getData());
		}

	}

}
