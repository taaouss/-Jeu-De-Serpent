package poo;
import poo.controller.PlateauController;
import poo.modele.Plateau;
import poo.modele.Position;
import poo.view.PlateauView;
import poo.modele.Segment;
import poo.modele.SegmentType;

import java.awt.event.KeyEvent;
import java.util.Scanner;
public class Main  {

    public static void main(String[] args) {
        Plateau<Integer> plateau = new Plateau<Integer>(5, 5);
        PlateauView <Integer>plateauView = new PlateauView<Integer>();
        PlateauController<Integer> plateauController = new PlateauController<Integer>(plateau,plateauView);
        Position <Integer>position= new Position<Integer>(3, 2);
        Segment<Integer> segment = new Segment<Integer>(position,SegmentType.SERPENT);
        plateauController.initPlateau();
        plateauController.setPlateauCellType((int)segment.getPosition().getPositionX(), (int)segment.getPosition().getPositionY(), SegmentType.SERPENT);
        plateauController.UpdatePlateau();
    

        
    }
}
