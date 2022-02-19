package exemples.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcExemple {
	public static void main(String[] args) {
		Statement query1;	//requête
		
		//Définir une Map avec les paramètres de la connexion"
		Properties props = new Properties();
		props.setProperty("user", "SYSDBA");
		props.setProperty("password", "masterkey");
		props.setProperty("encoding", "NONE");
		//Création de la connexion dans un Try With Resource
		try (Connection connection = 
				DriverManager.getConnection("jdbc:firebirdsql:localhost/3050:employee", props)
			) 
		{
			
		 connection.setAutoCommit(false);
		 //Création d'une requête de de type "Statement"	
		 query1 = connection.createStatement();//, lResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		 //exécution du query spécifié
		 ResultSet res = query1.executeQuery(
					"SELECT a.EMP_NO, a.FIRST_NAME, a.LAST_NAME, a.PHONE_EXT,a.Hire_Date From Employee a");
			
	  	// On parcourt le ResultSet
		 while (res.next()) {
		 	System.out.println(" id : " + res.getInt(1) + " prénom: " + res.getString(2) + " nom: "
						+ res.getString(3) + " date: " + res.getDate(5));
		 }
		//Fermeture du ResultSet
		 query1.close();
		} catch (SQLException e1) {
			System.err.println("Un problème est survenu: "+ e1.getMessage());
		}//La fermeture de la connexion est gérée par le Try With Resource
	}
}
