package exemples.javaFX.thread;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ExempleThreadIHM extends Application {

	private Button bt;

	private Label lbl;

	@Override
	public void start(Stage scenePrincipale) throws Exception {
		Pane cp = new StackPane();
		lbl = new Label(" BIENVENUE AU COURS DE PDB");
		lbl.setVisible(false);

		bt = new Button(" Appuyer sur le bouton");

		bt.setOnAction(e -> {
			// Cache le bouton et affiche le label
			bt.setVisible(false);
			lbl.setVisible(true);
			new Thread(() -> {

				try {// Attente de 3 s
					Thread.sleep(3000);
				} catch (Exception e1) {
				}
				Platform.runLater(() -> {
					bt.setVisible(true);
					lbl.setVisible(false);
				});

			}).start();

		});

		cp.getChildren().add(lbl);
		cp.getChildren().add(bt);

		Scene scene = new Scene(cp, 300, 400);
		scenePrincipale.setTitle("Exemple TP01 ");
		scenePrincipale.setScene(scene);
		scenePrincipale.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
