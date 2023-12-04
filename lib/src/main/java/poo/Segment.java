package poo;

public class Segment<U extends Number> {
    // type
    private Position<U> position;
    //private final int size=10;

    public Segment(Position<U> position){
        this.position= position.copy();
    }
    // public int getSize() {
    //     return size;
    // }
    public Position<U> getPosition() {
        return position.copy();
    }
    public void deplacer(){}


}
