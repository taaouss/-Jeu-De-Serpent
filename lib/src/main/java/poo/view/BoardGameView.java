package poo.view;

import poo.modele.*;

/**
 * Classe responsable de l'affichage du plateau de jeu dans la console.
 *
 * @param <U> Le type générique utilisé pour les coordonnées du plateau.
 */
public class BoardGameView<U extends Number> {

    /**
     * Affiche le plateau de jeu dans la console en utilisant des caractères ASCII.
     *
     * @param largeur  La largeur du plateau.
     * @param longueur La longueur du plateau.
     * @param segments Le tableau bidimensionnel de segments représentant le plateau.
     */
    public void AfficherPlateau(int largeur, int longueur, Segment<U>[][] segments) {

        // Affichage du plateau
        for (int x = 0; x < largeur; x++) {
            System.out.println();
            for (int y = 0; y < longueur; y++) {
                System.out.print("_ _ ");
            }
            System.out.println();

            for (int y = 0; y < longueur; y++) {
                // Affichage du contenu de chaque cellule en fonction du type de segment
                switch (segments[y][x].getType()) {
                    case VIDE:
                        System.out.printf("|   ");
                        break;
                    case NOURRITURE:
                        System.out.printf("|  $");
                        break;
                    case SERPENT:
                        System.out.printf("|  O");
                        break;
                    default:
                        System.out.printf("|  "); 
                        break;
                }
            }
            System.out.print(" |");
        }

        System.out.println();

        // Ligne inférieure du plateau
        for (int y = 0; y < longueur; y++) {
            System.out.print("_ _ ");
        }

        System.out.println();
    }
}
