package poo.controller;

import poo.modele.Plateau;
import poo.view.PlateauView;

public class PlateauController {
    private Plateau plateau ;
    private PlateauView plateauView ;

    public PlateauController(Plateau plateau, PlateauView plateauView) {
        this.plateau = plateau;
        this.plateauView = plateauView;
    }

   // controle l'objet modele
    public  String[][] getPlateauTab() {
        return plateau.getCells();
    }
    public int getPlateauLargeur(){
        return plateau.getLargeur();
    }
     public int getPlateauLongueur(){
        return plateau.getLongueur();
    }
    public void setPlateauCellType(int x, int y, String string){
        plateau.setCellType(x, y, string);
    }
    //controle l'objet view

    public PlateauView getPlateauView() {
        return plateauView;
    }

    public void initPlateau(){

        for (int x = 0; x < getPlateauLargeur(); x++) {
            for (int y = 0; y < getPlateauLongueur(); y++) {
                 setPlateauCellType(x, y, " ");;
            }
        } 
      }
     
      public void UpdatePlateau (){
         plateauView.AfficherPlateau(getPlateauLargeur(), getPlateauLongueur(), getPlateauTab());
      }
    
}
