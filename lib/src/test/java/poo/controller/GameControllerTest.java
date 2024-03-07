package poo.controller;
import static org.junit.Assert.*;
import org.junit.Test;
import poo.modele.*;
import poo.view.BoardGameView;
import java.util.List;


public class GameControllerTest {

    @Test
    public void testGenerateFood() {
        // Créer une instance de GameController avec un plateau et un joueur factices
        Player<Double> player1 =new HumanPlayer<>("joueur_1", 3.0, 2.0);
        Game<Double> game = Game.getInstance(player1, new BoardGame<>(10, 10), 5, false, true, false, false, false);
        GameController<Double> gameController = new GameController<>(game);
        // Générer de la nourriture
        List<Position<Double>> food = gameController.genererNourriture(10, 10);

        // Vérifier que le nombre de positions de nourriture générées correspond au nombre spécifié dans le jeu
        assertEquals(game.getNombreNourriture(), food.size());

        // Vérifier que les positions de nourriture générées ne se chevauchent pas
        for (int i = 0; i < food.size(); i++) {
            for (int j = i + 1; j < food.size(); j++) {
                assertNotEquals(food.get(i), food.get(j));
            }
        }
    }

         @Test
    public void testGameOver() {
        // Créer une instance de GameController
         Game<Double> game = Game.getInstance(new HumanPlayer<Double>("joueur_1", 0.0, 0.0), new BoardGame<>(5, 5), 5, false, true, false, false, false);
        GameController<Double> gameController = new GameController<>(game);

        // Appeler la méthode jouer plusieurs fois (ou déplacer le serpent jusqu'à la collision avec mur)
        // pour simuler une condition de "Game Over"
        BoardGameController boardGameController = new BoardGameController<>(game.getPlateau(),new BoardGameView<>());
        for (int i = 0; i < 6; i++) {
            gameController.jouer(boardGameController); 
        }

        assertTrue(game.isFin());

        // Vérifier que le statut de Game Over du joueur a été correctement mis à true
        assertTrue(game.getPlayer(0).isGameOver());
    }

    


}
