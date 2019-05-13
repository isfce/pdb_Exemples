package exemples.javaFX;


import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Optional;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import exemples.javaFX.Article.Etape;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class ApplicationJavaFX extends Application {
	private Label lbl;
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private Button bt4;
	private Button bt5;
	private TextField ztEntier;

	@Override
	public void start(Stage scenePrincipale) throws Exception {
		BorderPane cp = new BorderPane();
		VBox lstBoutons = new VBox(5.0);
		lbl = new Label("  BIENVENUE AU COURS DE PDB  ");
		cp.setPadding(new Insets(5, 5, 5, 5));
		BorderPane.setAlignment(lbl, Pos.CENTER);
		lbl.setStyle("-fx-background-color: lightGreen;");
		cp.setTop(lbl);
		// BT1 création de Stage
		bt1 = new Button("Lancer");
		

		bt1.setOnAction(e -> {
			//Création d'une Fenêtre
			Stage s1 = new Stage();
			s1.initStyle(StageStyle.DECORATED);
			
			//bouton pour fermer la fenêtre
			Button close = new Button("Close");
			close.setOnAction(ev->s1.close());
			//création d'un conteneur
			Pane c=new StackPane();
			c.getChildren().add(close);// ajout du bouton
			//Création de la scene
			Scene scene=new Scene(c, 150, 100);
			//Transparence de la fenêtre
			//s1.setOpacity(0.9);
			//Initialise la "modalité" de la fenêtre
			s1.initModality(Modality.APPLICATION_MODAL);
			//Initialisation classique
			s1.initOwner(scenePrincipale);
			s1.setScene(scene);
			s1.setTitle("Stage de style: " + s1.getStyle()+" modality: "+s1.getModality() );
			//s1.show();
			s1.showAndWait();
			System.out.println("Suite du code après show ou showAndWait");

		});
		
		// BT2 test Dialog
		bt2 = new Button(" Test Dialog Information");
		
		bt2.setOnAction(e -> {
			Alert al1 = new Alert(AlertType.ERROR);
			al1.setHeaderText(" Il est l'heure de la pause !!!");
			al1.showAndWait();
		});
		// BT3 test Dialog
		bt3 = new Button(" Test Dialog Confirmation");
		
		bt3.setOnAction(e -> {
			Alert al1 = new Alert(AlertType.CONFIRMATION);
			al1.setHeaderText(" La pause est finie ?");
			al1.setContentText("Votre choix ");
			Optional<ButtonType> result = al1.showAndWait();
			
			result.ifPresent(b -> {
				Alert al2;
				// en fonction du bouton appuyé, initialise une alerte
				// différente
				if (b == ButtonType.OK)
					al2 = new Alert(AlertType.INFORMATION);
				else
					al2 = new Alert(AlertType.WARNING);

				al2.setHeaderText("Vous avez appuyé sur le bouton");
				al2.setContentText(b.getText());
				al2.showAndWait();
			});

		});
		// BT4
		bt4 = new Button("Test Choice");
		
		bt4.setOnAction(e -> {
			ChoiceDialog<Article.Etape> dlg = new ChoiceDialog<>(Etape.PLAT, Arrays.asList(Etape.values()));
			dlg.setHeaderText("Choisir une étape");
			Optional<Etape> result = dlg.showAndWait();
			result.ifPresent(et -> {
				Alert al2 = new Alert(AlertType.INFORMATION);
				al2.setHeaderText("Vous avez choisi");
				al2.setContentText(et.toString());
				al2.showAndWait();
			});

		});
		bt5 = new Button("Test Input");
		
		bt5.setOnAction(this::brol);
		
		
		
//		e-> {
//				Alert al2 = new Alert(AlertType.INFORMATION);
//				al2.setHeaderText("Voici le nom du bouton du clic");
//				al2.setContentText(((Button)e.getSource()).getText());
//				al2.showAndWait();
//			}
//		);
		
		
		
		
		
//		bt5.setOnAction(e->{
//			TextInputDialog dlg = new TextInputDialog ("VO");
//			dlg.setHeaderText (" Quel est le nom de votre Professeur ?");
//			dlg.setTitle (" Votre Professeur ");
//			dlg.setContentText (" Encodez :");
//			dlg.showAndWait().ifPresent (n-> System . out . println (n));			
//		});
		
		ztEntier=new TextField("0");
		ztEntier.textProperty().addListener( new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, 
					String oldValue, String newValue) {
				try {
					if(newValue.length()>0) Integer.parseInt(newValue);
				} catch (NumberFormatException e) {
					ztEntier.setText(oldValue);
				}
				
			}

			
		});
		TextField txt=new TextField("HEllo");
		txt.textProperty().bind(ztEntier.textProperty());
		
		lstBoutons.getChildren().addAll(bt1,bt2,bt3,bt4,bt5,ztEntier);
		cp.setLeft(lstBoutons);
		lstBoutons.setStyle("-fx-background-color: AQUA;");
		cp.setCenter(txt);
		// création d'une scène avec son conteneur parent
		Scene scene = new Scene(cp, 300, 400);
		scenePrincipale.setTitle("Exemple TP01 ");
		// Ajout de la scène à la Stage
		scenePrincipale.setScene(scene);
		// Affichage de la vue
		scenePrincipale.show();
	}

	public void brol(ActionEvent e) {
		Alert al2 = new Alert(AlertType.INFORMATION);
		al2.setHeaderText("Voici le nom du bouton du clic");
		al2.setContentText(((Button)e.getSource()).getText());
		al2.showAndWait();
	};
	
	public static void main(String[] args) {
		Application.launch(ApplicationJavaFX.class, args);

	}

}

/* Définition de classes juste pour l'exemple
 * Classe Article Article.Etape et Categorie
 * 
 * */
class Article{
	public enum Etape {
		ENTREE, PLAT, DESSERT, AUCUNE
	};

	private final String code;
	private String nom;
	private String description;
	private Integer calories;
	private Double prix;
	private Boolean dispo;
	private Etape etape;
	private Categorie cat;

	public Article(String code, String nom, String description, Integer calories, Double prix, Boolean dispo,
			Etape etape, Categorie cat) {
		super();
		this.code = code.trim();
		this.nom = nom;
		this.description = description;
		this.calories = calories;
		this.prix = prix;
		this.dispo = dispo;
		this.etape = etape;
		this.cat = cat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Boolean getDispo() {
		return dispo;
	}

	public void setDispo(Boolean dispo) {
		this.dispo = dispo;
	}

	public Etape getEtape() {
		return etape;
	}

	public void setEtape(Etape etape) {
		this.etape = etape;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	public String getCode() {
		return code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calories == null) ? 0 : calories.hashCode());
		result = prime * result + ((cat == null) ? 0 : cat.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dispo == null) ? 0 : dispo.hashCode());
		result = prime * result + ((etape == null) ? 0 : etape.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prix == null) ? 0 : prix.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (calories == null) {
			if (other.calories != null)
				return false;
		} else if (!calories.equals(other.calories))
			return false;
		if (cat == null) {
			if (other.cat != null)
				return false;
		} else if (!cat.equals(other.cat))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dispo == null) {
			if (other.dispo != null)
				return false;
		} else if (!dispo.equals(other.dispo))
			return false;
		if (etape != other.etape)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prix == null) {
			if (other.prix != null)
				return false;
		} else if (!prix.equals(other.prix))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [code=" + code + ", nom=" + nom + ", prix=" + prix + "]";
	}
}
class Categorie {

	private final String code;
	private final String nom;
	private Categorie pereCat;

	public Categorie(String code, String nom, Categorie pereCat) {
		super();
		this.code = code;
		this.nom = nom;
		this.pereCat = pereCat;
	}

	public Categorie(String code, String nom) {
		//Appel de l'autre constructeur
		this(code, nom, null);
	}

	public String getCode() {
		return code;
	}

	public String getNom() {
		return nom;
	}

	public Categorie getPereCat() {
		return pereCat;
	}
	
	public void setPereCat(Categorie pereCat) {
		this.pereCat = pereCat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categorie [code=" + code + ", nom=" + nom + "]";
	}

}
