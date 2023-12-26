package poo.controller;

import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import com.google.common.io.LineReader;

import javafx.scene.input.KeyEvent;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import poo.modele.*;

public class GameController<U extends Number> {
    private Game<U> jeu;

    public GameController(Game<U> jeu) {
        this.jeu = jeu;
    }

    public void gameInit() {
        jeu.getPlateau().setCellType((int) jeu.getPlayer().getSnake().getHead().getPosition().getPositionX(),
                (int) jeu.getPlayer().getSnake().getHead().getPosition().getPositionY(), SegmentType.SERPENT);
        ;
    }

    public Player<U> getJoueur() {
        return jeu.getPlayer();
    }

    public List<Position<U>> genererNourriture(int longueur, int largeur) {
        List<Position<U>> nourriture = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < jeu.getNombreNourriture(); i++) {
            U x = genererCoordonnee(random, longueur);
            U y = genererCoordonnee(random, largeur);

            Position<U> segment = new Position<U>(x, y);
            nourriture.add(segment);
        }

        return nourriture;
    }

    private U genererCoordonnee(Random random, int maxValue) {
        return (U) Integer.valueOf(random.nextInt(maxValue));
    }

    public void jouer(PlateauController plateauController) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("w")) {
                    System.out.println("Fin du programme.");
                    break;
                }

                if (!traitementDirection(input))
                    break;
                plateauController.setPlateau(jeu.getPlateau());
                plateauController.UpdatePlateau();

            }

        } catch (Exception e) {
            System.out.println("erreur" + e);
        }
    }

    private boolean deplacementValid(DirectionKey direction) {
        switch (direction) {
            case UP:

                if ((int) jeu.getPlayer().getSnake().getHead().getPosition().getPositionX() < 1)
                    return false;
                else
                    return true;
            case DOWN:
                if ((int) jeu.getPlayer().getSnake().getHead().getPosition().getPositionX()+1 == (int) jeu.getPlateau()
                        .getLongueur())
                    return false;
                else
                    return true;
            case RIGHT:
                if ((int) jeu.getPlayer().getSnake().getHead().getPosition().getPositionY()+1 == (int) jeu.getPlateau()
                        .getLargeur())
                    return false;
                else
                    return true;
            case LEFT:
                if ((int) jeu.getPlayer().getSnake().getHead().getPosition().getPositionY() == 0)
                    return false;
                else
                    return true;

            default:
                System.err.println("erreur");
                return false;
        }

    }

    private boolean traitementDirection(String input) {
        // Gestion des directions en utilisant une énumération
        DirectionKey direction = DirectionKey.transformer(input);
        if (direction != null) {
            if (deplacementValid(direction)) {
                System.out.println("Direction : " + direction);
                Segment seg = this.getJoueur().getSnake().getHead();
                System.out.println("ancien " + (Integer) seg.getPosition().getPositionX()
                        + (Integer) seg.getPosition().getPositionY());
                jeu.getPlateau().setCellType((Integer) seg.getPosition().getPositionX(),
                        (Integer) seg.getPosition().getPositionY(), SegmentType.VIDE);

                switch (direction) {
                    case UP:

                        seg.deplacerUp();
                        break;
                    case DOWN:
                        seg.deplacerDown();
                        break;
                    case RIGHT:
                        seg.deplacerright();
                        break;
                    case LEFT:
                        seg.deplacerLeft();
                        break;

                    default:
                        System.err.println("erreur");
                        break;
                }
                jeu.getPlateau().setCellType((Integer) seg.getPosition().getPositionX(),
                        (Integer) seg.getPosition().getPositionY(), SegmentType.SERPENT);
                System.out.println(
                        "apres " + (Integer) seg.getPosition().getPositionX()
                                + (Integer) seg.getPosition().getPositionY());

                // Mettez en œuvre la logique pour déplacer le serpent en fonction de la
                // direction
                return true;
            } else {
                System.out.println("Game Over ");
                return false;
            }
        } else {
            System.out.println("Direction inconnue");
            return true;
        }
    }

    // Énumération pour représenter les directions
    // private enum Direction {
    // UP("\u001B[A"),
    // DOWN("\u001B[B"),
    // RIGHT("\u001B[C"),
    // LEFT("\u001B[D");

    // private final String input;

    // Direction(String input) {
    // this.input = input;
    // }

    // public static Direction transformer(String input) {
    // for (Direction direction : values()) {
    // if (input.startsWith(direction.input)) {
    // return direction;
    // }
    // }
    // return null;
    // }
    // }

}
