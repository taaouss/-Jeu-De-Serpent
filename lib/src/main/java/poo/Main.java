package poo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int CELL_SIZE = 10;
    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 20;
    
    @Override
    public void start(Stage primaryStage) {
        
        Plateau plateau = new Plateau();
        Scene scene = new Scene(new BorderPane(plateau), GRID_WIDTH * CELL_SIZE, GRID_HEIGHT * CELL_SIZE);
         primaryStage.setScene(scene);
        //Label label = new Label("Hello, JavaFX!");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lyes");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
