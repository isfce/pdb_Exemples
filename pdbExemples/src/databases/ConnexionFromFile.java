package databases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Permet de configurer les paramètres de connexion dans un fichier
 * xxx.properties avec les clés suivantes user = ... (obligatoire) password =
 * ... (obligatoire) encoding = ... (default None) role = ... (default pas de
 * role) port = .... (default 3050) file = .... (default parking) ip = ...
 * (default localhost)
 * 
 * @author vo
 *
 */
public class ConnexionFromFile implements IConnexionInfos {
	private static final Logger logger = LoggerFactory.getLogger(ConnexionFromFile.class);
	// map de propriétés
	private Properties props = new Properties();

	/**
	 * Doit contenir le chemin et le nom du fichier contenant les propriétés info:
	 * ./ressources/connexion.properties (représente un fichier avec un chemin
	 * relatif)
	 * 
	 * @param filename
	 * @throws PersistanceException 
	 */
	public ConnexionFromFile(String filename, Databases sgbd) throws PersistanceException {

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			props.load(br);
			props.putIfAbsent("url",
						sgbd.buildServeurURL(
								props.getProperty("file", "emp"),
								props.getProperty("ip", "localhost"),
								props.containsKey("port")?
								 Integer.parseInt(props.getProperty("port")): sgbd.getDefaultPort()
							)
						);
		 logger.info("url: "+props.getProperty("url"));
		} catch (IOException e) {
			logger.error("Problème de chargement du fichier "+filename+" : "+e.getMessage());
			throw new PersistanceException("Problème de chargement du fichier "+filename+" : "+e.getMessage());

		}

	}

	@Override
	public Properties getProperties() {
		return props;
	}

}
