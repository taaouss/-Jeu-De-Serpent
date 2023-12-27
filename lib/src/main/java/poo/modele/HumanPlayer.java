package poo.modele;

public class HumanPlayer <U extends Number> implements Player{
    private String name;
    private Snake<U> snake;
    // score ?
    
    public HumanPlayer(String name,U x,U y){
        this.name= name;
        this.snake= new Snake<U>(x,y);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Snake<U> getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    

}
