package poo.modele;

public class HumanPlayer<U extends Number> implements Player <U >{
    private String name;
    private Snake<U> snake;
    private boolean gameOver;

    public HumanPlayer(String name, U x, U y) {
        this.name = name;
        this.snake = new Snake<>(x, y);
        this.gameOver = false;
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

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
   
    public void regenererNouveauSerpent(U x, U y) {
        this.snake = new Snake<>(x, y);
        this.gameOver = false;
    }

    // Méthode pour régénérer un nouveau serpent
  
}
