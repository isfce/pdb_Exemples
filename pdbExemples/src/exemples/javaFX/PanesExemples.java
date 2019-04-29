package exemples.javaFX;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PanesExemples extends Application {
	List<Pane> panes = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();

			Scene scene = new Scene(root, 800, 400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Liste des différents Panes
			panes.add(getBorderPane());
			panes.add(getHPane());
			panes.add(getVPane());
			panes.add(getFlowPane());
			panes.add(getStackPane());
			panes.add(getAnchorPane());
			panes.add(getGridPane());
			panes.add(getTilePane());

			// Création d'une VPane pour les boutons
			VBox p = new VBox();
			p.setPadding(new Insets(5, 5, 5, 5));
			p.setSpacing(10);
			p.setAlignment(Pos.CENTER);
			p.setStyle("-fx-background-color: #336699;");
			ToggleGroup tg = new ToggleGroup();
			ToggleButton b1 = new ToggleButton("BorderPane");
			b1.setToggleGroup(tg);
			b1.setOnAction(e -> root.setCenter(panes.get(0)));
			ToggleButton b2 = new ToggleButton("HPane");
			b2.setToggleGroup(tg);
			b2.setOnAction(e -> root.setCenter(panes.get(1)));
			ToggleButton b3 = new ToggleButton("VPane");
			b3.setToggleGroup(tg);
			b3.setOnAction(e -> root.setCenter(panes.get(2)));
			ToggleButton b4 = new ToggleButton("FlowPane");
			b4.setToggleGroup(tg);
			b4.setOnAction(e -> root.setCenter(panes.get(3)));
			ToggleButton b5 = new ToggleButton("StackPane");
			b5.setToggleGroup(tg);
			b5.setOnAction(e -> root.setCenter(panes.get(4)));
			ToggleButton b6 = new ToggleButton("AnchorPane");
			b6.setToggleGroup(tg);
			b6.setOnAction(e -> root.setCenter(panes.get(5)));
			ToggleButton b7 = new ToggleButton("GridPane");
			b7.setToggleGroup(tg);
			b7.setOnAction(e -> root.setCenter(panes.get(6)));
			ToggleButton b8 = new ToggleButton("TilePane");
			b8.setToggleGroup(tg);
			b8.setOnAction(e -> root.setCenter(panes.get(7)));

			tg.selectToggle(b1);
			root.setCenter(panes.get(0));
			p.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8);
			root.setLeft(p);
			// Fixe des panes en top, right botton
			root.setTop(getHPane());
			root.setRight(getVPane());
			root.setBottom(getFlowPane());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BorderPane getBorderPane() {
		BorderPane p = new BorderPane();
		p.setId("monBorderPane");//Id pour référence css
		p.setPadding(new Insets(5, 5, 5, 5));
		Node top = new Text("Top");
		p.setTop(top);
		p.setLeft(new Text("Left"));
		p.setRight(new Text("Right"));
		p.setCenter(new Button("Center"));
		p.setBottom(new Text("Bottom"));
		p.getChildren().stream().forEach(n -> BorderPane.setAlignment(n, Pos.CENTER));
		//p.setStyle("-fx-background-color:azure; -fx-border-color:skyblue ;-fx-border-width:3");
		return p;
	}

	public HBox getHPane() {
		HBox p = new HBox();
		p.setPadding(new Insets(5, 5, 5, 5));
		p.setSpacing(10);
		p.setAlignment(Pos.BOTTOM_CENTER);
		p.setStyle("-fx-background-color: lightseagreen;");
		Button b1 = new Button("Bouton1");
		Button b2 = new Button("Bouton2");
		p.getChildren().addAll(b1, b2);
		return p;
	}

	public VBox getVPane() {
		VBox p = new VBox();// création du conteneur horizontal
		p.setPadding(new Insets(5, 5, 5, 5));// Ecart entre les bordures
		p.setSpacing(10);// Ecart entre les composants
		p.setAlignment(Pos.TOP_LEFT);// alignement Haut en Vertical et Gauche en
										// Horizontal
		p.setStyle("-fx-background-color:lavender"); // couleur du fond
		Text titre = new Text("Liens"); // Un texte
		titre.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		p.getChildren().add(titre); // Ajout du texte
		Hyperlink l1 = new Hyperlink("Layout"); // Création d'un lien hypertexte
		p.getChildren().add(l1); // Ajout au lien
		VBox.setMargin(l1, new Insets(0, 0, 0, 10));// marge du lien pour un
													// décalage
		Hyperlink l2 = new Hyperlink("CSS styles");// idem pour un 2ème lien
		p.getChildren().add(l2);
		VBox.setMargin(l2, new Insets(0, 0, 0, 10));
		return p;
	}

	public StackPane getStackPane() {
		StackPane p;
		p = new StackPane();
		Ellipse e1 = new Ellipse(150, 40); // une ellipse
		e1.setFill(Color.CORNFLOWERBLUE); // couleur de fond
		Rectangle r2 = new Rectangle(80, 300);// une rectangle
		r2.setFill(Color.color(0.4, 1.0, 0.8, 0.5));// avec une couleur avec
													// transparence RGB+Alpha
		Text txt = new Text("ISFCE");// un texte
		txt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		txt.setFill(Color.WHITE);// couleur de remplissage
		txt.setStroke(Color.FUCHSIA);// couleur de bordure
		p.getChildren().addAll(e1, r2, txt); // ajout au stack

		return p;
	}

	public GridPane getGridPane() {
		GridPane p = new GridPane();
		p.setPadding(new Insets(5.0));
		p.setHgap(10);
		p.setVgap(10);
		TextField t = new TextField();
		t.setStyle("-fx-background-color:azure; -fx-border-color:skyblue ; -fx-border-width:3");
		p.add(t, 0, 0, 3, 1); // 1ère ligne et colonne, étalé sur 3 cellules en
								// colonne et une en ligne
		// création de 10 boutons
		Button[] boutons = new Button[10];
		for (int i = 0; i < boutons.length; i++) {
			boutons[i] = new Button(Integer.toString(i));// crée le bouton i
			p.add(boutons[i], i % 3, 1 + i / 3);// positionne le bonton i
		}
		Button enter = new Button("ENTER");// ajout du bouton ENTER
		enter.setPrefWidth(55);
		p.add(enter, 1, 4, 2, 1);// prend 2 cellules en colonne et une en ligne
		p.setBorder(new Border(new BorderStroke(Color.GOLD, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));
		// p.setGridLinesVisible(true); //permet d'afficher les lignes de
		// séparation
		return p;
	}

	public FlowPane getFlowPane() {
		FlowPane p = new FlowPane();
		p.setPadding(new Insets(5, 5, 5, 5));
		p.setVgap(5);
		p.setHgap(5);
		p.setPrefWrapLength(85); // Largeur préférée pour 2 colonnes
		p.setStyle("-fx-background-color: skyblue;");
		// liste d'images img0x.png dans /images/...
		ImageView pages[] = new ImageView[8];
		for (int i = 0; i < 8; i++) {
			pages[i] = new ImageView(new Image(PanesExemples.class.getResourceAsStream("Images/img0" + i + ".png")));
			p.getChildren().add(pages[i]);
		}
		p.getChildren().add(new Text("FX==ISFCE==FX"));

		return p;
	}

	public TilePane getTilePane() {
		TilePane p = new TilePane();
		p.setPadding(new Insets(5, 5, 5, 5));
		p.setVgap(5);
		p.setHgap(5);
		p.setPrefColumns(2);// nb de tuiles désirées sur une ligne
		// p.setPrefRows(2); // nb de tuiles désirées sur une colonne
		p.setStyle("-fx-background-color: skyblue;");
		// liste d'images img0x.png dans /images/...
		ImageView pages[] = new ImageView[8];
		for (int i = 0; i < 8; i++) {
			pages[i] = new ImageView(new Image(PanesExemples.class.getResourceAsStream("Images/img0" + i + ".png")));
			p.getChildren().add(pages[i]);
		}
		p.getChildren().add(new Text("FX==ISFCE==FX"));
		return p;
	}

	public AnchorPane getAnchorPane() {
		AnchorPane p = new AnchorPane();
		p.setPrefHeight(80);
		p.setPrefWidth(200);
		p.setStyle("-fx-background-color: skyblue;");
		Text t1 = new Text(" Haut_Gauche");
		Text t2 = new Text(" Bas_Droit");
		Text t3 = new Text(" Haut_Droit ");
		Text t4 = new Text(" Bas Gauche");
		Button b5 = new Button(" CENTRE ");
		p.getChildren().addAll(t1, t2, t3, t4, b5); // ajout au conteneur
		// Les 4 textes avec 2 ancrages
		AnchorPane.setTopAnchor(t1, 2.0);
		AnchorPane.setLeftAnchor(t1, 2.0);
		AnchorPane.setBottomAnchor(t2, 2.0);
		AnchorPane.setRightAnchor(t2, 2.0);
		AnchorPane.setRightAnchor(t3, 2.0);
		AnchorPane.setTopAnchor(t3, 2.0);
		AnchorPane.setLeftAnchor(t4, 2.0);
		AnchorPane.setBottomAnchor(t4, 2.0);
		// Le bouton avec 4 ancrages
		AnchorPane.setRightAnchor(b5, 50.0);
		AnchorPane.setTopAnchor(b5, 20.0);
		AnchorPane.setLeftAnchor(b5, 50.0);
		AnchorPane.setBottomAnchor(b5, 20.0);
		return p;
	}

	public static void main(String[] args) {
		Application.launch(PanesExemples.class, args);
	}
}
