package poo.modele;

/**
 * La classe Position représente une position dans un espace bidimensionnel.
 *
 * @param <U> Le type de données utilisé pour les coordonnées X et Y.
 */
public class Position<U extends Number> {
    private U positionX; // Coordonnée X de la position
    private U positionY; // Coordonnée Y de la position

    /**
     * Constructeur de la classe Position.
     *
     * @param positionX La coordonnée X de la position.
     * @param positionY La coordonnée Y de la position.
     */
    public Position(U positionX, U positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Obtient la coordonnée X de la position.
     *
     * @return La coordonnée X de la position.
     */
    public U getPositionX() {
        return positionX;
    }

    /**
     * Obtient la coordonnée Y de la position.
     *
     * @return La coordonnée Y de la position.
     */
    public U getPositionY() {
        return positionY;
    }

    /**
     * Crée une copie de la position.
     *
     * @return Une nouvelle instance de Position avec les mêmes coordonnées.
     */
    public Position<U> copy() {
        return new Position<U>(this.positionX, this.positionY);
    }

    /**
     * Définit la coordonnée X de la position.
     *
     * @param xValue La nouvelle valeur de la coordonnée X.
     */
    public void setPositionX(U xValue) {
        this.positionX = xValue;
    }

    /**
     * Définit la coordonnée Y de la position.
     *
     * @param yValue La nouvelle valeur de la coordonnée Y.
     */
    public void setPositionY(U yValue) {
        this.positionY = yValue;
    }

    /**
     * Génère le code de hachage pour la position.
     *
     * @return Le code de hachage calculé.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((positionX == null) ? 0 : positionX.hashCode());
        result = prime * result + ((positionY == null) ? 0 : positionY.hashCode());
        return result;
    }
    /**
     * Vérifie l'égalité entre la position actuelle et un autre objet.
     *
     * @param obj L'objet à comparer avec la position.
     * @return true si les positions sont égales, sinon false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (positionX == null) {
            if (other.positionX != null)
                return false;
        } else if (!positionX.equals(other.positionX))
            return false;
        if (positionY == null) {
            if (other.positionY != null)
                return false;
        } else if (!positionY.equals(other.positionY))
            return false;
        return true;
    }
}
