package poo.view;

import poo.modele.Segment;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import poo.controller.BoardGameController;
import poo.controller.GameController;
import poo.modele.Player;
import poo.modele.Position;
import poo.modele.Snake;

public class GameCanvas extends Canvas {
    private final BoardGameController<Integer> plateauController;
    private final GameController<Integer> gameController;
    private long lastUpdateTime = 0;
    private final long updateInterval = 1_000_000_000L / 10;  // Mettez à jour toutes les 10 frames par seconde
    public GameCanvas(BoardGameController<Integer> plateauController , GameController<Integer> gameController,double width , double height) {
      super(width, height);
 
        this.plateauController = plateauController;
        this.gameController = gameController;
        
        // Utilisez un AnimationTimer pour mettre à jour le rendu du jeu à intervalles réguliers
        new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                draw(); // Mettez à jour le rendu du jeu à chaque image
            }
        }.start();
    }

    private void draw() {
        // Obtenez le contexte graphique du Canvas
        GraphicsContext gc = getGraphicsContext2D();

        // Effacez le Canvas
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Dessinez le plateau en utilisant le plateauController
        drawBoard(gc);

        // Dessinez d'autres éléments du jeu (serpents, nourriture, etc.) en utilisant plateauController
        drawPlayers(gc);

        drawFood(gc);
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
        List<Player<Integer>> players = gameController.getJeu().getPlayers();
    
        for (Player<Integer> player : players) {
            Snake<Integer> snake = player.getSnake();
    
            // Définir la couleur du serpent pour chaque joueur
            // Vous pouvez utiliser une logique pour attribuer une couleur différente à chaque joueur
            gc.setFill(getSnakeColor(players.indexOf(player)));
    
            // Obtenir la taille de chaque cellule
            double cellSize = calculateCellSize();
    
            // Itérer sur tous les segments du serpent et dessiner chaque segment
            for (Segment segment : snake.getBody()) {
                Number xValue = segment.getPosition().getPositionX();
                Number yValue = segment.getPosition().getPositionY();
                double x, y;
                if (xValue instanceof Integer) {
                    x = (Integer) xValue * cellSize;
                } else if (xValue instanceof Double) {
                    x = ((Double) xValue).intValue() * cellSize;
                } else {
                    throw new IllegalStateException("La valeur de x n'est ni un Integer ni un Double.");
                }
    
                if (yValue instanceof Integer) {
                    y = (Integer) yValue * cellSize;
                } else if (yValue instanceof Double) {
                    y = ((Double) yValue).intValue() * cellSize;
                } else {
                    throw new IllegalStateException("La valeur de y n'est ni un Integer ni un Double.");
                }
    
                // Dessiner chaque segment du serpent
                gc.fillRect(y, x, cellSize, cellSize);
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

    // Dessinez chaque position de nourriture
    for (Position nourriture : nourritures) {
        double cellSize = calculateCellSize();
        double x = nourriture.getPositionX().doubleValue() * cellSize;
        double y = nourriture.getPositionY().doubleValue() * cellSize;

        // Dessinez la nourriture (par exemple, un cercle de couleur différente)
        gc.setFill(Color.RED);
        gc.fillOval(x, y, cellSize, cellSize);
        //System.out.println("la position de la nouristure "+ nourriture.getPositionX().doubleValue() + nourriture.getPositionY().doubleValue());
    }
}

private double calculateCellSize() {
        // Calculez la taille d'une cellule en fonction de la taille du Canvas et du plateau
        return Math.min(getWidth() / plateauController.getPlateauLongueur(), getHeight() / plateauController.getPlateauLargeur());
    }
}
