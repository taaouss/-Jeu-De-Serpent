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

        for (Player<U> player : jeu.getPlayers()){

            jeu.getPlateau().setCellType((int) player.getSnake().getHead().getPosition().getPositionX(),
            (int) player.getSnake().getHead().getPosition().getPositionY(), SegmentType.SERPENT);
        }
       
        
    }

    public Player getJoueur(int i) {
        return jeu.getPlayer(i);
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

    public void jouer(BoardGameController plateauController) {
        boolean continu = true ;
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            while (continu) {
             for (Player<U> player : jeu.getPlayers()){

                if(player instanceof HumanPlayer) {

                     input = scanner.nextLine();

                 }else {

                    //AiPlayer
                  input =genererEntree();

                }

                if (input.equalsIgnoreCase("w")) {
                    System.out.println("Fin du programme.");
                    continu = false;
                    break;
                }

                if (!traitementDirection(input ,jeu.getPlayers().indexOf(player))) {continu =false ; break ;}
                  
                plateauController.setPlateau(jeu.getPlateau());
                plateauController.UpdatePlateau();

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
                if ((int) jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX()+1 == (int) jeu.getPlateau()
                        .getLongueur())
                    return false;
                else
                    return true;
            case RIGHT:
                if ((int) jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY()+1 == (int) jeu.getPlateau()
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
    

    private boolean traitementDirection(String input,int numerJoueur) {
        // Gestion des directions en utilisant une énumération
        DirectionKey direction = DirectionKey.transformer(input);
        if (direction != null) {

            if (deplacementValid(direction,numerJoueur)) {
                System.out.println("Direction : " + direction);
                Segment seg = this.jeu.getPlayer(numerJoueur).getSnake().getHead();
                System.out.println("ancien " + (Integer) seg.getPosition().getPositionX()
                        + (Integer) seg.getPosition().getPositionY());
                jeu.getPlateau().setCellType((Integer) seg.getPosition().getPositionX(),
                        (Integer) seg.getPosition().getPositionY(), SegmentType.VIDE);

                switch (direction) {
                    case UP:

                        U newX = decrementerValue(this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX());
                        U newY=this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY();
                        if (jeu.getPlateau().getCellType(newX, newY)==SegmentType.NOURRITURE){

                            this.jeu.getPlayer(numerJoueur).getSnake().addLength(new Position<>(newX, newY));
                            this.jeu.getPlateau().setCellType((int)newX,(int) newY, SegmentType.SERPENT);
                        }
                        else  this.jeu.getPlayer(numerJoueur).getSnake().seDeplacer(new Position<>(newX, newY));
                        break;

                    case DOWN:

                        U newX1 = incrementValue(this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX());
                        U newY1=this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY();
                        if (jeu.getPlateau().getCellType(newX1, newY1)==SegmentType.NOURRITURE){

                            this.jeu.getPlayer(numerJoueur).getSnake().addLength(new Position<>(newX1, newY1));
                            this.jeu.getPlateau().setCellType((int)newX1,(int) newY1, SegmentType.SERPENT);

                        }
                        else this.jeu.getPlayer(numerJoueur).getSnake().seDeplacer(new Position<>(newX1, newY1));
                        break;

                    case RIGHT:

                        U newX2 = this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX();
                        U newY2 =incrementValue(this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY());
                        if (jeu.getPlateau().getCellType(newX2, newY2)==SegmentType.NOURRITURE){
                            this.jeu.getPlayer(numerJoueur).getSnake().addLength(new Position<>(newX2, newY2));
                             this.jeu.getPlateau().setCellType((int)newX2,(int) newY2, SegmentType.SERPENT);

                        }
                        else  this.jeu.getPlayer(numerJoueur).getSnake().seDeplacer(new Position<>(newX2, newY2));

                        break;
                    case LEFT:

                        U newX3= this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX();
                        U newY3 =decrementerValue(this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY());
                        if (jeu.getPlateau().getCellType(newX3, newY3)==SegmentType.NOURRITURE){
                         this.jeu.getPlayer(numerJoueur).getSnake().addLength(new Position<>(newX3, newY3));
                         this.jeu.getPlateau().setCellType((int)newX3,(int) newY3, SegmentType.SERPENT);

                        }
                        else this.jeu.getPlayer(numerJoueur).getSnake().seDeplacer(new Position<>(newX3, newY3));
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
