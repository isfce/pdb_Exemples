package databases;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import databases.uri.Databases;

public class Test_URL {
	@Test
	public void testH2() {
		// assertEquals(Databases.valueOf("H2"),Databases.H2);
		// assertEquals(Databases.H2.ordinal(), 0);
		// assertEquals(Databases.values()[0],Databases.H2);
		assertTrue(Databases.H2.hasMemoryMode(), "H2 Doit avoir un mode mémoire");
		assertTrue(Databases.H2.hasEmbeddedMode(), "H2 Doit avoir un mode embarqué");
		assertTrue(Databases.H2.hasServeurMode(), "H2 Doit avoir un mode serveur");
		assertEquals("jdbc:h2:mem:", Databases.H2.buildMemURL(null));
		assertEquals("jdbc:h2:mem:brol", Databases.H2.buildMemURL("brol"));
	}

	@Test
	public void testFirebirdURL() {
		// assertEquals(Databases.FIREBIRD, Databases.valueOf("FIREBIRD"));
		// assertEquals(Databases.FIREBIRD.ordinal(), 1);
		assertFalse(Databases.FIREBIRD.hasMemoryMode(), "Elle ne doit pas posséder un mode mémoire");
		assertTrue(Databases.FIREBIRD.hasEmbeddedMode(), "Elle doit posséder un mode embarqué");
		assertTrue(Databases.FIREBIRD.hasServeurMode(), "Elle doit posséder un mode serveur");
		assertEquals("jdbc:firebirdsql:embedded:c:\test", Databases.FIREBIRD.buildEmbeddedURL("c:\test"));
		assertEquals("jdbc:firebirdsql:localhost/3050:brol", Databases.FIREBIRD.buildServeurURL("brol", "localhost"));
		assertEquals("jdbc:firebirdsql:192.168.0.5/3051:brol",
				Databases.FIREBIRD.buildServeurURL("brol", "192.168.0.5", 3051));
	}

	/**
	 * On ne doit pas pouvoir construire une URL mémoire avec Firebird ==> une
	 * exception doit être provoquée si on essaye de construire une url mémoire
	 */
	@Test
	public void testFirebirdNonMem() {
		assertThrows(UnsupportedOperationException.class, () -> Databases.FIREBIRD.buildMemURL(""));
	}

}
