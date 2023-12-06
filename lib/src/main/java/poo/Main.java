package poo;
import poo.controller.PlateauController;
import poo.modele.Plateau;
import poo.modele.Position;
import poo.view.PlateauView;
import poo.modele.Segment;

public class Main  {

    public static void main(String[] args) {
        Plateau plateau = new Plateau(5, 5);
        PlateauView plateauView = new PlateauView();
        PlateauController plateauController = new PlateauController(plateau,plateauView);
        Position <Integer>position= new Position<Integer>(3, 2);
        Segment segment = new Segment(position);
        plateauController.initPlateau();
        plateauController.setPlateauCellType((int)segment.getPosition().getPositionX(), (int)segment.getPosition().getPositionY(), "*");
        plateauController.UpdatePlateau();
    }
}
