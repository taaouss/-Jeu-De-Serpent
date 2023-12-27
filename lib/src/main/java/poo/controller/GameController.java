package poo.controller;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import poo.modele.*;

public class GameController<U extends Number> {
    private Game<U> jeu;

    public GameController(Game<U> jeu) {
        this.jeu = jeu;
    }

    public void gameInit() {

        for (Player<U> player : jeu.getPlayers()) {

            jeu.getPlateau().setCellType((int) player.getSnake().getHead().getPosition().getPositionX(),
                    (int) player.getSnake().getHead().getPosition().getPositionY(), SegmentType.SERPENT);
        }

    }

    public Player getJoueur(int i) {
        return jeu.getPlayer(i);
    }

    public List<Position<U>> genererNourriture(int longueur, int largeur) {
        List<Position<U>> nourriture = new ArrayList<>();
        for (int i = 0; i < jeu.getNombreNourriture(); i++) {
           
            nourriture.add(genererUneNourriture(longueur, largeur));
        }
        return nourriture;
    }

    public Position<U> genererUneNourriture(int longueur, int largeur){
        Random random = new Random();
         U x = genererCoordonnee(random, longueur);
         U y = genererCoordonnee(random, largeur);

           return new Position<U>(x, y);
    }

    private U genererCoordonnee(Random random, int maxValue) {
        return (U) Integer.valueOf(random.nextInt(maxValue));
    }

    public void jouer(BoardGameController plateauController) {
        boolean continu = true;
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            while (continu) {
                for (Player<U> player : jeu.getPlayers()) {

                    if (player instanceof HumanPlayer) {

                        input = scanner.nextLine();

                    } else {

                        // AiPlayer
                        input = genererEntree();

                    }

                    if (input.equalsIgnoreCase("w")) {
                        System.out.println("Fin du programme.");
                        continu = false;
                        break;
                    }

                    if (!traitementDirection(input, jeu.getPlayers().indexOf(player))) {
                        continu = false;
                        break;
                    }

                   plateauController.setPlateau(jeu.getPlateau());
                   plateauController.UpdatePlateau(jeu.getPlayers());

                }

            }
        } catch (Exception e) {
            System.out.println("erreur" + e);
        }
    }

    private static String genererEntree() {
        // Créer une instance de la classe Random
        Random random = new Random();

        // Générer un nombre aléatoire entre 0 et 3
        int randomNumber = random.nextInt(4);

        // Mapper le nombre généré à une lettre
        switch (randomNumber) {
            case 0:
                return "z";
            case 1:
                return "s";
            case 2:
                return "q";
            case 3:
                return "d";
            default:
                throw new IllegalStateException("Unexpected value: " + randomNumber);
        }
    }

    private boolean deplacementValid(DirectionKey direction, int numerJoueur) {
        switch (direction) {
            case UP:

                if ((int) jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX() < 1)
                    return false;
                else
                    return true;
            case DOWN:
                if ((int) jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX()
                        + 1 == (int) jeu.getPlateau()
                                .getLongueur())
                    return false;
                else
                    return true;
            case RIGHT:
                if ((int) jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY()
                        + 1 == (int) jeu.getPlateau()
                                .getLargeur())
                    return false;
                else
                    return true;
            case LEFT:
                if ((int) jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY() == 0)
                    return false;
                else
                    return true;

            default:
                System.err.println("erreur");
                return false;
        }

    }

    private U incrementValue(U value) {
        if (value instanceof Integer) {
            return (U) Integer.valueOf(value.intValue() + 1);
        } else if (value instanceof Double) {
            return (U) Double.valueOf(value.doubleValue() + 1);
        }
        return value;
    }

    private U decrementerValue(U value) {
        if (value instanceof Integer) {
            return (U) Integer.valueOf(value.intValue() - 1);
        } else if (value instanceof Double) {
            return (U) Double.valueOf(value.doubleValue() - 1);
        }
        return value;
    }

  private boolean traitementDirection(String input, int numerJoueur) {
    DirectionKey direction = DirectionKey.transformer(input);

    if (direction != null) {
        if (deplacementValid(direction, numerJoueur)) {
            // Enregistrez les anciennes coordonnées
            U oldX = this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX();
            U oldY = this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY();

            // Déplacez le serpent en fonction de la direction
            switch (direction) {
                case UP:
                    deplacerSerpent(numerJoueur, decrementerValue(oldX), oldY);
                    break;
                case DOWN:
                    deplacerSerpent(numerJoueur, incrementValue(oldX), oldY);
                    break;
                case RIGHT:
                    deplacerSerpent(numerJoueur, oldX, incrementValue(oldY));
                    break;
                case LEFT:
                    deplacerSerpent(numerJoueur, oldX, decrementerValue(oldY));
                    break;
                default:
                    System.err.println("Direction inattendue");
                    return true;
            }

            // Mettez à jour le plateau
          //  plateauController.UpdatePlateau(jeu.getPlayers());

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

private void deplacerSerpent(int numerJoueur, U newX, U newY) {
    if (jeu.getPlateau().getCellType(newX, newY) == SegmentType.NOURRITURE) {
        this.jeu.getPlayer(numerJoueur).getSnake().addLength(new Position<>(newX, newY));
        this.jeu.getPlateau().setCellType((int) newX, (int) newY, SegmentType.SERPENT);
        Position <U>food = genererUneNourriture(jeu.getPlateau().getLongueur(), jeu.getPlateau().getLargeur());

        // Vérifiez si la nouvelle position de la nourriture chevauche la position du serpent
        while (this.jeu.getPlateau().getCellType( food.getPositionX(), food.getPositionY()) == SegmentType.SERPENT) {
            // Si c'est le cas, régénérez une nouvelle position pour la nourriture
            food = genererUneNourriture(jeu.getPlateau().getLongueur(), jeu.getPlateau().getLargeur());
        }

        // Mettez à jour le plateau avec la nouvelle position de la nourriture
        this.jeu.getPlateau().setCellType((int) food.getPositionX(), (int) food.getPositionY(), SegmentType.NOURRITURE);

    } else {
        Position<U> oldPosition = this.jeu.getPlayer(numerJoueur).getSnake().getLast().getPosition();
        this.jeu.getPlateau().setCellType((int) oldPosition.getPositionX(), (int) oldPosition.getPositionY(), SegmentType.VIDE);
        this.jeu.getPlayer(numerJoueur).getSnake().seDeplacer(new Position<>(newX, newY));
        this.jeu.getPlateau().setCellType((int) newX, (int) newY, SegmentType.SERPENT);
    }
}


}
