package poo;
import poo.controller.PlateauController;
import poo.modele.Plateau;
import poo.modele.Position;
import poo.view.PlateauView;
import poo.modele.Segment;

public class Main  {

    public static void main(String[] args) {
        Plateau plateau = new Plateau(15, 15);
        PlateauView plateauView = new PlateauView();
        PlateauController plateauController = new PlateauController(plateau,plateauView);
        Position <Integer>position= new Position<Integer>(5, 5);
        Segment segment = new Segment(position);
        plateauController.setPlateauCellType((int)segment.getPosition().getPositionX(), (int)segment.getPosition().getPositionY(), "taa");
        System.out.println(plateauController.getPlateauTab()[5][5]);
        plateauController.initPlateau();
    }
}
