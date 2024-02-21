package databases.connexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import databases.uri.Databases;
import lombok.extern.slf4j.Slf4j;

/**
 * Permet de configurer les paramètres de connexion dans un fichier
 * xxx.properties avec les clés suivantes user = ... (obligatoire) password =
 * ... (obligatoire) encoding = ... (default None) role = ... (default pas de
 * role) port = .... (default 3050) file = .... (default emp) ip = ...
 * (default localhost) (autoCommit :default true)
 * 
 * @author vo
 *
 */
@Slf4j
public class ConnexionFromFile implements IConnexionInfos {
	// map de propriétés
	private Properties props = new Properties();

	/**
	 * Le chemin et le nom du fichier contenant les propriétés info:
	 * ./ressources/connexion.properties (représente un fichier avec un chemin
	 * relatif) s'il est à null le chemin "./ressources/connexion.properties" sera
	 * utilisé
	 * 
	 * @param filename le nom du fichier qui contient les informations de connexion
	 * @param sgbd     le type de base de données
	 * @throws PersistanceException
	 */
	public ConnexionFromFile(String filename, Databases sgbd) throws PersistanceException {
		// défini un chemin par défaut s'il n'est pas précisé
		if (filename == null)
			filename = "./ressources/connexion.properties";
		// Try with resources pour la connexion au fichier
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			props.load(br);// charge toutes les proprités du fichier dans la map
			// construit une URL si elle n'est pas présente
			props.putIfAbsent("url", 
					sgbd.buildServeurURL(
							props.getProperty("file", "emp"),//defaut "emp"
							props.getProperty("ip", "localhost"),//default "localhost"
							props.containsKey("port") ? 
								Integer.parseInt(props.getProperty("port")) : sgbd.getDefaultPort()
								// prend le port par défaut de la BD si la propriété n'est pas présente
			));
			props.putIfAbsent("autoCommit", "true");
			
			log.info("url: " + props.getProperty("url"));
		} catch (IOException e) {
			log.error("Problème de chargement du fichier " + filename + " : " + e.getMessage());
			throw new PersistanceException("Problème de chargement du fichier " + filename + " : " + e.getMessage());

		}

	}

	@Override
	public Properties getProperties() {
		return props;
	}

}
