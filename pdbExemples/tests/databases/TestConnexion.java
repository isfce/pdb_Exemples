package databases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import databases.connexion.ConnexionFB_Employee;
import databases.connexion.ConnexionFromFile;
import databases.connexion.ConnexionSingleton;
import databases.connexion.IConnexionInfos;
import databases.connexion.PersistanceException;
import databases.uri.Databases;

public class TestConnexion {
	@Test
	public void testConnexionEmployeEcole() throws PersistanceException {
		IConnexionInfos info = new ConnexionFB_Employee();
		Properties map = info.getProperties();

		assertTrue(map.containsKey("url"));
		assertTrue(map.containsKey("user"));
		assertTrue(map.containsKey("password"));
		assertTrue(map.containsKey("autoCommit"));

		ConnexionSingleton.setInfoConnexion(info);
		Connection con = ConnexionSingleton.getConnexion();
		assertNotNull(con);
		ConnexionSingleton.liberationConnexion();
	}

	@Test
	public void testEmployeeFromFile() throws PersistanceException {
		IConnexionInfos info = new ConnexionFromFile("./ressources/connexion.properties", Databases.FIREBIRD);
		Properties map = info.getProperties();

		assertTrue(map.containsKey("url"));
		assertTrue(map.containsKey("user"));
		assertTrue(map.containsKey("password"));
		assertTrue(map.containsKey("autoCommit"));

		ConnexionSingleton.setInfoConnexion(info);
		Connection con = ConnexionSingleton.getConnexion();
		assertNotNull(con);

		ConnexionSingleton.liberationConnexion();

	}

}
