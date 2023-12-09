package poo.modele;

import java.util.ArrayList;
import java.util.List;

public class Snake <U extends Number>{
    private List<Segment<U>> body = new ArrayList<>();
    private int length;
    private Direction direction=Direction.HAUT ;
    private Segment <U> head ;
     //couleur
     //head ?
 
     
    public Direction getDirection() {
        return direction ;
    }
    public Snake( ){
        this.head = new Segment<>(new Position(0, 0), SegmentType.SERPENT);
        body.add(head);
    }
    public int getLength() {
        return length;
    }
    public void setBody(List<Segment<U>> body) {
        this.body = body;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
   
    public void seDeplacer(Position<U> pos){
         body.get(0).setPosition(pos);
    }
    public List<Segment<U>> getBody() {
        return body;
    }
    public Segment<U> getHead() {
        return head;
    }
    public void setHead(Segment<U> head) {
        this.head = head;
    }
}
