package poo.modele;

/**
 * L'énumération SegmentType représente les différents types de segments sur le plateau de jeu.
 * Un segment peut être de type VIDE, NOURRITURE ou SERPENT.
 */
public enum SegmentType {
    VIDE,         // Segment vide sur le plateau
    NOURRITURE,   // Segment représentant une nourriture
    SERPENT       // Segment faisant partie du serpent d'un joueur
}
