package poo.modele;

public class Position<U extends Number> {
    private U positionX;
    private U positionY;

    public Position(U positionX,U positionY){
        this.positionX=positionX;
        this.positionY=positionY;
    }
    public U getPositionX() {
        return positionX;
    }
    public U getPositionY() {
        return positionY;
    }
    public void setPositionX(U deltaX) {
        this.positionX = deltaX;
    }
    public void setPositionY(U deltaY) {
        this.positionY = deltaY;
    }
    public Position<U> copy(){
        return new Position<U>(this.positionX, this.positionY);
    }
    
}
