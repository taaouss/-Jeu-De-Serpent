package poo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import poo.controller.BoardGameController;
import poo.controller.GameController;
import poo.view.GameCanvas;



/**
 * Classe principale pour l'interface graphique du jeu Snake.
 * 
 * <p>
 * Cette classe hérite de la classe Application de JavaFX et gère l'interface
 * graphique du jeu. Elle crée une fenêtre JavaFX avec un canvas pour afficher
 * le plateau de jeu et utilise un AnimationTimer pour mettre à jour le rendu du
 * jeu en continu.
 * </p>
 */
public class MainGui extends Application {

    private  static BoardGameController boardGameController;
    private  static GameController gameController;
     
    /**
     * Méthode principale pour lancer l'interface graphique du jeu Snake.
     * 
     * @param bc Le contrôleur du plateau de jeu.
     * @param gc Le contrôleur du jeu.
     */
   public static void main(BoardGameController<Integer> bc,GameController<Integer> gc){

        boardGameController =bc ;
        gameController = gc ;
        launch();
      }
      
    @Override
    public void start(Stage primaryStage) {

        GameCanvas gameCanvas = new GameCanvas(boardGameController, gameController, 1000, 1000);
        Scene scene = new Scene(new Group(gameCanvas));

        // Ajout d'un gestionnaire d'événements pour les touches clavier
        scene.setOnKeyPressed(this::handleKeyPress);

       // Configuration de la fenêtre
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        gameCanvas.draw();
        
        // Mise en place d'un AnimationTimer pour la mise à jour continue du rendu du jeu
        new AnimationTimer() {
            private long lastUpdate = 0;
            private long updateInterval = 300_000_000L; 

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= updateInterval || gameController.isUpdate()) {
                    
                     gameController.setUpdate(false);
                     lastUpdate = now;
                      
                      if (!gameController.getJeu().isFin()){
                      gameController.jouer(boardGameController);
                    }
                    gameCanvas.draw(); // Mise à jour du rendu du jeu
                     
                }
            }
        }.start();   
    }
    /**
     * Gestionnaire d'événements pour les touches clavier.
     * 
     * @param event L'événement de la touche clavier.
     */
    private void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();
        gameController.handleKeyPressByPlayer(code);
    }
}
