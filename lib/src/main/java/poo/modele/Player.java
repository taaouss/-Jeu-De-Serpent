package poo.modele;

public class Player {
    private String name;
    private Snake snake;
    // score ?
    
    public Player(String name){
        this.name= name;
        this.snake= new Snake();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    

}
