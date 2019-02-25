package databases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class Test_URL {
	@Test
	public void testH2() {
		// assertEquals(Databases.valueOf("H2"),Databases.H2);
		// assertEquals(Databases.H2.ordinal(), 0);
		// assertEquals(Databases.values()[0],Databases.H2);
		assertTrue(Databases.H2.hasMemoryMode());
		assertTrue(Databases.H2.hasEmbeddedMode());
		assertTrue(Databases.H2.hasServeurMode());
		assertEquals(Databases.H2.buildMemURL(null), "jdbc:h2:mem:");
		assertEquals(Databases.H2.buildMemURL("brol"), "jdbc:h2:mem:brol");
	}

	@Test
	public void testFirebirdURL() {
		// assertEquals(Databases.FIREBIRD, Databases.valueOf("FIREBIRD"));
		// assertEquals(Databases.FIREBIRD.ordinal(), 1);
		assertFalse(Databases.FIREBIRD.hasMemoryMode(), "Elle ne doit pas posséder un mode mémoire");
		assertTrue(Databases.FIREBIRD.hasEmbeddedMode(), "Elle doit posséder un mode embarqué");
		assertTrue(Databases.FIREBIRD.hasServeurMode(), "Elle doit posséder un mode serveur");
		assertEquals(Databases.FIREBIRD.buildEmbeddedURL("c:\test"), "jdbc:firebirdsql:embedded:c:\test");
		assertEquals(Databases.FIREBIRD.buildServeurURL("brol", "localhost"), "jdbc:firebirdsql:localhost/3050:brol");
		assertEquals(Databases.FIREBIRD.buildServeurURL("brol", "192.168.0.5", 3051),
				"jdbc:firebirdsql:192.168.0.5/3051:brol");
	}

	/**
	 * On ne doit pas pouvoir construire une URL mémoire avec Firebird ==> une
	 * exception doit être provoquée si on essaye de construire une url mémoire
	 */
	@Test(expectedExceptions = UnsupportedOperationException.class)
	public void testFirebirdNonMem() {
		Databases.FIREBIRD.buildMemURL("");
	}

}
