package poo;

import poo.controller.GameController;

import java.util.ArrayList;

import poo.controller.BoardGameController;
import poo.modele.Game;
import poo.modele.AiPlayer;
import poo.modele.BoardGame;
import poo.modele.HumanPlayer;
import poo.modele.Position;
import poo.view.BoardGameView;
import poo.modele.Segment;
import poo.modele.SegmentType;

public class Main {

    public static void main(String[] args) {
        BoardGame<Integer> plateau = new BoardGame<Integer>(5, 5);
        BoardGameView<Integer> plateauView = new BoardGameView<Integer>();
        BoardGameController<Integer> plateauController = new BoardGameController<Integer>(plateau, plateauView);

        Position<Integer> position = new Position<Integer>(3, 2);
        // Segment<Integer> segment = new
        // Segment<Integer>(position,SegmentType.SERPENT);
        // plateau.setCellType((int)segment.getPosition().getPositionX(),
        // (int)segment.getPosition().getPositionY(), SegmentType.SERPENT);

        Game jeu = new Game<>(new HumanPlayer<>("taous", 2, 3), plateau, 5); // 5 cest pour le nombre de food
        //jeu.getPlayer(0).getSnake().setBody(new ArrayList<Segment<Integer>>().add(new Segment<Integer>(position, SegmentType.SERPENT)).add(new Segment<Integer>(new Position<>(1, 2), SegmentType.SERPENT)));
      //  jeu.addPlayer(new HumanPlayer<>("Lyes", 3, 2));

        GameController gameController = new GameController<>(jeu);
        gameController.gameInit();

        plateauController.initPlateau(gameController.genererNourriture(plateauController.getPlateauLongueur(),plateauController.getPlateauLargeur()));
        // init plateau recoit la nourriture generé par le GameController

        // plateauController.setPlateauCellType((int)segment.getPosition().getPositionX(),
        // (int)segment.getPosition().getPositionY(), SegmentType.SERPENT);
        // pur tester l'affichage du segment

        plateauController.setPlateau(jeu.getPlateau());

        plateauController.UpdatePlateau();

        gameController.jouer(plateauController);
            
 

    }
}
