package exemples.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJDBC {

	// Les Loggers sont thread safe, on peut les mettre "static"
	// La configuration du logger se fait dans le fichier: simpleLogger.properties

	private static final Logger logger = LoggerFactory.getLogger(TestJDBC.class);

	public static Connection connection = null;

	public static void main(String[] args) {
		Statement query1;
		// Chargement du Driver
		// Remarque: Cette opération n'est plus obligatoire,
		// elle se réalise lors de l'obtention de la connexion
		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
			// Class.forName("org.h2.Driver").newInstance();
			// jdbc:firebirdsql:192.168.0.5/3050:emp

			logger.info("Chargement du driver: OK");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// Log de l'exception
			logger.error("Impossible de charger le driver", e);
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur de chargement de pilote",
					JOptionPane.ERROR_MESSAGE);
			// Arret de l'application
			System.exit(2);
		}

		// Obtenir une connexion
		// Définir une Map avec les paires "clé-valeur"
		Properties props = new Properties();
		props.setProperty("user", "etudiant");
		props.setProperty("password", "isfce");
		props.setProperty("encoding", "NONE");

		try (Connection connection = 
				DriverManager.getConnection("jdbc:firebirdsql:192.168.0.5/3050:emp", props);
				//DriverManager.getConnection("jdbc:firebirdsql:localhost/3051:employee", props)
				
				) {
			
			connection.setAutoCommit(false);
			logger.info("Yes I'm connected");

			query1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			logger.info("Un Query est correctement créé");
			ResultSet res = query1.executeQuery(
					"SELECT a.EMP_NO, a.FIRST_NAME, a.LAST_NAME, a.PHONE_EXT,a.Hire_Date From Employee a");
			logger.info("La requête s'est exécutée correctement");
			// On parcourt le ResultSet
			while (res.next()) {
				System.out.println(" id : " + res.getInt(1) + " prénom: " + res.getString(2) + " nom: "
						+ res.getString(3) + " date: " + res.getDate(5));
			}

			query1.close();
			logger.info("Fermeture du Query");

		} catch (SQLException e1) {
			logger.error("Un problème est survenu", e1);
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur de chargement de pilote",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
