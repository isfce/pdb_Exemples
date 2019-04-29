package exemples.javaFX.properties;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

public class Place {
	private StringProperty code = new SimpleStringProperty();
	//private IntegerProperty taille = new SimpleIntegerProperty();
	//private IntegerProperty etage = new SimpleIntegerProperty();
	private BooleanProperty libre = new SimpleBooleanProperty(true);

	/**
	 * Construction d'une place par défaut libre
	 * 
	 * @param code
	 * @param taille
	 * @param etage
	 */
	public Place(String code, Integer taille, Integer etage) {
		super();
		this.code.set(code);
		//this.taille.set(taille);
		//this.etage.set(etage);
	}

	// un getter : getNomAttribut ()
	public String getCode() {
		return code.get();
	}

	// un setter : setNomAttribut (...)
	public void setCode(String code) {
		this.code.set(code);
	}

	// un getter pour la propriété : nomProperty ()
	public StringProperty codeProperty() {
		return code;
	}
	// Getter setter et getProperty

	public boolean getLibre() {
		return libre.get();
	}

	public void setLibre(boolean libre) {
		this.libre.set(libre);
	}

	public BooleanProperty libreProperty() {
		return libre;
	}
	
// Test
	
	public static void main(String[] args) {
		Place p1 = new Place("P01", 10, 1);
		
		// Permet de tester l'état "invalidation" avant l'appel d'un getter
		p1.libreProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable o) {

				System.out.println("Place dans l'état invalidé ");
			}
		});

		p1.libreProperty().addListener((ev, oldV, newV) -> {
			System.out.println("La place est " + (newV == true ? " libre " : " occupée "));
		});
		p1.setLibre(false);// indique que la place est occupée
		p1.setLibre(true);// indique que la place est libre
	
		//Exemple de liste observable
		ListProperty <Place > liste = new SimpleListProperty <>( FXCollections .<Place >
		observableArrayList ());
		liste.addListener (
				( ListChangeListener.Change <? extends Place > change ) ->{
				while ( change . next ()) {
				// obtenir la liste des éléments retirés
				change.getRemoved ().forEach (( Place suppr ) -> {
				System.out.println (" Place supprimée : "+ suppr . getCode ());
				});
				// obtenir la liste des éléments ajoutés
				change.getAddedSubList().forEach(( Place ajout ) -> {
				System.out.println (" Place ajoutée : "+ ajout . getCode ());
				});
				}
				});
		liste.add(p1);
		liste.add(new Place("P02",2,1));
		liste.remove(1);
		liste.get(0).setLibre(false);
	
	}

}
