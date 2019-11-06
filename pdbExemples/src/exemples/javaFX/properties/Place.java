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

/**
 * Création d'un classe qui respecte la convention des beans qui utilisent des
 * attributs d'un type "xxxProperty"
 * 
 * @author Didier
 *
 */
public class Place {
	private StringProperty code = new SimpleStringProperty();

	private BooleanProperty libre = new SimpleBooleanProperty(true);

	/**
	 * Construction d'une place par défaut libre
	 * 
	 * @param code
	 */
	public Place(String code) {
		super();
		this.code.set(code);
		this.libre.set(true);
	}

	/* ------------------------------------------------------------------------- */
	/**
	 * un getter qui renvoie la valeur de la property dans un String standard
	 * 
	 * @return un String standard
	 */
	public String getCode() {
		return code.get();
	}

	/**
	 * un setter avec initialise la property à partir d'un String standard
	 * 
	 * @param code un String standard
	 */
	public void setCode(String code) {
		this.code.set(code);
	}

	/**
	 * un getter qui renvoie la property (format pour un attribut xxx:"xxxProperty")
	 * 
	 * @return une property StringProperty
	 */
	public StringProperty codeProperty() {
		return code;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * ---
	 */
	/**
	 * Extrait le boolean de la property
	 * 
	 * @return un boolean qui signale si la place est libre
	 */
	public boolean getLibre() {
		return libre.get();
	}

	/**
	 * Initialise la property
	 * 
	 * @param libre un boolean
	 */
	public void setLibre(boolean libre) {
		this.libre.set(libre);
	}

	/**
	 * Renvoie la property elle-même
	 * 
	 * @return la property qui enveloppe l'attribut libre
	 */
	public BooleanProperty libreProperty() {
		return libre;
	}
	/* ------------------------------------------------------------------------ */

// Test

	public static void main(String[] args) {
		Place p1 = new Place("P01");
		
		/*
		 *  Ajout d'écouteurs sur les properties de la place p1 
		 *  
		 */
		
		// Permet de tester l'état "invalidation" avant l'appel d'un getter
//		p1.libreProperty().addListener(new InvalidationListener() {
//
//			@Override
//			public void invalidated(Observable o) {
//
//				System.out.println("Place dans l'état invalidé ");
//			}
//		});

		// Ecouteur sur le changement d'état de la place
		p1.libreProperty().addListener((ev, oldV, newV) -> {
			System.out.println("La place est " + (newV == true ? " libre " : " occupée "));
		});
		/* ---------------------------------------------------------- */
		p1.setLibre(false);// indique que la place est occupée
		p1.setLibre(true);// indique que la place est libre

		// Exemple de liste observable
		ListProperty<Place> liste = new SimpleListProperty<>(FXCollections.<Place>observableArrayList());
		liste.addListener((ListChangeListener.Change<? extends Place> change) -> {
			while (change.next()) {
				// obtenir la liste des éléments retirés
				change.getRemoved().forEach((Place suppr) -> {
					System.out.println(" Place supprimée : " + suppr.getCode());
				});
				// obtenir la liste des éléments ajoutés
				change.getAddedSubList().forEach((Place ajout) -> {
					System.out.println(" Place ajoutée : " + ajout.getCode());
				});
			}
		});
		liste.add(p1);
		liste.add(new Place("P02"));
		liste.remove(1);
		liste.get(0).setLibre(false);

	}

}
