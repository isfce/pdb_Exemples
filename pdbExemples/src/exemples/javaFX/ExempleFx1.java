package exemples.javaFX;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ExempleFx1 extends Application {
 private Button bt1;
 private Button bt2;
 private void traitementBt1(ActionEvent e) {
	 System.out.println("Référence de méthode: "+((Button)e.getSource()).getText()); 
 }
 
	@Override
	public void start(Stage primaryStage) {
		BorderPane cp = new BorderPane();
		cp.setTop(new Label("Je suis au TOP !"));
		bt1=new Button("BT1");
		//Version classe anonyme
		bt1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				 System.out.println("Classe anonyme "+((Button)e.getSource()).getText()); 
				
			}
		});
		//action version Lambda
		//bt1.setOnAction((e)-> System.out.println("Lambda "+((Button)e.getSource()).getText()));
		//action par référence de méthode
		//bt1.setOnAction(this::traitementBt1);
		
		bt1.getText();
		bt1.textProperty().addListener(
				(bt,o,n)->{System.out.println("change "+o+" en "+n);}
				);
		
		
		bt2=new Button("Change Nom");
		bt2.setOnAction((e)->bt1.setText("Voilà"));
		cp.setLeft(bt1);
		cp.setRight(bt2);
	
		Scene sc = new Scene(cp, 400, 300);
		primaryStage.setScene(sc);
		primaryStage.setTitle("Exemple 1 de JavaFX");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
