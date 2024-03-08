package poo.controller;

import java.util.List;
import poo.modele.BoardGame;
import poo.modele.Player;
import poo.modele.Position;
import poo.modele.Segment;
import poo.modele.SegmentType;
import poo.view.BoardGameView;

/**
 * Contrôleur du plateau de jeu.
 * Gère les interactions entre le modèle (BoardGame) et la vue (BoardGameView).
 *
 * @param <U> Type générique pour les coordonnées du plateau.
 */
public class BoardGameController<U extends Number> {
    private BoardGame<U> plateau;
    private BoardGameView<U> plateauView;

    /**
     * Constructeur du contrôleur du plateau de jeu.
     *
     * @param plateau     Modèle du plateau de jeu.
     * @param plateauView Vue du plateau de jeu.
     */
    public BoardGameController(BoardGame<U> plateau, BoardGameView<U> plateauView) {
        this.plateau = plateau;
        this.plateauView = plateauView;
    }

    /**
     * Récupère la représentation matricielle du plateau.
     *
     * @return Tableau de segments représentant le plateau.
     */
    public Segment<U>[][] getPlateauTab() {
        return this.plateau.getCells();
    }

    /**
     * Récupère la largeur du plateau.
     *
     * @return Largeur du plateau.
     */
    public int getPlateauLargeur() {
        return plateau.getLargeur();
    }

    /**
     * Récupère la longueur du plateau.
     *
     * @return Longueur du plateau.
     */
    public int getPlateauLongueur() {
        return plateau.getLongueur();
    }

    /**
     * Modifie le modèle du plateau.
     *
     * @param plateau Nouveau modèle du plateau.
     */
    public void setPlateau(BoardGame<U> plateau) {
        this.plateau = plateau;
    }

    /**
     * Modifie le type d'une cellule du plateau.
     *
     * @param x     Coordonnée X de la cellule.
     * @param y     Coordonnée Y de la cellule.
     * @param type  Nouveau type de la cellule.
     */
    public void setPlateauCellType(int x, int y, SegmentType type) {
        plateau.setCellType(x, y, type);
    }

    /**
     * Récupère le type d'une cellule du plateau.
     *
     * @param x Coordonnée X de la cellule.
     * @param y Coordonnée Y de la cellule.
     * @return Type de la cellule.
     */
    public SegmentType getPlateauCellType(U x, U y) {
        return plateau.getCellType(x, y);
    }

    /**
     * Récupère la vue du plateau.
     *
     * @return Vue du plateau.
     */
    public BoardGameView<U> getPlateauView() {
        return plateauView;
    }

    /**
     * Initialise le plateau avec des positions de nourriture.
     *
     * @param nourriture Liste des positions de nourriture.
     */
    public void initPlateau(List<Position<U>> nourriture) {
        for (Position<U> p : nourriture) {
            setPlateauCellType((Integer) p.getPositionX(), (Integer) p.getPositionY(), SegmentType.NOURRITURE);
        }
    }

    /**
     * Met à jour la vue du plateau en fonction des joueurs.
     *
     * @param players Liste des joueurs.
     */
    public void UpdatePlateau(List<Player<U>> players) {
        plateauView.AfficherPlateau(getPlateauLargeur(), getPlateauLongueur(), getPlateauTab());
    }
}
