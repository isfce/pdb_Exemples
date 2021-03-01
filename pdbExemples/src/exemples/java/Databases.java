package exemples.java;
/**
 * Exemple de classe énumérée
 * @author Didier
 *
 */
public enum Databases {
	FIREBIRD(3050), H2(4080), MYSQL(3389), ORACLE(4001);

	private int port;

	/**
	 * @param port
	 */
	private Databases(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public static void main(String[] args) {

		// Obtenir l'odre et le nom d'un �l�ment �num�r�
		System.out.println(Databases.H2.name());
		System.out.println(Databases.H2.ordinal());

		System.out.println(Databases.H2.getPort());

		// Construction d'un �l�ment enum�r� � partir de son nom (string)
		String db = "H2";
		Databases b1 = Databases.valueOf(db);
		System.out.println(b1);

		// construction d'un �l�ment �num�r� � partir deson ordre (int)
		int ordre = 1;
		Databases b2 = Databases.values()[ordre];
		System.out.println(b2);
	}

}
