package poo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Segment<U extends Number> {
    // type du segment
    private static final int CELL_SIZE = 10;
    private Position<U> position;
    private Rectangle view ;
    //private final int size=10;

    public Segment(Position<U> position){
        this.position= position.copy();
        this.view = new Rectangle((double)position.getPositionX() * CELL_SIZE, (double)position.getPositionY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        this.view.setFill(Color.GREEN); // Couleur du segment
    }
    // public int getSize() {
    //     return size;
    // }
    public Position<U> getPosition() {
        return position.copy();
    }

     public void deplacer(U deltaX, U deltaY) {
       position.setPositionX(deltaX);
       position.setPositionY(deltaY);
    // Mettre à jour la position du rectangle dans la scène
       view.setX((double)position.getPositionX() * CELL_SIZE);
       view.setY((double)position.getPositionY() * CELL_SIZE);
        }

        public Rectangle getView() {
            return view;
        }

}
