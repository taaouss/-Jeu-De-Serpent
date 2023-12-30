package poo.modele;

import java.util.ArrayList;
import java.util.List;

public class Snake <U extends Number>{
    private List<Segment<U>> body = new ArrayList<Segment<U>>();
    private int length;
    private DirectionKey direction=DirectionKey.LEFT ;
    private Segment <U> head ;
    private double speed =1;  // Ajoutez cette variable à la classe Snake

// Ajoutez ces méthodes à la classe Snake
public double getSpeed() {
    return speed;
}

public void setSpeed(double speed) {
    this.speed = speed;
}

     
    public DirectionKey getDirection() {
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

    public void setDirection(DirectionKey direction) {
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
    public boolean detecterAutoCollision() {
        Position<U> tetePosition = this.getHead().getPosition();
    
        for (int i = 1; i < this.getBody().size(); i++) {
            Position<U> segmentPosition = this.getBody().get(i).getPosition();
    
            if (segmentPosition.getPositionX().equals(tetePosition.getPositionX()) &&
                segmentPosition.getPositionY().equals(tetePosition.getPositionY())) {
                return true; // Auto-collision détectée
            }
        }
    
        return false;
    }

    
    
}
