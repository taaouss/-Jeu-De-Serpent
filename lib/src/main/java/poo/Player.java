package poo;

public class Player {
    private String name;
    private Snake snake;

    public Player(String name){
        this.name= name;
        this.snake= new Snake();
    }
}
