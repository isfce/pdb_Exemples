package databases.uri;
/**
 * 
 * @author VO
 *
 */
public enum Databases implements IDbURL {
	H2(new H2_URL()), FIREBIRD(new Firebird_URL());

	private IDbURL dbURL;

	/**
	 * Constructeur privé pour fournir une implémentation de construction d'URI
	 *  
	 * @param dbURL un objet qui implémente IDBURL pour la base de données associée
	 */
	private Databases(IDbURL dbURL) {
		this.dbURL = dbURL;
	}

	@Override
	public String getUrl() {
		return dbURL.getUrl();
	}

	@Override
	public String buildMemURL(String file) {
		return dbURL.buildMemURL(file);
	}

	@Override
	public String buildEmbeddedURL(String file) {
		return dbURL.buildEmbeddedURL(file);
	}

	@Override
	public String buildServeurURL(String file, String ip) {
		return dbURL.buildServeurURL(file, ip);
	}

	@Override
	public String buildServeurURL(String file, String ip, int port) {
		return dbURL.buildServeurURL(file, ip, port);
	}

	@Override
	public boolean hasMemoryMode() {
		return dbURL.hasMemoryMode();
	}

	@Override
	public boolean hasEmbeddedMode() {
		return dbURL.hasEmbeddedMode();
	}

	@Override
	public boolean hasServeurMode() {
		return dbURL.hasServeurMode();
	}

	@Override
	public int getDefaultPort() {
		return dbURL.getDefaultPort();
	}
}
