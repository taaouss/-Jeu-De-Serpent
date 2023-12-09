package poo.controller;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import poo.modele.*;

public class GameController <U extends Number> {
    private Game<U> jeu ;

    public GameController(Game<U> jeu) {
        this.jeu = jeu;
    }
    public Player<U> getJoueur(){
        return jeu.getPlayer();}
        
    public List<Position<U>> genererNourriture(int longueur, int largeur){
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
     
    public void jouer() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("Q")) {
                    System.out.println("Fin du programme.");
                    break;
                }

                traitementDirection(input);
            }
        
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void traitementDirection(String input) {
        // Gestion des directions en utilisant une énumération
        Direction direction = Direction.transformer(input);
        if (direction != null) {
            System.out.println("Direction : " + direction);
            // Mettez en œuvre la logique pour déplacer le serpent en fonction de la direction
        } else {
            System.out.println("Direction inconnue");
        }
    }

    // Énumération pour représenter les directions
    private enum Direction {
        UP("\u001B[A"),
        DOWN("\u001B[B"),
        RIGHT("\u001B[C"),
        LEFT("\u001B[D");

        private final String input;

        Direction(String input) {
            this.input = input;
        }

        public static Direction transformer(String input) {
            for (Direction direction : values()) {
                if (input.startsWith(direction.input)) {
                    return direction;
                }
            }
            return null;
        }
    }
    
    
    }


