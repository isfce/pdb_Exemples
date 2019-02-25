package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnexionSingleton {
	// Connexion unique à une base de données
	private static Connection connexionDB;
	private static final Logger logger = LoggerFactory.getLogger(ConnexionSingleton.class);

	// Interface pour obtenir les infos de connexion
	private static IConnexionInfos infosConnexion;

	// Construction privée
	private ConnexionSingleton() {
	};

	/**
	 * Fournir la façon de créer les infos de connexion
	 * 
	 * @param info
	 */
	public static void setInfoConnexion(IConnexionInfos info) {
		infosConnexion = info;
		// On libère la connexion si on change les infos de connexion
		liberationConnexion();
	}

	/**
	 * Gestion d'une connexion unique
	 * 
	 * @return une connexion à la base de données ou null en cas d'erreur
	 * @throws PersistanceException
	 */
	public static Connection getConnexion() throws PersistanceException {
		if (infosConnexion == null) {
			logger.error("Il manque un objet InfoConnexion !");
			throw new PersistanceException("Il manque un objet InfoConnexion !");
		}
		if (connexionDB == null) {
			// Définir une Map avec les paires "clé-valeur"
			Properties props = infosConnexion.getProperties();
			logger.info("Chargement de la liste de Property: OK");
			String url = props.getProperty("url");

			try {
				connexionDB = DriverManager.getConnection(url, props);
				logger.info("Obtention de la connexion: OK");

				String autoCommmit = props.getProperty("autoCommit", "true");
				if ("false".equalsIgnoreCase(autoCommmit))
					connexionDB.setAutoCommit(false);
			} catch (SQLException e) {
				logger.error("problème d'ouverture de connexion: " + e.getMessage());
				liberationConnexion();
				throw new PersistanceException("Problème d'ouverture de connexion: " + e.getMessage());
			}
		}
		return connexionDB;
	}

	public static void liberationConnexion() {
		if (connexionDB != null) {
			try {
				connexionDB.close();
				logger.info("Fermeture de la connexion: OK");
			} catch (SQLException e1) {
			}
			connexionDB = null;
		}
		logger.info("Connexion libérée: OK");
	}

}
