package exemples.exceptions;

//Checked exception
public class ExceptionVO extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = -8713706648942171301L;

	// ajout d'autres propriétés si nécessaires
	public ExceptionVO(String msg) {
		super(msg);
	}
}
