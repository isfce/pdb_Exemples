package databases.uri;

public class H2_URL implements IDbURL {
	@Override
	public String getUrl() {
		return "jdbc:h2:";
	}

	/**
	 * 1) null ==> une seule connexion possible (Bd sans nom) 2) Un nom ==>
	 * permet alors plusieurs connexions sur la bd
	 * 
	 * @param file
	 *            null ou un nom sans espace
	 * @return Une URL mémoire pour H2
	 * 
	 */
	@Override
	public String buildMemURL(String file) {
		return this.getUrl() + "mem:" + (file == null ? "" : file);
	}

	/**
	 * jdbc:h2:[file:][<path>]<databaseName> jdbc:h2:~/test ==> localisation par
	 * défaut des BD jdbc:h2:/data/sample ==> localisation relative
	 * jdbc:h2:file:C:/data/sample (Windows only) ==> chemin complet
	 * 
	 * @param file
	 *            chemin et nom de fichier
	 * @return une URL embarquée
	 */
	@Override
	public String buildEmbeddedURL(String file) {
		return this.getUrl() + file;
	}

	/**
	 * Exemples jdbc:h2:tcp://<server>[:<port>]/[<path>]<databaseName>
	 * jdbc:h2:tcp://localhost/~/test jdbc:h2:tcp://dbserv:8084/~/sample
	 * jdbc:h2:tcp://localhost/mem:test
	 * 
	 * @param file
	 * @param ip
	 *            localhost ou une adresse IP
	 * @return une URL serveur avec le port 8082 par défaut
	 */
	@Override
	public String buildServeurURL(String file, String ip) {
		return buildServeurURL(file, ip, getDefaultPort());
	}

	/**
	 * Exemples jdbc:h2:tcp://<server>[:<port>]/[<path>]<databaseName>
	 * jdbc:h2:tcp://localhost/~/test jdbc:h2:tcp://dbserv:8084/~/sample
	 * jdbc:h2:tcp://localhost/mem:test
	 * 
	 * @param file
	 *            chemin et nom du fichier
	 * @param ip
	 *            localhost ou une adresse IP
	 * @param port
	 *            le port TCP
	 * 
	 * @return une URL serveur avec le port précisé et l'ip précisée
	 */
	@Override
	public String buildServeurURL(String file, String ip, int port) {
		return this.getUrl() + "tcp://" + ip + ":" + port + "/" + file;
	}

	@Override
	public boolean hasMemoryMode() {
		return true;
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
		
		return 8082;
	}

}
