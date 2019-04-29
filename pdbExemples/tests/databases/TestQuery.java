package databases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.AfterClass;

public class TestQuery {
	String SQL00 = "SELECT * FROM COUNTRY WHERE CURRENCY='Euro'";
	String SQL01 = "SELECT a.COUNTRY, a.CURRENCY FROM COUNTRY a where a.CURRENCY=?";
	String SQL02 = "CALL GET_EMP_PROJ(?,?)";

	String SQL04 = "INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, DEPT_NO, JOB_CODE,JOB_GRADE, JOB_COUNTRY, SALARY)"
			+ "VALUES ('Test', 'Test','600','VP','2','USA',80000.00) RETURNING EMP_NO, HIRE_DATE ";
	String SQL05 = "INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, DEPT_NO, JOB_CODE,JOB_GRADE, JOB_COUNTRY, SALARY)"
			+ "VALUES ('Test', 'Test','600','VP','2','USA',80000.00)";

	private Connection connexion;

	@Test
	public void testStatement() throws SQLException {
		int cpt = 0;
		// Création query préparé
		Statement q00 = connexion.createStatement();

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

	@Test
	public void testInsertGetKey() throws SQLException {
		int cpt = 0;
		// Création query préparé
		Statement q00 = connexion.createStatement();

		// Exécution du query
		boolean res = q00.execute(SQL04);
		// S'il y a un resultset je l'utilise
		if (res) {
			// Récupère le RS
			ResultSet rs = q00.getResultSet();

			while (rs.next()) {
				//System.out.println("Champ1: "+rs.getInt(1));
				//System.out.println("Champ2: "+rs.getTimestamp(2));
				assertNotNull(rs.getInt(1));
				assertNotNull(rs.getTimestamp(2));
				cpt++;
			}
			assertEquals(cpt, 1);
			
			connexion.rollback();
			q00.close();
		} else
			fail();
	}
	@Ignore // Ne fonctionne pas avec Firebird
	@Test
	public void testInsertGetKey2() throws SQLException {
		int cpt = 0;
		// Création query préparé
		Statement q00 = connexion.createStatement();

		// Exécution du query
		boolean res = q00.execute(SQL05, new String[] {"EMP_NO"} );
		// S'il y a un resultset je l'utilise
		if (res) {
			// Récupère le RS
			ResultSet rs = q00.getGeneratedKeys();

			while (rs.next()) {
				//System.out.println("Champ1: "+rs.getInt(1));
				
				assertNotNull(rs.getInt(1));
				
				cpt++;
			}
			assertEquals(cpt, 1);
			
			connexion.rollback();
			q00.close();
		} else
			fail();
	}

	@BeforeClass
	public void beforeClass() throws PersistanceException {
		ConnexionSingleton
				.setInfoConnexion(new ConnexionFromFile("./ressources/connexion_test.properties", Databases.FIREBIRD));
		connexion = ConnexionSingleton.getConnexion();

	}

	@AfterClass
	public void afterClass() {
		ConnexionSingleton.liberationConnexion();
	}

}
