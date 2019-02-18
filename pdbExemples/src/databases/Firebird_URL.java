/**
 * modele.database.Firebird_URL
 *
 * @author Matthieu Schmit 
 * @date 12-05-2017 14:47:38
 *
 */

package databases;

public class Firebird_URL implements IDbURL {

	@Override
	public String getUrl() {
		return "jdbc:firebirdsql:";
	}

	/**
	 * Ne gère pas le mode mémoire
	 * 
	 * @param file 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public String buildMemURL(String file) {
		throw new UnsupportedOperationException("Firebird ne supporte pas le mode memoire !");
	}

	/**
	 * jdbc:firebirdsql:embedded:[<path>]<databaseName>
	 * 		jdbc:firebirdsql:embedded:emp
	 *		jdbc:firebirdsql:embedded:c:\test
	 * 
	 * @param file chemin et nom de fichier || alias
	 * @return une URL embarquée
	 */
	@Override
	public String buildEmbeddedURL(String file) {
		return this.getUrl() + "embedded:" + file;
	}

	/**
	 * jdbc:firebirdsql:<server>/[:<port>]:[<path>]<databaseName>
	 * 		jdbc:firebirdsql:localhost/3050:emp
	 * 		jdbc:firebirdsql:192.168.10.5/3050:resto
	 * 
	 * @param file chemin et nom de fichier || alias
	 * @param ip localhost ou une adresse IP
	 * @return une URL serveur avec le port 3050 par défaut
	 */
	@Override
	public String buildServeurURL(String file, String ip) {
		return buildServeurURL(file, ip, getDefaultPort());
	}

	/**
	 * jdbc:firebirdsql:<server>/[:<port>]:[<path>]<databaseName>
	 * 		jdbc:firebirdsql:localhost/3050:emp
	 * 		jdbc:firebirdsql:192.168.10.5/3050:resto
	 * 
	 * @param file chemin et nom du fichier || alias
	 * @param ip localhost ou une adresse IP
	 * @param port le port TCP
	 * @return une URL serveur avec le port précisé et l'ip précisée
	 */
	@Override
	public String buildServeurURL(String file, String ip, int port) {
		return this.getUrl() + ip + "/" + port + ":" + file;
	}

	@Override
	public boolean hasMemoryMode() {
		return false;
	}

	@Override
	public boolean hasEmbeddedMode() {
		return true;
	}

	@Override
	public boolean hasServeurMode() {
		return true;
	}

	@Override
	public int getDefaultPort() {
		return 3050;
	}
	
}
