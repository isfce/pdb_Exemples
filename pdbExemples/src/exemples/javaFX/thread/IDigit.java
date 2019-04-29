package exemples.javaFX.thread;

public interface IDigit {

	/**
	 * Indique le caractère à afficher 0..9 ou A
	 * 
	 * @param car
	 */
	public abstract void setCar(char car);

	/**
	 * Spécifie le caractère à afficher sous forme d'un entier
	 * 
	 * @param car
	 *            0..9
	 */
	public abstract void setCar(int car);

}