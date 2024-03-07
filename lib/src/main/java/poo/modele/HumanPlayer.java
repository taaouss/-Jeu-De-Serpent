package poo.modele;

/**
 * La classe HumanPlayer représente un joueur humain dans le jeu.
 *
 * @param <U> Type de données pour les coordonnées du joueur.
 */
public class HumanPlayer<U extends Number> implements Player<U> {

    private String name;
    private Snake<U> snake;
    private boolean gameOver;

    /**
     * Constructeur de la classe HumanPlayer.
     *
     * @param name Le nom du joueur.
     * @param x    La coordonnée X initiale du serpent du joueur.
     * @param y    La coordonnée Y initiale du serpent du joueur.
     */
    public HumanPlayer(String name, U x, U y) {
        this.name = name;
        this.snake = new Snake<>(x, y);
        this.gameOver = false;
    }

    /**
     * Obtient le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du joueur.
     *
     * @param name Le nouveau nom du joueur.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient le serpent associé au joueur.
     *
     * @return Le serpent du joueur.
     */
    public Snake<U> getSnake() {
        return snake;
    }

    /**
     * Définit le serpent associé au joueur.
     *
     * @param snake Le nouveau serpent du joueur.
     */
    public void setSnake(Snake<U> snake) {
        this.snake = snake;
    }

    /**
     * Vérifie si le joueur a perdu (Game Over).
     *
     * @return true si le joueur a perdu, sinon false.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Définit l'état de Game Over pour le joueur.
     *
     * @param gameOver true si le joueur a perdu, sinon false.
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
