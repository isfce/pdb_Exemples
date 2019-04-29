package exemples.javaFX.thread;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//import com.sun.swing.internal.plaf.synth.resources.synth;

public class VueCompteur extends Application {

	// MonAction
	class MonAction implements EventHandler<javafx.event.ActionEvent> {

		private volatile boolean stop; // permet d'interrompre le processus
		private volatile boolean start;// évite de lancer 2 fois le processus
		private volatile boolean pause;

		@Override
		public void handle(javafx.event.ActionEvent event) {

			System.err.println(Platform.isFxApplicationThread());
			if (start)
				return;
			start = true;
			stop = false;
			pause = false;
			// **************************************************
			// Version 1
			// int cpt=0;
			// while (cpt < 100 && !stop) {
			//
			// digit2.setCar(cpt % 10);
			// digit1.setCar(cpt / 10);
			//
			//
			// try {
			// Thread.sleep(30);
			// } catch (InterruptedException e) {
			//
			// }
			// cpt++;
			// }
			// start = false;
			// ******************************************************
			// ******************************************************
			// **Version 2

			// Lance la tâche dans un autre thread
			// new Thread(new Runnable() {
			// int cpt = 0;
			//
			// @Override
			// public void run() {
			// System.err.println(SwingUtilities.isEventDispatchThread());
			// while (cpt < 100 && !stop) {
			// // Lance le traitement dans l'Event Dispatch Thread
			//
			// digit2.setCar(cpt % 10);
			// digit1.setCar(cpt / 10);
			//
			// try {
			// Thread.sleep(120);
			// } catch (InterruptedException e) {
			//
			// }
			// cpt++;
			// }
			// start = false;
			// }
			// }).start();
			//
			// //******************************************************
			// //**Version 3
			//
			// // Lance la tâche dans un autre thread
			//System.err.println("Lance tâche");
			new Thread(new Runnable() {
				int cpt = 0;

				@Override
				public void run() {

					while (cpt < 100 && !stop) {
						// Lance le traitement dans l'Event Dispatch Thread
						Platform.runLater(new Runnable() {
							public void run() {
								digit2.setCar(cpt % 10);
								digit1.setCar(cpt / 10);
								//System.err.println("Avance "+cpt);
							}
						});
						;

						try {
							do {

								Thread.sleep(100);

							} while (pause && !stop);
						} catch (InterruptedException e) {

						}

						cpt++;

					}
					start = false;
				}
			}).start();

		}

		public boolean isStop() {
			return stop;
		}

		public void stop() {
			this.stop = true;
		}

		public void start() {
			if (start)
				return;

		}

		public void pause() {
			if (this.pause) {
				this.pause = false;
			} else {
				this.pause = true;
			}
		}

		public boolean isPause() {
			return pause;
		}

	}

	Digit digit1, digit2;
	private Button btStart;
	private Button btStop;
	private Button btPause;
	private MonAction action;

	private Pane initGui() {
		BorderPane cp = new BorderPane();
        //Canvas canvas=new Canvas();
		HBox panneauCentre = new HBox();
		digit1 = new Digit();

		digit2 = new Digit();

		panneauCentre.getChildren().addAll(digit1,digit2);
		cp.setCenter(panneauCentre);

		HBox panneauSud = new HBox();

		btStart = new Button("Start");
		// création de l'action
		action = new MonAction();
		btStart.setOnAction(action);

		btPause = new Button("Pause");
		// pause de l'action
		btPause.setOnAction((e) -> action.pause());

		btStop = new Button("Stop");

		// Arrêt de l'action
		btStop.setOnAction((e) -> action.stop());

		panneauSud.getChildren().addAll(btStart, btPause, btStop);
		cp.setBottom(panneauSud);
		cp.setTop(new TextField());
		return cp;
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Gestion des Threads dans l'IHM");
		Pane cp = initGui();
		Scene scene = new Scene(cp, 300, 300);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		launch(VueCompteur.class, args);
	}
}
