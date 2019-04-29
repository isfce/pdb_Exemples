package exemples.javaFX.thread;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;

public class exempleThreadNotification extends Application {

	@Override
	public void start(Stage scenePrincipale) {
		BorderPane cp = new BorderPane();
		Button bt1 = new Button("Download le fichier");
		bt1.setOnAction(e-> new TraitementChargement().start());
		cp.setTop(bt1);
		Scene scene = new Scene(cp, 300, 400);
		scenePrincipale.setTitle("Exemple TP01 ");
		scenePrincipale.setScene(scene);
		scenePrincipale.show();
	}

	private class TraitementChargement extends Thread {
		String info;

		@Override
		public void run() {
			// Simule une tâche de chargement
			try {
				Thread.sleep(3000);
				info = "Chargement terminé";
			} catch (InterruptedException e1) {
				info = "Erreur lors du Chargement";
			}

			Notifications notification = Notifications.create()
					.title("Chargement")
					.text(info)
					.graphic(null)
					.hideAfter(Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT)
					.onAction(e -> System.out.println(" Notification cliquée"));
			 notification.darkStyle();
			//Doit se faire dans le Thread de JavaFX
			
			Platform.runLater(()-> notification.show());

		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
