package poo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int CELL_SIZE = 10;
    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 20;

    @Override
    public void start(Stage primaryStage) {

        // Utilisation d'un BorderPane comme conteneur principal
        BorderPane root = new BorderPane();

        // Création d'un Plateau et ajout au centre du BorderPane
        Plateau plateau = new Plateau();
        
        root.setCenter(plateau);

        // Création d'une scène avec le BorderPane
       Scene scene = new Scene(new BorderPane(plateau), GRID_WIDTH * CELL_SIZE, GRID_HEIGHT * CELL_SIZE);
        // Création d'un joueur avec un Segment
        Segment playerSegment = new Segment(new Position<>(0, 0));
        
        // Ajout du Segment à la liste des enfants de la scène
        root.getChildren().add(playerSegment.getView());

        // Configuration de la scène et affichage de la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lyes");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
