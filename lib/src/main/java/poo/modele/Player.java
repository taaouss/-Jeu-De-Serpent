package poo.modele;

public interface Player <U extends Number>{
   
    public String getName() ;
    public void setName(String name) ;
    public Snake<U> getSnake() ;
    public void setSnake(Snake <U> snake) ;
    public boolean isGameOver();
    public void setGameOver(boolean gameOver) ;
    public void regenererNouveauSerpent(U x, U y) ;

}
