package poo.modele;

/**
 * La classe Segment représente un segment du plateau de jeu.
 *
 * @param <U> Le type de données utilisé pour les coordonnées du segment.
 */
public class Segment<U extends Number> {
    private SegmentType type;         // Type du segment (ex: VIDE, SERPENT, NOURRITURE)
    private Position<U> position;     // Position du segment sur le plateau

    /**
     * Constructeur de la classe Segment.
     *
     * @param position La position du segment sur le plateau.
     * @param type     Le type du segment (ex: VIDE, SERPENT, NOURRITURE).
     */
    public Segment(Position<U> position, SegmentType type) {
        this.position = position.copy();
        this.type = type;
    }

    /**
     * Obtient une copie de la position du segment.
     *
     * @return Une copie de la position du segment.
     */
    public Position<U> getPosition() {
        return position.copy();
    }

    /**
     * Définit la position du segment.
     *
     * @param position La nouvelle position du segment.
     */
    public void setPosition(Position<U> position) {
        this.position = position;
    }

    /**
     * Obtient le type du segment.
     *
     * @return Le type du segment.
     */
    public SegmentType getType() {
        return type;
    }

    /**
     * Définit le type du segment.
     *
     * @param type Le nouveau type du segment.
     */
    public void setType(SegmentType type) {
        this.type = type;
    }
}
