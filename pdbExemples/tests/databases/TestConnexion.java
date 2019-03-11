package databases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.sql.Connection;
import java.util.Properties;

import org.testng.annotations.AfterClass;

public class TestConnexion {
	@Test
	//@Ignore
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

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
