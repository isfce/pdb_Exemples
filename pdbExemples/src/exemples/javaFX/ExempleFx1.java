package exemples.javaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExempleFx1 extends Application implements EventHandler<ActionEvent> {
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private Button bt4;
	private Button bt5;
	private Button btEfface;

	private TextField zt1;

	private TextArea sortieTxt;

	// Affiche un texte dans une TextArea
	private void affiche(String txt) {
		sortieTxt.appendText("\n----------------\n");
		sortieTxt.appendText(txt);
	}

	// Traitement par référence de méthode
	private void traitementBt3(ActionEvent e) {
		affiche("Référence de méthode: Bouton (" + ((Button) e.getSource()).getText() + ")");
	}

	// Traitement centralisé associée à plusieurs boutons
	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == bt1) {
			affiche("Clic sur le bouton bt1: Traitement centralisé");
		} else if (event.getSource() == bt2) {
			affiche("Clic sur le bouton bt2: Traitement centralisé");
		} else if (event.getSource() == bt3) {
			affiche("Clic sur le bouton bt3: Traitement centralisé");
		} else if (event.getSource() == bt4) {
			affiche("Clic sur le bouton bt4: Traitement centralisé");
		} else if (event.getSource() == bt5) {
			affiche("Clic sur le bouton bt5: Traitement centralisé");
		}
	}

// Demarrage de la vue principale
	@Override
	public void start(Stage primaryStage) {
		BorderPane cp = new BorderPane();
		// Zone de sortie dans un ScrollPane
		sortieTxt = new TextArea();
		sortieTxt.setEditable(false); // empêche l'édition
		ScrollPane sp = new ScrollPane(sortieTxt);
		// indique que le TextArea prenne toute la place de son conteneur (le
		// scrollPane)
		sp.setFitToHeight(true);
		sp.setFitToWidth(true);
		cp.setCenter(sp);
		VBox vb = new VBox();

		// Version classe anonyme
		bt1 = new Button("BT1 appel a une classe anonyme");

		bt1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				affiche("Classe anonyme " + ((Button) e.getSource()).getText());

			}
		});

		// action version Lambda
		bt2 = new Button("BT2 à une fonction Lambda");
		bt2.setOnAction((e) -> affiche("Lambda : button : (" + ((Button) e.getSource()).getText() + ")"));

		// action par référence de méthode
		bt3 = new Button("BT3 appel à une référence de Méthode");
		bt3.setOnAction(this::traitementBt3);

		// L'action sera implémentée par cette classe elle-même (l'objet: this)
		bt4 = new Button("BT4 action centralisée sur this");
		bt5 = new Button("BT5 action centralisée sur this");
		bt4.setOnAction(this);
		bt5.setOnAction(this);

		// Action sur une touche
		zt1 = new TextField();
		zt1.setOnKeyTyped(e -> affiche("Touche: " + e.getCharacter()));
		zt1.setOnKeyPressed(e -> affiche("Touche pressée: " + e.getText() + " code: " + e.getCode()));
		zt1.setOnKeyReleased(e -> affiche("Touche relachée: " + e.getText() + " code: " + e.getCode()));

		// Ajout des nodes au conteneur vertical
		vb.getChildren().addAll(bt1, bt2, bt3, bt4, bt5, zt1);

		// Ajoute la liste des boutons à gauche
		cp.setLeft(vb);

		// Efface la sortie
		btEfface = new Button("Efface");
		btEfface.setOnAction(e -> sortieTxt.clear());
		cp.setRight(btEfface);

		Scene sc = new Scene(cp, 800, 300);
		primaryStage.setScene(sc);
		primaryStage.setTitle("Exemples avec les différentes façons de définir des actions en JavaFX");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
