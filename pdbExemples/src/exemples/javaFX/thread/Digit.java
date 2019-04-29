package exemples.javaFX.thread;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Digit extends Group implements IDigit {

	private static final double distBord = 0.1;
	private static final Color coulFond_Defaut = Color.rgb(0xe0, 0xf0, 0xff);
	private static final Color coulOn_Defaut = Color.rgb(0, 0, 0x80);
	private static final Color coulOff_Defaut = Color.rgb(0xe0, 0xff, 0xd0);

	private final Color coulFond;
	private final Color coulLigneOn;
	private final Color coulLigneOff;
	// variable pour les segments
	final int e1 = 1;

	final int e2 = 5;
	final int e3 = 3;

	// Points des segments
	private Point2D[] p = new Point2D[7];
	

	// code pour indiquer les segments à allumer ou éteindre
	private byte code = 0b01111110;
	// L'objet graphique
	private GraphicsContext g2;
	// Le car à afficher
	private char car = '0';

	/**
	 * Constructeur à défaut
	 */
	public Digit() {
		this(coulFond_Defaut, coulOn_Defaut, coulOff_Defaut);

	}

	/**
	 * Constructeur avec possibilité de spécifier les couleurs
	 * 
	 * @param coulFond
	 * @param coulLigneOn
	 * @param coulLigneOff
	 */
	private Digit(Color coulFond, Color coulLigneOn, Color coulLigneOff) {
		super();
		this.coulFond = coulFond;
		this.coulLigneOn = coulLigneOn;
		this.coulLigneOff = coulLigneOff;
		Canvas canvas = new Canvas(40, 80);
		g2 = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		// Donne une couleur de fond au conteneur
		// this.setStyle("-fx-background-color: "+"#"+coulFond.toString().substring(4)+" ;");
		setCar(' ');

	}

	/**
	 * Ajuste le code en fonction du chiffre (car) à afficher
	 */
	@Override
	public void setCar(char car) {
		if (this.car == car)
			return;
		this.car = car;

		switch (car) {
		case ' ':
			code = (byte) 0b00000000;
			break;
		case '0':
			code = (byte) 0b01111110;
			break;
		case '1':
			code = (byte) 0b00110000;
			break;
		case '2':
			code = (byte) 0b01101101;
			break;
		case '3':
			code = (byte) 0b01111001;
			break;
		case '4':
			code = (byte) 0b00110011;
			break;
		case '5':
			code = (byte) 0b01011011;
			break;
		case '6':
			code = (byte) 0b00011111;
			break;
		case '7':
			code = (byte) 0b01110000;
			break;
		case '8':
			code = (byte) 0b01111111;
			break;
		case '9':
			code = (byte) 0b01110011;
			break;
		case 'A':
			code = (byte) 0b01110111;
			break;
		case 'F':
			code = (byte) 0b01000111;
			break;
		default:
			break;
		}

		dessine();
	}

	/**
	 * Dessine un segment entre 2 points avec une finition pointue param
	 * e1,e2,e3
	 * 
	 * @param pa
	 * @param pb
	 */
	private void segment(Point2D pa, Point2D pb) {

		double x, y;

		g2.beginPath();
		if (Math.abs(pb.getY() - pa.getY()) < 2) {
			if (pa.getX() > pb.getX()) {
				Point2D tmp = pa;
				pa = pb;
				pb = tmp;
			}
			x = pa.getX();
			y = pa.getY();

			x = pa.getX() + e1;
			g2.moveTo(x, y);
			x += e2;
			y -= e3;
			g2.lineTo(x, y);

			x = pb.getX() - e1 - e2;
			g2.lineTo(x, y);
			x += e2;
			y += e3;
			g2.lineTo(x, y);
			x -= e2;
			y += e3;
			g2.lineTo(x, y);
			x = pa.getX() + e1 + e2;
			g2.lineTo(x, y);

		} else {
			if (pa.getY() > pb.getY()) {
				Point2D tmp = pa;
				pa = pb;
				pb = tmp;
			}
			x = pa.getX();
			y = pa.getY();
			y = pa.getY() + e1;
			g2.moveTo(x, y);

			y += e2;
			x -= e3;
			g2.lineTo(x, y);
			y = pb.getY() - e1 - e2;
			g2.lineTo(x, y);
			x += e3;
			y += e2;
			g2.lineTo(x, y);
			x += e3;
			y -= e2;
			g2.lineTo(x, y);
			y = pa.getY() + e1 + e2;
			g2.lineTo(x, y);

		}

		g2.closePath();
		g2.fill();
		g2.stroke();
	}

	/**
	 * 
	 * Dessin du digit
	 */

	protected void dessine() {
		g2.setLineWidth(1);
		// bordure du digit
		Bounds b = g2.getCanvas().getBoundsInLocal();
		g2.setFill(coulFond);
		g2.fillRect(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
		double w = b.getWidth();
		double h = b.getHeight();
		// définit une marge extérieure et une intérieure
		double bord = (w * distBord);// bordure
		double pad = (bord * 0.5);// padding= marge intérieure
		double ecart = pad + bord;
		double distM = ((h - ecart - ecart) / 2.0);

		// calcul des 6 points aux extrémités des segments
		p[0] = new Point2D(ecart, ecart);
		p[1] = new Point2D(w - ecart, ecart);
		p[2] = new Point2D(w - ecart, distM + ecart);
		p[3] = new Point2D(w - ecart, h - ecart);
		p[4] = new Point2D(ecart, h - ecart);
		p[5] = new Point2D(ecart, distM + ecart);
		p[6] = p[0];

		// Dessine les 7 segments: parcourt les points et dessine entre les
		// segments
		byte masque = 0b01000000;
		for (int i = 0; i < 6; i++) {
			// allume ou non un segment en fonction du code
			g2.setStroke((this.code & masque) != 0 ? coulLigneOn : coulLigneOff);
			g2.setFill((this.code & masque) != 0 ? coulLigneOn : coulLigneOff);
			// dessine le segment
			segment(p[i], p[i + 1]);
			// avance dans le masque pour passer au segment suivant
			masque = (byte) (masque >>> 1);
		}

		// reste le segment du milieu
		g2.setStroke((this.code & masque) != 0 ? coulLigneOn : coulLigneOff);
		g2.setFill((this.code & masque) != 0 ? coulLigneOn : coulLigneOff);
		segment(p[5], p[2]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ihm.IDigit#setCar(int)
	 */
	@Override
	public void setCar(int car) {

		setCar(Integer.toString(car).charAt(0));

	}
}
