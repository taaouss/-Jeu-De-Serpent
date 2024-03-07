package poo.controller;

import java.util.Random;
import java.util.Scanner;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;
import poo.modele.*;


/**
 * Le contrôleur de jeu (GameController) gère la logique du jeu, y compris la génération de nourriture.
 * Il est responsable de l'interaction entre les joueurs, le plateau de jeu et les éléments du jeu.
 *
 * @param <U> Le type numérique utilisé pour les coordonnées du jeu.
 */
public class GameController<U extends Number> {
    private Game<U> jeu;
    private boolean update =false ;
    private int iterations = 5 + (int)(Math.random() * 21); //pour IA
  
       /**
     * Constructeur de la classe GameController.
     *
     * @param game Le jeu à associer au contrôleur.
     */
    public GameController(Game game) {
        this.jeu = game;
    }
    
  
    public void gameInit() {

        for (Player<U> player : jeu.getPlayers()) {

            jeu.getPlateau().setCellType((int) player.getSnake().getHead().getPosition().getPositionX(),
                    (int) player.getSnake().getHead().getPosition().getPositionY(), SegmentType.SERPENT);
        }

    }

            /******** GENERATION DE NOURRITURE ******/


    /**
     * Génère de la nourriture dans le jeu en respectant les règles spécifiées.
     *
     * @param longueur Longueur du plateau de jeu.
     * @param largeur Largeur du plateau de jeu.
     * @return Une liste de positions représentant la nourriture générée.
     */
    public List<Position<U>> genererNourriture(int longueur, int largeur) {
        List<Position<U>> nourriture;

        for (int i = 0; i < jeu.getNombreNourriture(); i++) {
            jeu.getNourritures().add(genererUneNourriture(longueur, largeur));
        }

        // Vérifie si le jeu doit avoir une règle spéciale, comme le bouclier
        if (this.jeu.isAjouterRegles()) {
            jeu.getNourritures().add(genererUneNourriture(longueur, largeur));
            jeu.getNourritures().add(genererUneNourriture(longueur, largeur));
        }

        nourriture = new ArrayList<>(this.jeu.getNourritures());
        return nourriture;
    }

    /**
     * Génère une seule unité de nourriture dans le jeu.
     *
     * @param longueur Longueur du plateau de jeu.
     * @param largeur Largeur du plateau de jeu.
     * @return La position de la nourriture générée.
     */
    public Position<U> genererUneNourriture(int longueur, int largeur) {
        Random random = new Random();
        Position<U> nouvelleNourriture;
        U x, y;

        do {
            x = genererCoordonnee(random, longueur);
            y = genererCoordonnee(random, largeur);
            nouvelleNourriture = new Position<>(x, y);
        } while (positionOccupeeParSerpent(nouvelleNourriture) || positionOccupeeParNourriture(nouvelleNourriture));

        return new Position<>(x, y);
    }
   
    private U genererCoordonnee(Random random, int maxValue) {
        return (U) Integer.valueOf(random.nextInt(maxValue));
    }

    /**
 * Vérifie si la position spécifiée est occupée par de la nourriture dans le jeu.
 *
 * @param position La position à vérifier.
 * @return {@code true} si la position est occupée par de la nourriture, sinon {@code false}.
 */
public boolean positionOccupeeParNourriture(Position<U> position) {
    for (Position<U> nourriturePosition : jeu.getNourritures()) {
        if (nourriturePosition.equals(position)) {
            return true; // La position est occupée par de la nourriture
        }
    }
    return false; // La position n'est pas occupée par de la nourriture
}

   /**
 * Génère une entrée aléatoire sous forme de lettre (z, s, q, d) à des fins de jeu.
 *
 * @return Une lettre représentant une direction aléatoire (z pour haut, s pour bas, q pour gauche, d pour droite).
 * @throws IllegalStateException Si la valeur générée n'est pas dans la plage attendue.
 */
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
 
/**
 * Vérifie si un déplacement dans une direction spécifiée est valide pour un joueur donné.
 *
 * @param direction   La direction du déplacement à vérifier.
 * @param numerJoueur Le numéro du joueur pour lequel vérifier le déplacement.
 * @return {@code true} si le déplacement est valide, {@code false} sinon.
 */ 
  private boolean deplacementValid(DirectionKey direction, int numerJoueur) {
        
        switch (direction) {
            case UP:

                if (jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY().doubleValue() <= 0)
                    return false;
                else
                    return true;
            case DOWN:
                if ( jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY().doubleValue()
                        + 1 >= (double) jeu.getPlateau()
                                .getLongueur())
                    return false;
                else
                    return true;
            case RIGHT:
                if ( jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX().doubleValue()
                        + 1 >= (double) jeu.getPlateau()
                                .getLargeur())
                    return false;
                else
                    return true;
            case LEFT:
                if ( jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX().doubleValue() <= 0)
                    return false;
                else
                    return true;

            default:
                System.err.println("erreur");
                return false;
        }

    }


    /**************** Manipulation de Position *******************/

  private U incrementValue(U value) {
        if (value instanceof Integer) {
            return (U) Integer.valueOf(value.intValue() + 1);
        } else if (value instanceof Double) {
            return (U) Double.valueOf(value.doubleValue() + 1);
        }
        return value;
    }

  private U incrementValueWithPas(U value,double pas) {
        return (U) Double.valueOf(value.doubleValue() + pas);
    }
 
  private U decrementValuewithPas(U value,double pas) {
        return (U) Double.valueOf(value.doubleValue() - pas);
    }
 
  private U decrementerValue(U value) {
        if (value instanceof Integer) {
            return (U) Integer.valueOf(value.intValue() - 1);
        } else if (value instanceof Double) {
            return (U) Double.valueOf(value.doubleValue() - 1);
        }
        return value;
    }


    /******************* LOGIQUE DU JEU *****************************/

    /**
 * Gère le déroulement du jeu en traitant les entrées des joueurs et en mettant à jour l'état du jeu.
 *
 * @param plateauController Le contrôleur du plateau de jeu.
 */
 public void jouer(BoardGameController plateauController) {
       // Variable pour stocker l'entrée utilisateur ou l'entrée générée par l'IA
        String input;
        // Flag pour indiquer si une mise à jour du jeu a eu lieu
        update =false;
        try  {
            // Parcours de tous les joueurs du jeu
            for (Player<U> player : jeu.getPlayers()) {
             // Vérification si le jeu est en cours
                 if(!jeu.isFin()){
              // Vérification si le joueur n'a pas perdu
                  if(!player.isGameOver()){
                  
                    // Traitement du joueur humain
                    if (player instanceof HumanPlayer) {
                        
                        if(this.jeu.isSurTerminal())  {
                            //on attend l'entrée de l'utilisateur
                            Scanner scanner = new Scanner(System.in);
                            input = scanner.nextLine();
                            
                        }
                        else{
                            //le faire bouger de la derniere position
                            Snake<U> serpentActuel = jeu.getPlayer(jeu.getPlayers().indexOf(player)).getSnake();
                            DirectionKey directionActuelle = serpentActuel.getDirection();
                            input = directionActuelle.getLetter(directionActuelle) ;
                        }
                       
                    } else {
                        // AiPlayer
                       if (iterations == 0) {
                        // AiPlayer
                        input = genererEntree();
                        iterations = 5 + (int)(Math.random() * 21);
                        }else{
                            Snake<U> serpentActuel = jeu.getPlayer(jeu.getPlayers().indexOf(player)).getSnake();
                            DirectionKey directionActuelle = serpentActuel.getDirection();
                            input = directionActuelle.getLetter(directionActuelle) ;
                            iterations--;
                        }


                    }
                   
                    // Vérification de l'entrée "w" pour terminer le programme
                     if (input.equalsIgnoreCase("w")) {
                        System.out.println("Fin du programme.");
                        break;
                    }
                  
                    // Traitement de la direction et mise à jour du jeu   
                    if (!traitementDirection(input, jeu.getPlayers().indexOf(player))) {
                        jeu.getPlayer(jeu.getPlayers().indexOf(player)).setGameOver(true);
                        break;
                    }
                    
                    // Mise à jour du plateau si le jeu est en mode terminal
                     if (this.jeu.isSurTerminal()){
                        
                        plateauController.setPlateau(jeu.getPlateau());
                        plateauController.UpdatePlateau(jeu.getPlayers());
                     
                    }
                   
                }else {
                // Affichage du message de fin du jeu si le joueur a perdu
                    if(jeu.isSurTerminal()) System.out.println("Game Over");                    
                    jeu.setFin(true);
                }

                 }
              
                }

            }
         catch (Exception e) {
         System.out.println("erreur" + e);
        }
        }
/**
 * Vérifie si la nourriture a été mangée par la tête du serpent.
 *
 * @param positionTete La position de la tête du serpent.
 * @return true si la nourriture a été mangée, sinon false.
 */
 public boolean verifierNourritureMangee(Position<U>  positionTete ) {

            for (Position food : jeu.getNourritures()){
               if (! jeu.isDeplacementFluide()) { if (food.equals(positionTete)) return true ;}  
               else{ 
                    if (food.equals(new Position<>((U) Integer.valueOf((int) Math.round((double) positionTete.getPositionX())),(U) Integer.valueOf((int) Math.round((double)positionTete.getPositionY()))))) return true ;
                }
            }
             return false ;
            
        }
/**
 * Vérifie si le serpent peut faire demi-tour sans collision avec son propre corps.
 *
 * @param directionActuelle La direction actuelle du serpent.
 * @param directionVoulue   La direction souhaitée du serpent.
 * @param tailleSerpent     La taille actuelle du serpent.
 * @return true si le serpent peut faire demi-tour, sinon false.
 */
 private boolean peutFaireDemiTour(DirectionKey directionActuelle, DirectionKey directionVoulue, int tailleSerpent) {
   return (directionActuelle == DirectionKey.UP && directionVoulue == DirectionKey.DOWN && tailleSerpent > 1) ||
          (directionActuelle == DirectionKey.DOWN && directionVoulue == DirectionKey.UP && tailleSerpent > 1) ||
          (directionActuelle == DirectionKey.RIGHT && directionVoulue == DirectionKey.LEFT && tailleSerpent > 1) ||
          (directionActuelle == DirectionKey.LEFT && directionVoulue == DirectionKey.RIGHT && tailleSerpent > 1);
 }



 /**
 * Traite la direction fournie et déplace le serpent en conséquence.
 *
 * @param input        La direction fournie en tant qu'entrée utilisateur.
 * @param numerJoueur  Le numéro du joueur concerné.
 * @return true si le déplacement est réussi, sinon false.
 */
 private boolean traitementDirection(String input, int numerJoueur) {
    DirectionKey direction = DirectionKey.transformer(input);

    if (direction != null) {
        Snake<U> serpentActuel = jeu.getPlayer(numerJoueur).getSnake();
        double tempsEcoule = serpentActuel.getDeltaTime();
        double vitesse = serpentActuel.getSpeed();
        double pas = 0.1 + tempsEcoule * vitesse;
        DirectionKey directionActuelle = serpentActuel.getDirection();

        if (peutFaireDemiTour(directionActuelle, direction, serpentActuel.getLength())) {
            direction = directionActuelle;
        }

        if (deplacerSerpentSelonDirection(direction, numerJoueur, pas)) {
            return true;
        } else if (!jeu.isSansRebords()) {
            return false;
        } else {
            deplacerSerpentSansRebords(direction, numerJoueur);
            return true;
        }
    } else {
        System.out.println("Direction inconnue");
        return true;
    }
}

/**
 * Déplace le serpent du joueur dans la direction spécifiée.
 *
 * @param direction   La direction dans laquelle déplacer le serpent.
 * @param numerJoueur L'indice du joueur dont le serpent doit être déplacé.
 * @param pas         La distance de déplacement.
 * @return true si le déplacement est réussi, false sinon.
 */
private boolean deplacerSerpentSelonDirection(DirectionKey direction, int numerJoueur, double pas) {
    U oldX = this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX();
    U oldY = this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY();

    if (deplacementValid(direction, numerJoueur)) {
        switch (direction) {
            case UP:
                deplacerSerpent(numerJoueur, oldX, jeu.isDeplacementFluide() ? decrementValuewithPas(oldY, pas) : decrementerValue(oldY));
                break;
            case DOWN:
                deplacerSerpent(numerJoueur, oldX, jeu.isDeplacementFluide() ? incrementValueWithPas(oldY, pas) : incrementValue(oldY));
                break;
            case RIGHT:
                deplacerSerpent(numerJoueur, jeu.isDeplacementFluide() ? incrementValueWithPas(oldX, pas) : incrementValue(oldX), oldY);
                break;
            case LEFT:
                deplacerSerpent(numerJoueur, jeu.isDeplacementFluide() ? decrementValuewithPas(oldX, pas) : decrementerValue(oldX), oldY);
                break;
            default:
                System.err.println("Direction inattendue");
                return false;
        }
        this.jeu.getPlayer(numerJoueur).getSnake().setDirection(direction);
        return true;
    }
    return false;
}

/**
 * Déplace le serpent du joueur dans la direction spécifiée, sans tenir compte des rebords.
 *
 * @param direction   La direction dans laquelle déplacer le serpent.
 * @param numerJoueur L'indice du joueur dont le serpent doit être déplacé.
 */
private void deplacerSerpentSansRebords(DirectionKey direction, int numerJoueur) {
    U oldX = this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionX();
    U oldY = this.jeu.getPlayer(numerJoueur).getSnake().getHead().getPosition().getPositionY();

    switch (direction) {
        case UP:
            deplacerSerpent(numerJoueur, oldX, jeu.isDeplacementFluide() ? decrementValuewithPas(jeu.getPlateau().getLongueurU(), 1) : decrementerValue(jeu.getPlateau().getLongueurU()));
            break;
        case DOWN:
            deplacerSerpent(numerJoueur, oldX, jeu.isDeplacementFluide() ? (U) Double.valueOf(0) : (U) Integer.valueOf(0));
            break;
        case RIGHT:
            deplacerSerpent(numerJoueur, jeu.isDeplacementFluide() ? (U) Double.valueOf(0) : (U) Integer.valueOf(0), oldY);
            break;
        case LEFT:
            deplacerSerpent(numerJoueur, jeu.isDeplacementFluide() ? decrementValuewithPas(jeu.getPlateau().getLargeurU(), 1) : decrementerValue(jeu.getPlateau().getLargeurU()), oldY);
            break;
        default:
            System.err.println("Direction inattendue");
    }
}

/**
 * Déplace le serpent du joueur vers une nouvelle position.
 *
 * @param numerJoueur Le numéro du joueur concerné.
 * @param newX        La nouvelle position en X du serpent.
 * @param newY        La nouvelle position en Y du serpent.
 */
private void deplacerSerpent(int numerJoueur, U newX, U newY) {
    // Vérifie si le serpent a mangé de la nourriture à la nouvelle position
    if (verifierNourritureMangee(new Position<>(newX, newY))) {
        // Traite la nourriture mangée par le serpent
        traiterNourritureMangee(numerJoueur, newX, newY);
    } else {
        // Déplace le serpent sans nourriture à la nouvelle position
        deplacerSerpentSansNourriture(numerJoueur, newX, newY);
    }

    // Détecte les collisions avec d'autres objets du jeu
    detecterCollisions(numerJoueur);

    // Temporisation pour réguler la fréquence de la boucle du jeu
    try {
        Thread.sleep(50);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Met à jour le statut du jeu
   if(jeu.isDeplacementFluide())  update = true;
}


/**
 * Traite les conséquences de la nourriture mangée par le serpent du joueur.
 *
 * @param numerJoueur Le numéro du joueur concerné.
 * @param newX        La nouvelle position en X du serpent après avoir mangé la nourriture.
 * @param newY        La nouvelle position en Y du serpent après avoir mangé la nourriture.
 */
private void traiterNourritureMangee(int numerJoueur, U newX, U newY) {
    // Crée une nouvelle position à partir des coordonnées fournies
    Position pos = new Position<>(newX, newY);

    // Obtient l'index de la dernière nourriture dans la liste
    int lastIdx = jeu.getNourritures().size() - 1;

    // Si le déplacement est fluide, arrondit les coordonnées à des entiers
    if (jeu.isDeplacementFluide()) {
        pos = new Position<>((U) Integer.valueOf((int) Math.round((double) pos.getPositionX())),
                             (U) Integer.valueOf((int) Math.round((double) pos.getPositionY())));
    }

    // Vérifie si la dernière nourriture correspond à la position actuelle
    if (jeu.getNourritures().get(lastIdx).equals(pos) && this.jeu.isAjouterRegles()) {
        // Traite le cas où le serpent a mangé une nourriture spéciale (bouclier)
        traiterBouclierMange(numerJoueur, newX, newY, lastIdx);
    } else {
        // Vérifie si l'avant-dernière nourriture correspond à la position actuelle
        if (jeu.getNourritures().get(lastIdx - 1).equals(pos) && this.jeu.isAjouterRegles()) {
            // Traite le cas où le serpent a mangé une autre nourriture spéciale (vitesse)
            traiterVitesseMange(numerJoueur, newX, newY, lastIdx - 1);
        } else {
            // Traite le cas où le serpent a mangé une nourriture normale
            traiterNourritureNormale(numerJoueur, newX, newY);
        }
    }
}


/**
 * Traite les conséquences de la nourriture de vitesse mangée par le serpent du joueur.
 *
 * @param numerJoueur Le numéro du joueur concerné.
 * @param newX        La nouvelle position en X du serpent après avoir mangé la nourriture de vitesse.
 * @param newY        La nouvelle position en Y du serpent après avoir mangé la nourriture de vitesse.
 * @param lastIdx     L'index de la dernière nourriture mangée dans la liste des nourritures.
 */
private void traiterVitesseMange(int numerJoueur, U newX, U newY, int lastIdx) {
    // Incrémente la vitesse du serpent du joueur
    jeu.getPlayer(numerJoueur).getSnake().setSpeed(jeu.getPlayer(numerJoueur).getSnake().getSpeed() + 1);

    // Obtient l'ancienne position du serpent
    Position<U> oldPosition = this.jeu.getPlayer(numerJoueur).getSnake().getLast().getPosition();

    // Si le jeu se déroule sur un terminal, met à jour la cellule de l'ancienne position du serpent
    if (this.jeu.isSurTerminal()) {
        this.jeu.getPlateau().setCellType((int) oldPosition.getPositionX(), (int) oldPosition.getPositionY(), SegmentType.VIDE);
    }

    // Génère une nouvelle position pour la nourriture de vitesse
    Position vitesse = genererUneNourriture(jeu.getPlateau().getLongueur(), jeu.getPlateau().getLargeur());

    // Supprime la nourriture de vitesse actuelle de la liste des nourritures
    if (!jeu.isDeplacementFluide()) {
        jeu.getNourritures().removeIf(nourriture -> nourriture.equals(new Position<>(newX, newY)));
    } else {
        jeu.getNourritures().removeIf(nourriture -> nourriture.equals(new Position<>((U) Integer.valueOf((int) Math.round((double) newX)),
                                                                                      (U) Integer.valueOf((int) Math.round((double) newY)))));
    }

    // Ajoute la nouvelle nourriture de vitesse à la position de la dernière nourriture mangée
    jeu.getNourritures().add(lastIdx, vitesse);

    // Déplace le serpent vers la nouvelle position après avoir mangé la nourriture de vitesse
    this.jeu.getPlayer(numerJoueur).getSnake().seDeplacer(new Position<>(newX, newY));

    // Si le jeu se déroule sur un terminal, met à jour la cellule de la nouvelle position du serpent
    if (this.jeu.isSurTerminal()) {
        this.jeu.getPlateau().setCellType((int) newX, (int) newY, SegmentType.SERPENT);
    }
}

/**
 * Traite les conséquences de la nourriture de bouclier mangée par le serpent du joueur.
 *
 * @param numerJoueur Le numéro du joueur concerné.
 * @param newX        La nouvelle position en X du serpent après avoir mangé la nourriture de bouclier.
 * @param newY        La nouvelle position en Y du serpent après avoir mangé la nourriture de bouclier.
 * @param lastIdx     L'index de la dernière nourriture mangée dans la liste des nourritures.
 */
private void traiterBouclierMange(int numerJoueur, U newX, U newY, int lastIdx) {
    // Active le bouclier du serpent du joueur
    jeu.getPlayer(numerJoueur).getSnake().setBouclier(true);

    // Obtient l'ancienne position du serpent
    Position<U> oldPosition = this.jeu.getPlayer(numerJoueur).getSnake().getLast().getPosition();

    // Si le jeu se déroule sur un terminal, met à jour la cellule de l'ancienne position du serpent
    if (this.jeu.isSurTerminal()) {
        this.jeu.getPlateau().setCellType((int) oldPosition.getPositionX(), (int) oldPosition.getPositionY(), SegmentType.VIDE);
    }

    // Génère une nouvelle position pour la nourriture de bouclier
    Position bouclier = genererUneNourriture(jeu.getPlateau().getLongueur(), jeu.getPlateau().getLargeur());

    // Supprime la nourriture de bouclier actuelle de la liste des nourritures
    if (!jeu.isDeplacementFluide()) {
        jeu.getNourritures().removeIf(nourriture -> nourriture.equals(new Position<>(newX, newY)));
    } else {
        jeu.getNourritures().removeIf(nourriture -> nourriture.equals(new Position<>((U) Integer.valueOf((int) Math.round((double) newX)),
                                                                                      (U) Integer.valueOf((int) Math.round((double) newY)))));
    }

    // Ajoute la nouvelle nourriture de bouclier à la position de la dernière nourriture mangée
    jeu.getNourritures().add(lastIdx, bouclier);

    // Déplace le serpent vers la nouvelle position après avoir mangé la nourriture de bouclier
    this.jeu.getPlayer(numerJoueur).getSnake().seDeplacer(new Position<>(newX, newY));

    // Si le jeu se déroule sur un terminal, met à jour la cellule de la nouvelle position du serpent
    if (this.jeu.isSurTerminal()) {
        this.jeu.getPlateau().setCellType((int) newX, (int) newY, SegmentType.SERPENT);
    }
}

/**
 * Traite les conséquences de la nourriture normale mangée par le serpent du joueur.
 *
 * @param numerJoueur Le numéro du joueur concerné.
 * @param newX        La nouvelle position en X du serpent après avoir mangé la nourriture normale.
 * @param newY        La nouvelle position en Y du serpent après avoir mangé la nourriture normale.
 */
private void traiterNourritureNormale(int numerJoueur, U newX, U newY) {
    // Augmente la longueur du serpent du joueur
    this.jeu.getPlayer(numerJoueur).getSnake().addLength(new Position<>(newX, newY));

    // Si le jeu se déroule sur un terminal, met à jour la cellule de la nouvelle position du serpent
    if (this.jeu.isSurTerminal()) {
        this.jeu.getPlateau().setCellType((int) newX, (int) newY, SegmentType.SERPENT);
    }

    // Génère une nouvelle position pour la prochaine nourriture normale
    Position food = genererUneNourriture(jeu.getPlateau().getLongueur(), jeu.getPlateau().getLargeur());

    // Supprime la nourriture normale actuelle de la liste des nourritures
    if (!jeu.isDeplacementFluide()) {
        jeu.getNourritures().removeIf(nourriture -> nourriture.equals(new Position<>(newX, newY)));
    } else {
        jeu.getNourritures().removeIf(nourriture -> nourriture.equals(new Position<>((U) Integer.valueOf((int) Math.round((double) newX)),
                                                                                      (U) Integer.valueOf((int) Math.round((double) newY)))));
    }

    // Ajoute la nouvelle nourriture normale à la position initiale du serpent
    jeu.getNourritures().add(0, food);

    // Si le jeu se déroule sur un terminal, met à jour la cellule de la nouvelle nourriture normale
    if (this.jeu.isSurTerminal()) {
        this.jeu.getPlateau().setCellType((int) food.getPositionX(), (int) food.getPositionY(), SegmentType.NOURRITURE);
    }
}


/**
 * Déplace le serpent du joueur vers une nouvelle position sans manger de nourriture.
 *
 * @param numerJoueur Le numéro du joueur dont le serpent doit être déplacé.
 * @param newX        La nouvelle position en X du serpent.
 * @param newY        La nouvelle position en Y du serpent.
 */
private void deplacerSerpentSansNourriture(int numerJoueur, U newX, U newY) {
    // Récupère la position actuelle de la tête du serpent
    Position<U> oldPosition = this.jeu.getPlayer(numerJoueur).getSnake().getLast().getPosition();

    // Si le jeu se déroule sur un terminal, met à jour la cellule de la position actuelle du serpent à VIDE
    if (this.jeu.isSurTerminal()) {
        this.jeu.getPlateau().setCellType((int) oldPosition.getPositionX(), (int) oldPosition.getPositionY(), SegmentType.VIDE);
    }

    // Déplace le serpent vers la nouvelle position
    this.jeu.getPlayer(numerJoueur).getSnake().seDeplacer(new Position<>(newX, newY));

    // Si le jeu se déroule sur un terminal, met à jour la cellule de la nouvelle position du serpent à SERPENT
    if (this.jeu.isSurTerminal()) {
        this.jeu.getPlateau().setCellType((int) newX, (int) newY, SegmentType.SERPENT);
    }
}

/**
 * Détecte les collisions du serpent du joueur avec d'autres objets ou serpents.
 * En cas de collision avec un autre joueur, vérifie également si le joueur possède un bouclier.
 * Si le joueur n'a pas de bouclier, le jeu le considère comme "Game Over".
 *
 * @param numerJoueur Le numéro du joueur dont le serpent doit être vérifié pour les collisions.
 */
private void detecterCollisions(int numerJoueur) {
    if (detecterCollisionAvecAutreJoueur(numerJoueur) || this.jeu.getPlayer(numerJoueur).getSnake().detecterAutoCollision()) {
        if (!jeu.getPlayer(numerJoueur).getSnake().isBouclier()) {
            this.jeu.getPlayer(numerJoueur).setGameOver(true);
            System.out.println("Le joueur a perdu.");
        } else {
            jeu.getPlayer(numerJoueur).getSnake().setBouclier(false);
        }
    }
}

/**
 * Vérifie s'il y a une collision entre le serpent d'un joueur et le serpent d'un autre joueur.
 *
 * @param numerJoueur Le numéro du joueur dont le serpent doit être vérifié pour la collision avec d'autres joueurs.
 * @return true s'il y a une collision avec un autre joueur, false sinon.
 */
public boolean detecterCollisionAvecAutreJoueur(int numerJoueur) {
    Snake<U> serpentActuel = jeu.getPlayer(numerJoueur).getSnake();

    for (Player<U> autreJoueur : jeu.getPlayers()) {
        if (autreJoueur != jeu.getPlayer(numerJoueur)) {
            Snake<U> autreSerpent = autreJoueur.getSnake();

            // Vérifiez s'il y a une collision entre les deux serpents
            if (serpentActuel.detecterCollisionEntreSerpents(autreSerpent)) {
                return true; // Collision avec un autre joueur détectée
            }
        }
    }

    return false; // Aucune collision avec un autre joueur détectée
}

/**
 * Vérifie si la position donnée est occupée par un serpent d'un des joueurs.
 *
 * @param position La position à vérifier.
 * @return true si la position est occupée par un serpent, false sinon.
 */
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


/***************** LOGIQUE POUR L'INTERFACE GRAPHIQUE ************************/
/**
 * Gère l'appui sur une touche du clavier par un joueur, mettant à jour la direction du serpent.
 *
 * @param numJoueur Le numéro du joueur dont le serpent doit être mis à jour en fonction de la touche appuyée.
 * @param code      Le code de la touche appuyée.
 */
public void handleKeyPress(int numJoueur, KeyCode code) {
    // Change la dernière position du serpent
    String input = code.getName();
    Player player = jeu.getPlayer(numJoueur);

    // Éviter le demi-tour
    Snake<U> serpentActuel = player.getSnake();
    DirectionKey directionActuelle = serpentActuel.getDirection();
    DirectionKey direction = DirectionKey.transformer(input);

    if (peutFaireDemiTour(directionActuelle, direction, serpentActuel.getLength())) {
        ((HumanPlayer) player).getSnake().setDirection(directionActuelle);
    } else {
        ((HumanPlayer) player).getSnake().setDirection(direction);
    }
}

/**
 * Gère l'appui sur une touche du clavier par un joueur, en appelant la méthode handleKeyPress
 * pour mettre à jour la direction du serpent correspondant au joueur concerné.
 *
 * @param code Le code de la touche appuyée.
 */
public void handleKeyPressByPlayer(KeyCode code) {
    // Pour le joueur 1
    if (code.equals(KeyCode.Z) || code.equals(KeyCode.Q) ||
        code.equals(KeyCode.S) || code.equals(KeyCode.D)) {
        handleKeyPress(0, code);
    } else {// Pour le joueur 2
        if (code.equals(KeyCode.UP) || code.equals(KeyCode.DOWN) ||
            code.equals(KeyCode.LEFT) || code.equals(KeyCode.RIGHT)) {
            if (jeu.isDeuxJoueursAuClavier()) handleKeyPress(1, code);
        } else {
            System.out.println("Direction inconnue");
        }
    }
}



 /************ SETTEURS GETTEURS ************************/


/**
 * Indique si une mise à jour est nécessaire.
 *
 * @return true si une mise à jour est nécessaire, false sinon.
 */
public boolean isUpdate() {
    return update;
}

/**
 * Définit si une mise à jour est nécessaire.
 *
 * @param b La valeur à définir pour l'indicateur de mise à jour.
 */
public void setUpdate(boolean b) {
    update = b;
}

/**
 * Récupère le joueur avec l'indice spécifié.
 *
 * @param i L'indice du joueur à récupérer.
 * @return Le joueur correspondant à l'indice spécifié.
 */
public Player getJoueur(int i) {
    return jeu.getPlayer(i);
}

/**
 * Récupère l'instance de jeu associée à ce contrôleur.
 *
 * @return L'instance de jeu associée à ce contrôleur.
 */
public Game getJeu() {
    return jeu;
}

}


