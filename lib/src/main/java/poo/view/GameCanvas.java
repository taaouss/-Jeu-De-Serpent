package poo.view;

import poo.modele.Segment;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import poo.controller.BoardGameController;
import poo.controller.GameController;
import poo.modele.Player;
import poo.modele.Position;
import poo.modele.Snake;

public class GameCanvas extends Canvas {
    private final BoardGameController<Integer> plateauController;
    private final GameController<Integer> gameController;
    private boolean gameOver =false ;

      /**
     * Constructeur de la classe GameCanvas.
     *
     * @param plateauController Le contrôleur du plateau de jeu.
     * @param gameController    Le contrôleur du jeu.
     * @param width             La largeur du Canvas.
     * @param height            La hauteur du Canvas.
     */
    public GameCanvas(BoardGameController<Integer> plateauController , GameController<Integer> gameController,double width , double height) {
       super(width, height);
 
        this.plateauController = plateauController;
        this.gameController = gameController;
        
        new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
               if(!gameOver && !gameController.getJeu().isDeplacementFluide()) draw(); // Mettez à jour le rendu du jeu à chaque image
            }
        }.start();
    }
    

     /**
     * Dessine le contenu du Canvas en fonction de l'état actuel du jeu.
     */
    public void draw() {
        // Obtenez le contexte graphique du Canvas
     
        GraphicsContext gc = getGraphicsContext2D();

        // Effacez le Canvas
        gc.clearRect(0, 0, getWidth(), getHeight());
         
        if(gameController.getJeu().isFin()==true){
            
            gameOver=true;
           drawFinDuJeu();
        }
       
        else {
        
        drawBoard(gc);

        drawPlayers(gc);

        drawFood(gc);
        }
      
    }

 private void drawBoard(GraphicsContext gc) {
        // Obtenez les informations sur le plateau à partir du plateauController
        int longueur = plateauController.getPlateauLongueur();
        int largeur = plateauController.getPlateauLargeur();

        // Dessinez les cellules du plateau
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < largeur; j++) {
                double cellSize = calculateCellSize();
                double x = i * cellSize;
                double y = j * cellSize;

                // Dessinez une cellule vide (ou d'autres types de cellules selon votre logique)
                gc.setFill(Color.BLACK);
                gc.fillRect(x, y, cellSize, cellSize);
            }
        }
    }

    private void drawPlayers(GraphicsContext gc) {
        List<Player> players = gameController.getJeu().getPlayers();
    
        for (Player player : players) {
            Snake snake = player.getSnake();
    
            // Définir la couleur du serpent pour chaque joueur
            // Vous pouvez utiliser une logique pour attribuer une couleur différente à chaque joueur
            gc.setFill(getSnakeColor(players.indexOf(player)));
    
            // Obtenir la taille de chaque cellule
            double cellSize = calculateCellSize();
    
            List<Segment> corpsSerpent = snake.getBody();
            double margin = 0;

            // Itérer sur tous les segments du serpent et dessiner chaque segment
            for (Segment segment : corpsSerpent) { 
                double xValue = segment.getPosition().getPositionX().doubleValue();
                double yValue = (double) segment.getPosition().getPositionY().doubleValue();
                
                // Ajouter une marge entre les segments
    
                // Calculer les nouvelles positions en ajoutant une marge
               
                  double x = xValue * cellSize  ;
                  double y = yValue * cellSize ;
                
                
    
                // Dessiner chaque segment du serpent
                if (margin != 0) gc.fillRect(x, y, cellSize , cellSize);
                    else gc.fillRect(x, y, cellSize , cellSize);
                margin++;
                
            }
        }
    }
    
    // Méthode pour attribuer une couleur différente à chaque joueur
    private Color getSnakeColor(int playerNumber) {
        switch (playerNumber) {
            case 0:
                return Color.GREEN;
            case 1:
                return Color.BLUE;
        
            default:
                throw new IllegalStateException("Couleur non définie pour le joueur numéro " + playerNumber);
        }
    }
    

private void drawFood(GraphicsContext gc) {
    // Obtenez les positions de la nourriture depuis le GameController
    List<Position> nourritures = gameController.getJeu().getNourritures();
     double cellSize = calculateCellSize();

    // Dessinez chaque position de nourriture
    for (Position nourriture : nourritures) {
        double x = nourriture.getPositionX().doubleValue() * cellSize;
        double y = nourriture.getPositionY().doubleValue() * cellSize;

        // Dessinez la nourriture (par exemple, un cercle de couleur différente)
        gc.setFill(Color.RED);
        gc.fillOval(x, y, cellSize, cellSize);/*********************************************************************************************************** */
    }
    int lastIndx =nourritures.size()-1 ;
    double x = nourritures.get(lastIndx).getPositionX().doubleValue() * cellSize;
    double y = nourritures.get(lastIndx).getPositionY().doubleValue() * cellSize;
    gc.setFill(Color.BEIGE);
    gc.fillOval(x, y, cellSize, cellSize);
    int Indx =nourritures.size()-2 ;
    double x1 = nourritures.get(Indx).getPositionX().doubleValue() * cellSize;
    double y1 = nourritures.get(Indx).getPositionY().doubleValue() * cellSize;
    gc.setFill(Color.YELLOW);
    gc.fillOval(x1, y1, cellSize, cellSize);

}

  /**
   * Dessine la fin du jeu, affiche un message et un bouton "Quitter".
   */
  public void drawFinDuJeu() {
    GraphicsContext gc = getGraphicsContext2D();
    double canvasWidth = getWidth();
    double canvasHeight = getHeight();

    gc.setFill(Color.BLACK);
    gc.setFont(Font.font("Arial", FontWeight.BOLD, 70));

    String finDuJeuMessage = "Fin du jeu !";

    Text text = new Text(finDuJeuMessage);
    text.setFont(gc.getFont());
    double textWidth = text.getLayoutBounds().getWidth();
    double textHeight = text.getLayoutBounds().getHeight();

    double messageX = (canvasWidth - textWidth) / 2;
    double messageY = (canvasHeight - textHeight) / 2 + textHeight; // Ajustement pour aligner le texte au centre

    gc.fillText(finDuJeuMessage, messageX, messageY);

     Group buttonGroup = new Group();

     Button quitterButton = new Button("Quitter");
     quitterButton.setOnMousePressed(e -> System.exit(0));  
 
     buttonGroup.getChildren().addAll(quitterButton);
 
     Group root = (Group) getScene().getRoot();
     root.getChildren().add(buttonGroup);
 
     double buttonWidth = 100;
     double quitterButtonX = (canvasWidth - 2 * buttonWidth - 20) / 2;
     double buttonY = messageY + 50;  
 
     quitterButton.setLayoutX(quitterButtonX);
     quitterButton.setLayoutY(buttonY);
     

}

private double calculateCellSize() {
        // Calculez la taille d'une cellule en fonction de la taille du Canvas et du plateau
        return Math.min(getWidth() / plateauController.getPlateauLongueur(), getHeight() / plateauController.getPlateauLargeur());
    }

}
