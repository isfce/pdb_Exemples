package exemples.javaFX.properties;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

class Ball extends Circle {
	private double dragBaseX;
	private double dragBaseY;

	public Ball(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius);

		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// Lors du click maintient l'écart entre la position du click et le centre du
				// cercle
				dragBaseX = event.getX() - getCenterX();
				dragBaseY = event.getY() - getCenterY();
			}
		});

		setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setCenterX(event.getX() - dragBaseX);
				setCenterY(event.getY() - dragBaseY);
			}
		});
	}
}

class Connection extends Line {
	public Connection(Ball startBall, Ball endBall) {
		startXProperty().bind(startBall.centerXProperty());
		startYProperty().bind(startBall.centerYProperty());
		endXProperty().bind(endBall.centerXProperty());
		endYProperty().bind(endBall.centerYProperty());
	}
}

public class ConnectedBall extends Application {
	private TextField ztX1;
	private TextField ztY1;
	private Ball ball1;
	private Ball ball2;

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane cp = new BorderPane();
		
		//Panneau Graphique
		Group graphe = new Group();
		//prépare les balles
		initShapes(graphe);
		cp.setCenter(graphe);
		//Ajoute un écouteur 
		ball1.centerXProperty().addListener((obj, o, n) -> {
			ztX1.setText(n.toString());
		});
		ball1.centerYProperty().addListener((obj, o, n) -> {
			ztY1.setText(n.toString());
		});
		
		//Panneau d'affichage des données
		FlowPane box = new FlowPane();
		Label lbl1 = new Label("X1");lbl1.setTextAlignment(TextAlignment.RIGHT);lbl1.setStyle("-fx-background-color: bisque;");
		Label lbl2 = new Label("Y1");lbl2.setTextAlignment(TextAlignment.RIGHT);lbl2.setStyle("-fx-background-color: bisque;");
		ztX1 = new TextField(Double.toString(ball1.getCenterX()));
		ztX1.setEditable(false);
		ztY1 = new TextField(Double.toString(ball1.getCenterY()));
		ztY1.setEditable(false);;
		
		//Ajout des nodes au panel
		box.getChildren().addAll(lbl1, ztX1,lbl2,ztY1);
		//Positionne le panneau d'affichage en haut
		cp.setTop(box);

		Scene scene = new Scene(cp, 400, 400);
		stage.setScene(scene);
		stage.show();
	}

	private void initShapes(Group root) {
		//rectangle noir en fond
		Rectangle r = new Rectangle(1, 1, 400, 400);
		ball1 = new Ball(100, 200, 20);
		ball1.setFill(Color.RED);

		ball2 = new Ball(300, 200, 20);
		ball2.setFill(Color.GOLD);
		
		Connection connection = new Connection(ball1, ball2);
		connection.setStroke(Color.CYAN);
		connection.setStrokeWidth(5);
		root.getChildren().addAll(r, connection, ball1, ball2);
	}

	public static void main(String... args) {
		launch(args);
	}
}