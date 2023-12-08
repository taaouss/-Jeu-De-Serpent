package poo.controller;



import poo.modele.Plateau;
import poo.modele.Segment;
import poo.modele.SegmentType;
import poo.view.PlateauView;

public class PlateauController <U extends Number>{
    private Plateau <U> plateau ;
    private PlateauView<U> plateauView ;

    public PlateauController(Plateau<U> plateau, PlateauView<U> plateauView) {
        this.plateau = plateau;
        this.plateauView = plateauView;
    }

   // controle l'objet modele
    public  Segment<U>[][] getPlateauTab() {
        return plateau.getCells();
    }
    public int getPlateauLargeur(){
        return plateau.getLargeur();
    }
     public int getPlateauLongueur(){
        return plateau.getLongueur();
    }
    public void setPlateauCellType(int x, int y, SegmentType string){
        plateau.setCellType(x, y, string);
    }
    //controle l'objet view

    public PlateauView <U> getPlateauView() {
        return plateauView;
    }

    public void initPlateau(){

        for (int x = 0; x < getPlateauLargeur(); x++) {
            for (int y = 0; y < getPlateauLongueur(); y++) {
                 setPlateauCellType(x, y, SegmentType.VIDE);;
            }
        } 
      }
     
    public void UpdatePlateau (){
        
        plateauView.AfficherPlateau(getPlateauLargeur(), getPlateauLongueur(), getPlateauTab());
      }
    
  

}
