package databases;
/**
 * Exception générée lors d'un problème de connexion
 * @author Didier
 *
 */
public class PersistanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistanceException(String message) {
		super(message);
	}

}
