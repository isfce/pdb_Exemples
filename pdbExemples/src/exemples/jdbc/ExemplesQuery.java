package exemples.jdbc;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Properties;

import databases.connexion.ConnexionSingleton;
import databases.connexion.PersistanceException;
import databases.uri.Databases;

public class ExemplesQuery {

	String SQL01 = "SELECT a.COUNTRY, a.CURRENCY FROM COUNTRY a where a.CURRENCY=?";
	String SQL02 = "CALL GET_EMP_PROJ(?,?)";

	String SQL05 = "INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, DEPT_NO, JOB_CODE,JOB_GRADE, JOB_COUNTRY, SALARY)"
			+ "VALUES ('Test', 'Test','600','VP','2','USA',80000.00)";
	String SQL06 = "INSERT INTO CUSTOMER (CUSTOMER, CONTACT_FIRST, CONTACT_LAST) VALUES ('UnClient', 'monPrenom', 'monNom') RETURNING CUST_NO";

	public static void main(String[] args) throws PersistanceException {

		// Création de la connexion
		Connection connexion;
		ConnexionSingleton.setInfoConnexion(() -> {
			Properties prop = new Properties();
			prop.put("url", Databases.FIREBIRD.buildServeurURL("employee", "localhost"));
			prop.put("user", "SYSDBA");
			prop.put("password", "masterkey");
			prop.put("autoCommit", "false");
			return prop;
		});
		connexion = ConnexionSingleton.getConnexion();

		// *****************************************************/
		// Création d'une Statement
		System.out.println("SQL00");
		String SQL00 = "SELECT * FROM COUNTRY WHERE CURRENCY='Euro' ";
		try (Statement q00 = connexion.createStatement();) {
			// Exécution du query qui retourne tjs un ResultSet
			// q00.setFetchSize(10);
			ResultSet rs = q00.executeQuery(SQL00);

			while (rs.next())
				System.out.println(rs.getString("COUNTRY"));
		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}
		System.out.println("SQL01--1");
		// Création d'une PreparedStatement
		String SQL01 = "SELECT a.COUNTRY, a.CURRENCY FROM COUNTRY a where a.CURRENCY=?";
		try (PreparedStatement q01 = connexion.prepareStatement(SQL01);) {
			q01.setString(1, "Euro");
			ResultSet rs = q01.executeQuery();
			while (rs.next())
				System.out.println(rs.getString("COUNTRY"));
			// changement du paramètre pour une 2ème exécution
			q01.setString(1, "Dollar");
			rs = q01.executeQuery();
			while (rs.next())
				System.out.println(rs.getString("COUNTRY"));
		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}
		System.out.println("----------- Callable Statement ----------------");
		String SQL02 = "CALL GET_EMP_PROJ(?,?)";
		try (CallableStatement q02 = connexion.prepareCall(SQL02)) {
			// Associer le Param d'entrée
			q02.setInt(1, 8);
			// Associer le type du param de retour
			q02.registerOutParameter(2, Types.VARCHAR);
			// exécution du query

			ResultSet rs = q02.executeQuery();
			while (rs.next())
				System.out.println(rs.getString(1));
			;
		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}
		System.out.println("----------- Callable Statement ----------------");
		String SQL022 = "?,?,?,? =CALL SUB_TOT_BUDGET(?)";
		try (CallableStatement q02 = connexion.prepareCall(SQL022)) {
			// Associer le Param d'entrée
			q02.setString(5, "100");
			// Associer le type du param de retour
			q02.registerOutParameter(1, Types.DECIMAL, 2);
			q02.registerOutParameter(2, Types.DECIMAL, 2);
			q02.registerOutParameter(3, Types.DECIMAL, 2);
			q02.registerOutParameter(4, Types.DECIMAL, 2);
			// exécution du query

			ResultSet rs = q02.executeQuery();
			while (rs.next())
				System.out.println(rs.getBigDecimal(1) + " , " + rs.getBigDecimal(2) + " , " + rs.getBigDecimal(3)
						+ " , " + rs.getBigDecimal(4));
			;
		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}

		System.out.println("----------- Callable Statement pas selectable ----------------");
		String SQL023 = "CALL TEST(?,?)";
		try (CallableStatement q02 = connexion.prepareCall(SQL023)) {
			// Associer le Param d'entrée
			q02.setString(1, "8");
			// Associer le type du param de retour
			q02.registerOutParameter(2, Types.SMALLINT);

			// exécution du query
			ResultSet rs = q02.executeQuery();
			while (rs.next())
				System.out.println(rs.getInt(1));
			;
		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}
		System.out.println("-------------- Insert Preparé --------------");
		// Création query préparé
		try (Statement q00 = connexion.createStatement()) {
			// Exécution du query
			String SQL04 = "INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, DEPT_NO, JOB_CODE,JOB_GRADE, JOB_COUNTRY, SALARY)"
					+ "VALUES ('Test', 'Test','600','VP','2','USA',80000.00) RETURNING EMP_NO, HIRE_DATE ";
			ResultSet rs = q00.executeQuery(SQL04);
			while (rs.next()) {
				System.out.println("Emp_NO: " + rs.getInt(1));

				LocalDate d1 = rs.getDate("HIRE_DATE").toLocalDate();
				System.out.println("Hire_Date: " + d1);
			}
		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}

		System.out.println("--------------- Test Batch 1----------------");
		// Création query préparé
		try (Statement q00 = connexion.createStatement()) {
			// Exécution du query
			// connexion.setAutoCommit(true);
			String SQL04 = "INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, DEPT_NO, JOB_CODE,JOB_GRADE, JOB_COUNTRY, SALARY)"
					+ "VALUES ('Test', 'Test','600','VP','2','USA',80000.00)";
			q00.addBatch(SQL04);
			q00.addBatch("create table tbrol( pk integer primary key, nom char(5))");
			int[] r = q00.executeBatch();
			System.out.println("1°) => " + r[0]);
			System.out.println("2°) => " + r[1]);
			// q00.getConnection().commit();

		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}
		System.out.println("--------------- Test Batch 2----------------");
		// Création query préparé
		try (PreparedStatement q06 = connexion.prepareStatement("INSERT INTO COUNTRY VALUES (?,?)")) {
			q06.setString(1, "Sweeden");
			q06.setString(2, "EURO");
			q06.addBatch();
			q06.setString(1, "Spain");
			q06.setString(2, "Euro");
			q06.addBatch();
			int[] r = q06.executeBatch();
			System.out.println("1°) => " + r[0]);
			System.out.println("2°) => " + r[1]);
			// q00.getConnection().commit();
		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}

		System.out.println("--------------- Test insert avec ResultSet bidirect ----------------");
		try (Statement q07 = connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);) {
			ResultSet rs = q07.executeQuery("SELECT * FROM COUNTRY");
			rs.moveToInsertRow();
			rs.updateString(1,"NewCountry");
			rs.updateString(2,"TheCoin");
			rs.insertRow();
			rs.beforeFirst();
			while (rs.next())
				System.out.println(rs.getString("COUNTRY")+" "+rs.getString("CURRENCY"));
		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}
		
System.out.println("--------------- Test update avec ResultSet bidirect ----------------");
		try (Statement q08 = connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);) {
			ResultSet rs = q08.executeQuery("SELECT * FROM COUNTRY");
			rs.first();//sur USA
			rs.updateString(2,"US-Dollar"); //adapte devise
			rs.updateRow();
			rs.absolute(5);//position sur Japon
			rs.updateString(2,"YEN2");//adapte la devise
			rs.updateRow();
			rs.beforeFirst();
			while (rs.next())
				System.out.println(rs.getString("COUNTRY")+" "+rs.getString("CURRENCY"));
		} catch (SQLException e) {
			System.err.println("Problème SQL:" + e.getMessage());
		}
		// Libération de la connexion
		ConnexionSingleton.liberationConnexion();
	}

}
