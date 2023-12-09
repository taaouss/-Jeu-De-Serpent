package poo;
import poo.controller.GameController;
import poo.controller.PlateauController;
import poo.modele.Game;
import poo.modele.Plateau;
import poo.modele.Player;
import poo.modele.Position;
import poo.view.PlateauView;
import poo.modele.Segment;
import poo.modele.SegmentType;

public class Main  {

    public static void main(String[] args) {
        Plateau<Integer> plateau = new Plateau<Integer>(5, 5);
        PlateauView <Integer>plateauView = new PlateauView<Integer>();
        PlateauController<Integer> plateauController = new PlateauController<Integer>(plateau,plateauView);
        
        Position <Integer>position= new Position<Integer>(3, 2);
        Segment<Integer> segment = new Segment<Integer>(position,SegmentType.SERPENT);
        
        Game jeu = new Game<>(new Player<>("taous"), plateau, 5); // 5 cest pour le nombre de food
        GameController gameController = new GameController<>(jeu);
         
        plateauController.initPlateau(gameController.genererNourriture(plateauController.getPlateauLongueur(), plateauController.getPlateauLargeur()));
        //init plateau recoit la nourriture generé par le GameController

        plateauController.setPlateauCellType((int)segment.getPosition().getPositionX(), (int)segment.getPosition().getPositionY(), SegmentType.SERPENT);
        //pur tester l'affichage du segment  

        plateauController.UpdatePlateau();
        gameController.jouer();
    

        
    }
}
