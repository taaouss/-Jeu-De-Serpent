package poo.modele;

import java.util.List;

public class Snake {
    private List<Segment> body;
    private int length;
    private Direction direction=Direction.HAUT ;
     //couleur
     //head ?

    public Direction getDirection() {
        return direction ;
    }
    public int getLength() {
        return length;
    }
    public void setBody(List<Segment> body) {
        this.body = body;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
   
    
}
