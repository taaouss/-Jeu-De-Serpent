package poo;
import poo.controller.BoardGameController;
import poo.controller.GameController;
import poo.modele.Game;
import poo.view.BoardGameView;



/**
 * Classe principale du jeu Snake.
 * 
 * <p>
 * Cette classe contient la méthode principale (main) qui initialise le jeu en
 * récupérant les paramètres du joueur, crée les contrôleurs et démarre le jeu.
 * </p>
 */
public class Main {

     private  static BoardGameController boardGameController;
     private static GameController gameController;
     static Game jeu ;


    /**
     * Méthode principale pour lancer le jeu Snake.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        //on fait appelle a la methode pour prendre les paramettres de l'utilisateur et on crée le jeu
        jeu = GestionGame.getGameParamsFromUser() ;
        boardGameController = new BoardGameController<>(jeu.getPlateau(), new BoardGameView<>());
        gameController = new GameController<>(jeu);
        boardGameController.initPlateau(gameController.genererNourriture( jeu.getPlateau().getLargeur(),jeu.getPlateau().getLongueur()));        


      if(!jeu.isSurTerminal()) {
        MainGui.main(boardGameController, gameController);
      } 
      else 
      {
       //pas d'inteface graphique affichage sur terminal
        boardGameController.setPlateau(jeu.getPlateau());
        boardGameController.UpdatePlateau(jeu.getPlayers());
        gameController.jouer(boardGameController);
     
            //boucle du jeu
            while(!gameController.getJeu().isFin()){
                gameController.jouer(boardGameController);
            
            }
      }
     
       
    }


}

