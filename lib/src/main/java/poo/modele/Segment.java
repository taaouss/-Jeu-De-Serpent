package poo.modele;


public class Segment<U extends Number> {
    private SegmentType type ;
    private Position<U> position;

    public Segment(Position<U> position,SegmentType type){
        this.position= position.copy();
        this.type = type;
    }
  
    public Position<U> getPosition() {
        return position.copy();
    }

    public void setPosition(Position<U> position) {
        this.position = position;
    }


    public SegmentType getType() {
        return type;
    }

    public void setType(SegmentType type) {
        this.type = type;
    }


    }
    
  
