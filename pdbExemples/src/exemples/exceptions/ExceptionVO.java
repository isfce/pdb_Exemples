package exemples.exceptions;

//Checked exception
public class ExceptionVO extends Exception {
/**
	 * 
	 */
	private String data;
	
	private static final long serialVersionUID = -8713706648942171301L;

	// ajout d'autres propriétés si nécessaires
	public ExceptionVO(String msg,String data) {
		super(msg);
		this.data=data;
	}

	public String getData() {
		return data;
	}
	
}
