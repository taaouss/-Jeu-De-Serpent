package poo.controller;

import java.util.Random;
import java.util.Scanner;

import javafx.scene.input.KeyCode;

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
    private boolean positionOccupeeParSerpent(Position<U> position) {
        for (Player<U> joueur : jeu.getPlayers()) {
            Snake<U> serpent = joueur.getSnake();
            for (Segment<U> segment : serpent.getBody()) {
                if (segment.getPosition().equals(position)) {
                    return true; // La position est occupée par un serpent
                }
            }
        }
        return false; // La position n'est pas occupée par un serpent
    }
    public Player getJoueur(int i) {
        return jeu.getPlayer(i);
    }

    public List<Position<U>> genererNourriture(int longueur, int largeur) {
        List<Position<U>> nourriture = new ArrayList<>();
        for (int i = 0; i < jeu.getNombreNourriture(); i++) {
           
            nourriture.add(genererUneNourriture(longueur, largeur));
        }
        this.jeu.setNourritures(nourriture);
        return nourriture;
    }

    public Position<U> genererUneNourriture(int longueur, int largeur){
        Random random = new Random();
        Position<U> nouvelleNourriture ;
        U x,y;
        do {
            x = genererCoordonnee(random, longueur);
            y = genererCoordonnee(random, largeur);
           nouvelleNourriture = new Position<>(x, y);
        } while (positionOccupeeParSerpent(nouvelleNourriture));
           return new Position<U>(x, y);
    }

    private U genererCoordonnee(Random random, int maxValue) {
        return (U) Integer.valueOf(random.nextInt(maxValue));
    }

public void jouer(BoardGameController plateauController) {
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
                for (Player<U> player : jeu.getPlayers()) {
                    if(! player.isGameOver()){
                    if (player instanceof HumanPlayer) {
                       // input = scanner.nextLine();
                        //le faire bouger de la derniere position
                        Snake<U> serpentActuel = jeu.getPlayer(jeu.getPlayers().indexOf(player)).getSnake();
                        DirectionKey directionActuelle = serpentActuel.getDirection();
                        input = directionActuelle.getLetter(directionActuelle) ;

                    } else {

                        // AiPlayer
                       // input = genererEntreeVersNourriture(player.getSnake().getHead().getPosition(), jeu.getNourritures().get(0));
                       input = genererEntree();

                    }

                    if (input.equalsIgnoreCase("w")) {
                        System.out.println("Fin du programme.");
                        break;
                    }

                    if (!traitementDirection(input, jeu.getPlayers().indexOf(player))) {
                        jeu.getPlayer(jeu.getPlayers().indexOf(player)).setGameOver(true);
                        break;
                    }

                   plateauController.setPlateau(jeu.getPlateau());
                   plateauController.UpdatePlateau(jeu.getPlayers());
                }else {
                    //le joeur est mort donc on lui regenere le serpent 
                    Random random = new Random();
                    player.regenererNouveauSerpent(genererCoordonnee(random, jeu.getPlateau().getLongueur()),genererCoordonnee(random, jeu.getPlateau().getLargeur()));
                    
                }
                }
              // verifierNourritureMangee();

            }
         catch (Exception e) {
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
    // private String genererEntreeVersNourriture(Position<U> tete, Position<U> nourriture) {
    //     double diffX = tete.getPositionX().doubleValue() - nourriture.getPositionX().doubleValue();
    //     double diffY= tete.getPositionY().doubleValue() - nourriture.getPositionY().doubleValue();
    //     System.out.println("diffx :"+diffX+"dify :"+diffY+"nourriture "+nourriture.getPositionX()+ nourriture.getPositionY() );
    //     if (diffX < 0) {
    //         return "d"; // Aller vers le bas
    //     } else if (diffX > 0) {
    //         return "q"; // Aller vers le haut
    //     } else {
    //         // Si la tête et la nourriture sont sur la même colonne, décidez de la direction en fonction de la différence en Y
    //         if (diffY < 0) {
    //             return "s"; // Aller vers la droite
    //         } else if (diffY > 0) {
    //             return "z"; // Aller vers la gauche
    //         } else {
    //             // La tête et la nourriture sont au même emplacement (ce cas ne devrait pas se produire normalement)
    //             return genererEntree(); // Générer une entrée aléatoire
    //         }
    //     }
    // }
    

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

public boolean verifierNourritureMangee(Position<U>  positionTete ) {

            for (Position food : jeu.getNourritures()){
               // System.out.println("le food :"+ food.getPositionX() +food.getPositionY() + "le serpent :" + positionTete.getPositionX() + positionTete.getPositionY());
                 if (food.equals(positionTete)) return true ;

            }
             return false ;
            
        }

 private boolean peutFaireDemiTour(DirectionKey directionActuelle, DirectionKey directionVoulue, int tailleSerpent) {
   return (directionActuelle == DirectionKey.UP && directionVoulue == DirectionKey.DOWN && tailleSerpent > 1) ||
          (directionActuelle == DirectionKey.DOWN && directionVoulue == DirectionKey.UP && tailleSerpent > 1) ||
          (directionActuelle == DirectionKey.RIGHT && directionVoulue == DirectionKey.LEFT && tailleSerpent > 1) ||
          (directionActuelle == DirectionKey.LEFT && directionVoulue == DirectionKey.RIGHT && tailleSerpent > 1);
 }

 private boolean traitementDirection(String input, int numerJoueur) {
    DirectionKey direction = DirectionKey.transformer(input);
    //System.out.println(input);
  

        //eviter le demi tour
        Snake<U> serpentActuel = jeu.getPlayer(numerJoueur).getSnake();
        DirectionKey directionActuelle = serpentActuel.getDirection();
        System.out.println("la direction actuelle  :"+directionActuelle.toString()+"la direction voulue :"+direction.toString()+"la taille du serpent :"+ serpentActuel.getLength());

        if (peutFaireDemiTour(directionActuelle, direction, serpentActuel.getLength())) {
         direction = directionActuelle ; // Le serpent ne peut pas faire demi-tour sur lui-même alors il continu dans son ancien direction
         System.out.println("la direction choisie : "+ direction.toString());
        }

    if (direction != null) {
        if (deplacementValid(direction, numerJoueur)) {
            // Enregistrez les anciennes coordonnées
            U oldX = this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX();
            U oldY = this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY();
           
            // Déplacez le serpent en fonction de la direction
            switch (direction) {
                case UP:
                    deplacerSerpent(numerJoueur, decrementerValue(oldX), oldY);
                    //sauvgarder la derniere direction du serpent
                    this.jeu.getPlayer(numerJoueur).getSnake().setDirection(direction);
                    break;
                case DOWN:
                    deplacerSerpent(numerJoueur, incrementValue(oldX), oldY);
                    this.jeu.getPlayer(numerJoueur).getSnake().setDirection(direction);

                    break;
                case RIGHT:
                    deplacerSerpent(numerJoueur, oldX, incrementValue(oldY));
                   this.jeu.getPlayer(numerJoueur).getSnake().setDirection(direction);

                    break;
                case LEFT:
                    deplacerSerpent(numerJoueur, oldX, decrementerValue(oldY));
                    this.jeu.getPlayer(numerJoueur).getSnake().setDirection(direction);

                    break;
                default:
                    System.err.println("Direction inattendue");
                    return true;
            }

            // Mettez à jour le plateau
          //  plateauController.UpdatePlateau(jeu.getPlayers());

            return true;
        } else {
            //game over collision avec le mur
            return false;
        }
    } else {
        System.out.println("Direction inconnue");
        return true;
    }
}

 private void deplacerSerpent(int numerJoueur, U newX, U newY) {
    
    if (verifierNourritureMangee(new Position<>(newX, newY))) {

        this.jeu.getPlayer(numerJoueur).getSnake().addLength(new Position<>(newX, newY));
        this.jeu.getPlateau().setCellType((int) newX, (int) newY, SegmentType.SERPENT);
        Position food = genererUneNourriture(jeu.getPlateau().getLongueur(), jeu.getPlateau().getLargeur());
        jeu.getNourritures().removeIf(nourriture -> nourriture.equals(new Position<>(newX, newY)));
        jeu.getNourritures().add(food);
        this.jeu.getPlateau().setCellType((int) food.getPositionX(), (int) food.getPositionY(), SegmentType.NOURRITURE);

        // Position <U>food = genererUneNourriture(jeu.getPlateau().getLongueur(), jeu.getPlateau().getLargeur());

        // // Vérifiez si la nouvelle position de la nourriture chevauche la position du serpent
        // while (this.jeu.getPlateau().getCellType( food.getPositionX(), food.getPositionY()) == SegmentType.SERPENT) {
        //     // Si c'est le cas, régénérez une nouvelle position pour la nourriture
        //     food = genererUneNourriture(jeu.getPlateau().getLongueur(), jeu.getPlateau().getLargeur());
        // }

        // // Mettez à jour le plateau avec la nouvelle position de la nourriture
        // this.jeu.getPlateau().setCellType((int) food.getPositionX(), (int) food.getPositionY(), SegmentType.NOURRITURE);

    } else {  
        System.out.println("pas de nourriture");
        Position<U> oldPosition = this.jeu.getPlayer(numerJoueur).getSnake().getLast().getPosition();
        this.jeu.getPlateau().setCellType((int) oldPosition.getPositionX(), (int) oldPosition.getPositionY(), SegmentType.VIDE);
        this.jeu.getPlayer(numerJoueur).getSnake().seDeplacer(new Position<>(newX, newY));
        this.jeu.getPlateau().setCellType((int) newX, (int) newY, SegmentType.SERPENT);
    }
    if (this.jeu.getPlayer(numerJoueur).getSnake().detecterAutoCollision()) {
          this.jeu.getPlayer(numerJoueur).setGameOver(true);
         // Vous pouvez ajouter d'autres actions ici, comme afficher un message de fin de jeu
        // ou réinitialiser le jeu.
        return;
    }
    if(detecterCollisionAvecAutreJoueur(numerJoueur)){
      this.jeu.getPlayer(numerJoueur).setGameOver(true);
      return;

    }
 }
 

 public Game getJeu() {
    return jeu;
}

public boolean detecterCollisionAvecAutreJoueur(int numerJoueur) {
    Snake<U> serpentActuel = jeu.getPlayer(numerJoueur).getSnake();
    
    for (Player<U> autreJoueur : jeu.getPlayers()) {
        if (autreJoueur != jeu.getPlayer(numerJoueur)) {
            Snake<U> autreSerpent = autreJoueur.getSnake();
            
            // Vérifiez s'il y a une collision entre les deux serpents
            if (detecterCollisionEntreSerpents(serpentActuel, autreSerpent)) {
                return true; // Collision avec un autre joueur détectée
            }
        }
    }
    
    return false; // Aucune collision avec un autre joueur détectée
}

private boolean detecterCollisionEntreSerpents(Snake<U> serpent1, Snake<U> serpent2) {
    List<Segment<U>> corpsSerpent1 = serpent1.getBody();
    List<Segment<U>> corpsSerpent2 = serpent2.getBody();
    
    for (Segment<U> segment1 : corpsSerpent1) {
        for (Segment<U> segment2 : corpsSerpent2) {
            // Vérifiez s'il y a une collision entre les segments des deux serpents
            if (segment1.getPosition().equals(segment2.getPosition())) {
                return true; // Collision détectée
            }
        }
    }
    
    return false; // Aucune collision détectée
}

 public void handleKeyPress(int numJoueur,KeyCode code) {
    //change la derniere position du serpent
    String input = code.getName();
    // boolean continueGame = true;
   // System.out.println("le code est : "+input);
    Player player = jeu.getPlayer( numJoueur);
  
            // if (input.equalsIgnoreCase("z") || input.equalsIgnoreCase("s") ||
            //         input.equalsIgnoreCase("q") || input.equalsIgnoreCase("d")) {
                //continueGame = traitementDirection(input, jeu.getPlayers().indexOf(player));

                 //eviter le demi tour
                 Snake<U> serpentActuel = player.getSnake();
                 DirectionKey directionActuelle = serpentActuel.getDirection();  
                 DirectionKey direction =DirectionKey.transformer(input);
                  if (peutFaireDemiTour(directionActuelle, direction, serpentActuel.getLength())) {
     
                  ((HumanPlayer)player).getSnake().setDirection(directionActuelle);

                 } else {

                    ((HumanPlayer)player).getSnake().setDirection(direction);;

                 }

                // ((HumanPlayer)player).getSnake().setDirection(DirectionKey.transformer(input));;
              //  System.out.println("la nouvelle direction apres pressed /"+player.getSnake().getDirection().toString());
            // } else {
            //     System.out.println("Direction inconnue");
            //     continueGame = true;
            // }
        // } else {
        //     // AiPlayer
        //     String aiInput = genererEntree();
        //     continueGame = traitementDirection(aiInput, jeu.getPlayers().indexOf(player));
        // }
    
        // if (!continueGame) {
        //     break;  
        // }   
}

public void handleKeyPressByPlayer(KeyCode code) {
    // Pour le joueur 1
    if (code.equals(KeyCode.Z) || code.equals(KeyCode.Q) ||
        code.equals(KeyCode.S) || code.equals(KeyCode.D)) {
        handleKeyPress(0, code);
    }
    
    else {// Pour le joueur 2
        if (code.equals(KeyCode.UP) || code.equals(KeyCode.DOWN) ||
            code.equals(KeyCode.LEFT) || code.equals(KeyCode.RIGHT)) {
            handleKeyPress(1, code);
        }
        else 
        {
        System.out.println("Direction inconnue");
        }
    }

 }


}
