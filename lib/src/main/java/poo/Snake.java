package poo;

import java.util.List;

public class Snake {
    private List<Segment> body;
    private int length;
    private Direction direction=Direction.HAUT ;
    
    public Direction getDirection() {
        return direction;
    }
    public int getLength() {
        return length;
    }

}
