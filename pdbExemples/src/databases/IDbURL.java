package databases;

public interface IDbURL {
	/**
	 * Utilisé en interne
	 * 
	 * @return la première partie commune à tous les types d' URL
	 */
	String getUrl();

	/**
	 * Construiction d'une URL Mémoire
	 * 
	 * @param file
	 * @return une URL Mémoire
	 */
	String buildMemURL(String file);

	/**
	 * @param file chemin et nom de fichier ou alias
	 * @return une URL embarquée
	 */
	String buildEmbeddedURL(String file);

	/**
	 * 
	 * @param file chemin et nom de fichier ou alias
	 * @param ip   l'adresse IP en utilisant le port par défaut
	 * @return une URL serveur
	 */
	String buildServeurURL(String file, String ip);

	/**
	 * 
	 * @param file chemin et nom de fichier ou alias
	 * @param ip   l'adresse IP en utilisant le port par défaut
	 * @param port le port à utiliser
	 * @return une URL serveur
	 */
	String buildServeurURL(String file, String ip, int port);

	/**
	 * 
	 * @return le port par défaut du sgbdr
	 */
	int getDefaultPort();

	boolean hasMemoryMode();

	boolean hasEmbeddedMode();

	boolean hasServeurMode();

}