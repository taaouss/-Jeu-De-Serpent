package poo.modele;

/**
 * La classe BoardGame représente le plateau de jeu du serpent.
 *
 * @param <U> Type de données pour les coordonnées du plateau.
 */
public class BoardGame<U extends Number> {
    private U largeur, longueur;
    private Segment<U>[][] cells;

    /**
     * Constructeur de la classe BoardGame.
     *
     * @param largeur  La largeur du plateau.
     * @param longueur La longueur du plateau.
     */
    public BoardGame(U largeur, U longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.cells = new Segment[(int) longueur][(int) largeur];
        int lon = (int) longueur;
        int lar = (int) largeur;

        for (int i = 0; i < lon; i++) {
            for (int j = 0; j < lar; j++) {
                    this.cells[i][j] = new Segment<>(createPosition((U) Integer.valueOf(i), (U) Integer.valueOf(j)), SegmentType.VIDE);
                }
            }
        }

    private <T extends Number> Position<T> createPosition(T x, T y) {
        return new Position<>(x, y);
    }

    /**
     * Définit le type de segment à une position spécifiée sur le plateau.
     *
     * @param x      La coordonnée X de la position.
     * @param y      La coordonnée Y de la position.
     * @param string Le type de segment à définir.
     */
    public void setCellType(int x, int y, SegmentType string) {
        cells[x][y].setType(string);
    }

    /**
     * Obtient le type de segment à une position spécifiée sur le plateau.
     *
     * @param x La coordonnée X de la position.
     * @param y La coordonnée Y de la position.
     * @return Le type de segment à la position spécifiée.
     */
    public SegmentType getCellType(U x, U y) {
        return cells[(Integer) x][(Integer) y].getType();
    }

    /**
     * Obtient la matrice de segments représentant le plateau de jeu.
     *
     * @return La matrice de segments du plateau de jeu.
     */
    public Segment<U>[][] getCells() {
        return cells;
    }

    /**
     * Obtient la largeur du plateau.
     *
     * @return La largeur du plateau.
     */
    public int getLargeur() {
        return (int) largeur;
    }

    /**
     * Obtient la longueur du plateau.
     *
     * @return La longueur du plateau.
     */
    public int getLongueur() {
        return (int) longueur;
    }

    /**
     * Obtient la longueur du plateau en tant que type générique.
     *
     * @return La longueur du plateau en tant que type générique.
     */
    public U getLongueurU() {
        return longueur;
    }

    /**
     * Obtient la largeur du plateau en tant que type générique.
     *
     * @return La largeur du plateau en tant que type générique.
     */
    public U getLargeurU() {
        return largeur;
    }
}
