package poo.modele;

/**
 * L'interface Player définit les méthodes nécessaires pour représenter un joueur dans le jeu.
 *
 * @param <U> Type de données pour les coordonnées du joueur.
 */
public interface Player<U extends Number> {

    /**
     * Obtient le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    String getName();

    /**
     * Définit le nom du joueur.
     *
     * @param name Le nouveau nom du joueur.
     */
    void setName(String name);

    /**
     * Obtient le serpent associé au joueur.
     *
     * @return Le serpent du joueur.
     */
    Snake<U> getSnake();

    /**
     * Définit le serpent associé au joueur.
     *
     * @param snake Le nouveau serpent du joueur.
     */
    void setSnake(Snake<U> snake);

    /**
     * Vérifie si le joueur a perdu (Game Over).
     *
     * @return true si le joueur a perdu, sinon false.
     */
    boolean isGameOver();

    /**
     * Définit l'état de Game Over pour le joueur.
     *
     * @param gameOver true si le joueur a perdu, sinon false.
     */
    void setGameOver(boolean gameOver);
}
