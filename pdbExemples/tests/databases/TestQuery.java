package databases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;

import org.testng.annotations.AfterClass;

public class TestQuery {
	String SQL00 = "SELECT * FROM COUNTRY WHERE CURRENCY='Euro'";
	String SQL01 = "SELECT a.COUNTRY, a.CURRENCY FROM COUNTRY a where a.CURRENCY=?";
	String SQL02 = "CALL GET_EMP_PROJ(?,?)";
	private Connection connexion;
	
	@Test
	public void testStatement() throws SQLException {
		int cpt = 0;
		// Création query préparé
		 Statement q00= connexion.createStatement();
		
		// Exécution du query
		boolean res = q00.execute(SQL00);
		// S'il y a un resultset je l'utilise
		if (res) {
			// Récupère le RS
			ResultSet rs = q00.getResultSet();
			
			while (rs.next()) {
				cpt++;
			}
			assertEquals(cpt, 6);

			connexion.commit();
			q00.close();

		} else
			fail();
	}

	@Test
	public void testPreparedStatement() throws SQLException {
		int cpt = 0;
		// Création query préparé
		PreparedStatement q01 = connexion.prepareStatement(SQL01);
		// associe le paramètre
		q01.setString(1, "Euro");
		// Exécution du query
		boolean res = q01.execute();
		// S'il y a un resultset je l'utilise
		if (res) {
			// Récupère le RS
			ResultSet rs = q01.getResultSet();

			while (rs.next()) {
				cpt++;
			}
			assertEquals(cpt, 6);

			connexion.commit();
			q01.close();

		} else
			fail();
	}

	@Test
	public void testCallableQuery() throws SQLException {
		int cpt = 0;

		CallableStatement q02 = connexion.prepareCall(SQL02);
		// Associer le Param d'entrée
		q02.setInt(1, 8);
		// Associer le type du param de retour
		q02.registerOutParameter(2, Types.VARCHAR);

		// exécution du query
		boolean res = q02.execute();
		// S'il y a un resultset je l'utilise
		if (res) {
			// Récupère le RS
			ResultSet rs = q02.getResultSet();

			while (rs.next()) {
				cpt++;
			}
			assertEquals(cpt, 3);

			connexion.commit();
			q02.close();

		} else
			fail();

	}

	@BeforeClass
	public void beforeClass() throws PersistanceException {
		ConnexionSingleton.setInfoConnexion(new ConnexionFromFile("./ressources/connexion_test.properties", Databases.FIREBIRD));
		connexion = ConnexionSingleton.getConnexion();

	}

	@AfterClass
	public void afterClass() {
		ConnexionSingleton.liberationConnexion();
	}

}
