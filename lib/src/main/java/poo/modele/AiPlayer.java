package poo.modele;

/**
 * La classe AiPlayer représente un joueur artificiel (IA) dans le jeu.
 *
 * @param <U> Type de données pour les coordonnées du joueur.
 */
public class AiPlayer<U extends Number> implements Player<U> {

    private String name;
    private Snake<U> snake;
    private boolean gameOver = false;

    /**
     * Constructeur de la classe AiPlayer.
     *
     * @param name Le nom du joueur artificiel.
     * @param x    La coordonnée X initiale du serpent du joueur.
     * @param y    La coordonnée Y initiale du serpent du joueur.
     */
    public AiPlayer(String name, U x, U y) {
        this.name = name;
        this.snake = new Snake<>(x, y);
    }

    /**
     * Obtient le nom du joueur artificiel.
     *
     * @return Le nom du joueur artificiel.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du joueur artificiel.
     *
     * @param name Le nouveau nom du joueur artificiel.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient le serpent associé au joueur artificiel.
     *
     * @return Le serpent du joueur artificiel.
     */
    public Snake<U> getSnake() {
        return snake;
    }

    /**
     * Définit le serpent associé au joueur artificiel.
     *
     * @param snake Le nouveau serpent du joueur artificiel.
     */
    public void setSnake(Snake<U> snake) {
        this.snake = snake;
    }

    /**
     * Vérifie si le joueur artificiel a perdu (Game Over).
     *
     * @return true si le joueur artificiel a perdu, sinon false.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Définit l'état de Game Over pour le joueur artificiel.
     *
     * @param gameOver true si le joueur artificiel a perdu, sinon false.
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
