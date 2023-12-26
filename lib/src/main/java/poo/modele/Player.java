package poo.modele;

public class Player <U extends Number>{
    private String name;
    private Snake<U> snake;
    // score ?
    
    public Player(String name,U x,U y){
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

    public void setSnake(Snake <U> snake) {
        this.snake = snake;
    }
    

}
