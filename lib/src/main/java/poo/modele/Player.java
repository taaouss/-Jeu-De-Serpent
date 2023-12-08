package poo.modele;

public class Player <U extends Number>{
    private String name;
    private Snake<U> snake;
    // score ?
    
    public Player(String name){
        this.name= name;
        this.snake= new Snake<U>();
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
