package poo.modele;


public class Segment<U extends Number> {
    // type du segment
    private Position<U> position;

    public Segment(Position<U> position){
        this.position= position.copy();
    }
  
    public Position<U> getPosition() {
        return position.copy();
    }

    public void setPosition(Position<U> position) {
        this.position = position;
    }
    
}
