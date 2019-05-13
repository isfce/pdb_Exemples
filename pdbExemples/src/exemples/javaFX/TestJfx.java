package exemples.javaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestJfx extends Application {

	@Override
	public void start(Stage stage) {
		//Le conteneur
		BorderPane cp= new BorderPane();
		cp.setPadding(new Insets(5, 5, 5, 5));
		//Ajout de composants au conteneur
		cp.setTop(new Text("Top"));
		cp.setLeft(new Text("Left"));
		cp.setRight(new Text("Right"));
		cp.setCenter(new Text("Center"));
		cp.setBottom(new Text("Bottom"));
		//Centre tous les composants
		cp.getChildren().stream().forEach(n->BorderPane.setAlignment(n, Pos.CENTER));
		//Spécifie un style
		cp.setStyle("-fx-background-color:azure; -fx-border-color:skyblue ;-fx-border-width:3");
		//Création de la scene et ajout à la stage
		Scene scene= new Scene(cp,300,200);
		stage.setScene(scene);
		stage.setTitle("5 zones d'un BorderPane");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
