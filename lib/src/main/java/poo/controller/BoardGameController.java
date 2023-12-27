package poo.controller;
import java.util.List;
import poo.modele.BoardGame;
import poo.modele.Position;
import poo.modele.Segment;
import poo.modele.SegmentType;
import poo.view.BoardGameView;

public class BoardGameController <U extends Number>{
    private BoardGame <U> plateau ;
    private BoardGameView<U> plateauView ;

    public BoardGameController(BoardGame<U> plateau, BoardGameView<U> plateauView) {
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
    public void setPlateau(BoardGame plateau){
        this.plateau=plateau;
    }
    public void setPlateauCellType(int x, int y, SegmentType string){
        plateau.setCellType(x, y, string);
    } 
     public SegmentType getPlateauCellType(U x, U y){
           return plateau.getCellType(x,y) ;
     }
    //controle l'objet view

    public BoardGameView <U> getPlateauView() {
        return plateauView;
    }

    public void initPlateau(List<Position<U>> nourriture){

        for(Position<U> p :nourriture){
          setPlateauCellType((Integer)p.getPositionX(),(Integer)p.getPositionY(), SegmentType.NOURRITURE);;
        }
      }
     
    public void UpdatePlateau (){
        plateauView.AfficherPlateau(getPlateauLargeur(), getPlateauLongueur(), getPlateauTab());
      }
    
    

}
