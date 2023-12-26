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

    private U incrementValue(U value) {
        if (value instanceof Integer) {
            return (U) Integer.valueOf(value.intValue() + 1);
        } else if (value instanceof Double) {
            return (U) Double.valueOf(value.doubleValue() + 1);
        }
        return value;
    }
    private U decrementerValue(U value) {
        if (value instanceof Integer) {
            return (U) Integer.valueOf(value.intValue() - 1);
        } else if (value instanceof Double) {
            return (U) Double.valueOf(value.doubleValue() - 1);
        }
        return value;
    }
    
    
    public void deplacerright() {
        U newX = this.position.getPositionX();
        U newY = incrementValue(this.position.getPositionY());
        this.position = new Position<>(newX, newY);
    }
    public void deplacerLeft() {
        U newX = this.position.getPositionX();
        U newY = decrementerValue(this.position.getPositionY());
        this.position = new Position<>(newX, newY);
    }
    public void deplacerUp() {
        U newX = decrementerValue(this.position.getPositionX());
        U newY = this.position.getPositionY();
        this.position = new Position<>(newX, newY);
    }
    public void deplacerDown () {
        U newX = incrementValue(this.position.getPositionX());
        U newY = this.position.getPositionY();
        this.position = new Position<>(newX, newY);
    }
}
