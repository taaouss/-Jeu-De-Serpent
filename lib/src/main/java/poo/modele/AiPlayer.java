package poo.modele;

public class AiPlayer  <U extends Number> implements Player <U> {
    private String name;
    private Snake<U> snake;

    public AiPlayer(String name,U x,U y){
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
