package poo;

import javafx.animation.AnimationTimer;

// import poo.controller.GameController;

// import java.util.ArrayList;

// import poo.controller.BoardGameController;
// import poo.modele.Game;
// import poo.modele.AiPlayer;
// import poo.modele.BoardGame;
// import poo.modele.HumanPlayer;
// import poo.modele.Position;
// import poo.view.BoardGameView;
// import poo.modele.Segment;
// import poo.modele.SegmentType;

// public class Main {

    // public static void main(String[] args) {
    //     BoardGame<Integer> plateau = new BoardGame<Integer>(5, 5);
    //     BoardGameView<Integer> plateauView = new BoardGameView<Integer>();
    //     BoardGameController<Integer> plateauController = new BoardGameController<Integer>(plateau, plateauView);

    //     Position<Integer> position = new Position<Integer>(3, 2);
    //     // Segment<Integer> segment = new
    //     // Segment<Integer>(position,SegmentType.SERPENT);
    //     // plateau.setCellType((int)segment.getPosition().getPositionX(),
    //     // (int)segment.getPosition().getPositionY(), SegmentType.SERPENT);

    //     Game jeu = new Game<>(new HumanPlayer<>("taous", 2, 3), plateau, 5); // 5 cest pour le nombre de food
    //     // jeu.getPlayer(0).getSnake().setBody(new ArrayList<Segment<Integer>>().add(new
    //     // Segment<Integer>(position, SegmentType.SERPENT)).add(new Segment<Integer>(new
    //     // Position<>(1, 2), SegmentType.SERPENT)));
    //     // jeu.addPlayer(new HumanPlayer<>("Lyes", 3, 2));

    //     GameController gameController = new GameController<>(jeu);
    //     //gameController.gameInit();

    //     plateauController.initPlateau(gameController.genererNourriture(plateauController.getPlateauLongueur(),
    //             plateauController.getPlateauLargeur()));
    //     // init plateau recoit la nourriture generé par le GameController

    //     // plateauController.setPlateauCellType((int)segment.getPosition().getPositionX(),
    //     // (int)segment.getPosition().getPositionY(), SegmentType.SERPENT);
    //     // pur tester l'affichage du segment

    //     plateauController.setPlateau(jeu.getPlateau());

    //     plateauController.UpdatePlateau(jeu.getPlayers());

    //     gameController.jouer(plateauController);}

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import poo.controller.BoardGameController;
import poo.controller.GameController;
import poo.modele.AiPlayer;
import poo.modele.BoardGame;
import poo.modele.Game;
import poo.modele.HumanPlayer;
import poo.view.BoardGameView;
import poo.view.GameCanvas;


public class Main extends Application {

    private BoardGameController<Integer> boardGameController;
    private GameController<Integer> gameController;

    @Override
    public void start(Stage primaryStage) {
        BoardGame boardGame =new BoardGame<>(10, 10);
        boardGameController = new BoardGameController<>(boardGame, new BoardGameView<>());
        Game jeu = new Game<>(new HumanPlayer<>("Player 2", 9, 2), boardGame, 3) ;
      
        //deuxieme joueur
        jeu.addPlayer(new HumanPlayer<>("Player 2", 6, 2));
        
        gameController = new GameController<>(jeu);

        boardGameController.initPlateau(gameController.genererNourriture( boardGameController .getPlateauLongueur(),boardGameController.getPlateauLargeur()));        
        GameCanvas gameCanvas = new GameCanvas(boardGameController, gameController, 1000, 1000);
        Scene scene = new Scene(new Group(gameCanvas));

        // Add key pressed event handler to the scene
        scene.setOnKeyPressed(this::handleKeyPress);

        // Configure the stage
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        
        primaryStage.show();
       
        new AnimationTimer() {
            private long lastUpdate = 0;
            private long updateInterval = 500_000_000L; // Mettez à jour toutes les 10 milliards de nanosecondes (1 seconde)

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= updateInterval) {
                    lastUpdate = now;
                    gameController.jouer(boardGameController);
                    // Le rendu du jeu sera mis à jour automatiquement grâce à AnimationTimer
                }
            }
        }.start();
        
        
    }

    public static void main(String[] args) {
        launch();
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();
        // Call the handleKeyPress method of your GameController
        gameController.handleKeyPressByPlayer(code);
    }
}

