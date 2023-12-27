package poo.modele;

import java.util.ArrayList;
import java.util.List;

public class Snake <U extends Number>{
    private List<Segment<U>> body = new ArrayList<Segment<U>>();
    private int length;
    private Direction direction=Direction.HAUT ;
    private Segment <U> head ;
     //couleur
     //head ?
 
     
    public Direction getDirection() {
        return direction ;
    }
    public Snake(U x,U y ){
        this.head = new Segment<U>(new Position<U>(x, y), SegmentType.SERPENT);
        body.add(head);
        length =1;
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
        for(int i= length-1 ; i > 0 ; i --){
            body.get(i).setPosition(body.get(i-1).getPosition());
        }
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
    public Segment<U> getLast(){
        System.out.println("lyess"+ this.body.get(this.length-1).getPosition().getPositionX().toString() + this.body.get(this.length-1).getPosition().getPositionY().toString()  );
        return this.body.get(this.body.size()-1);
    }
    public void addLength(Position <U> position){
        length++;
        body.add(0,new Segment<U>(position,SegmentType.SERPENT));
        head= body.get(0);
    }
}
