package poo;

import java.util.Scanner;

import poo.modele.AiPlayer;
import poo.modele.BoardGame;
import poo.modele.Game;
import poo.modele.HumanPlayer;
import poo.modele.Player;

/**
 * La classe GestionGame gère la configuration et la création d'une instance de Game en fonction de l'entrée de l'utilisateur.
 */
public class GestionGame {

    /**
     * Obtient les paramètres du jeu à partir de l'utilisateur et crée une instance de Game.
     *
     * @return L'instance de Game configurée.
     */
    static Game getGameParamsFromUser() {

        // Scanner pour la saisie de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez les paramètres du jeu :");

        // Demander la largeur du plateau de jeu
        System.out.print("Largeur du plateau : ");
        int plateauLargeur = scanner.nextInt();

        // Demander la longueur du plateau de jeu
        System.out.print("Longueur du plateau : ");
        int plateauLongueur = scanner.nextInt();

        // Demander si le plateau de jeu doit avoir des rebords
        System.out.print("Voulez-vous un plateau sans rebords ? (true/false) : ");
        boolean sansRebords = scanner.nextBoolean();

        // Demander si une interface utilisateur graphique doit être utilisée
        System.out.print("Voulez-vous une interface graphique ? (true/false) : ");
        boolean utiliserGUI = scanner.nextBoolean();

        // Initialisation des autres paramètres
        int nombreDeJoueurs = 1;
        boolean aDeuxiemeJoueur = false;
        boolean deplacementFluide = false;
        boolean reglesPersonnalisees = false;
        Boolean joueurIA = false;

        // Vérifier si une interface utilisateur graphique est utilisée
        if (utiliserGUI) {
            // Demander le nombre de joueurs (1 ou 2) utilisant le clavier
            System.out.print("Nombre de joueurs au clavier (1 / 2) : ");
            nombreDeJoueurs = scanner.nextInt();

            // Demander si l'utilisateur veut un deuxième joueur
            if (nombreDeJoueurs == 2) {
                aDeuxiemeJoueur = true;
                System.out.print("Le deuxième joueur est-il une IA (true/false) : ");
                joueurIA = scanner.nextBoolean();
            }

            // Demander si l'utilisateur veut un déplacement fluide
            System.out.print("Voulez-vous un déplacement fluide ? (true/false) : ");
            deplacementFluide = scanner.nextBoolean();

            // Demander si l'utilisateur veut des règles personnalisées
            System.out.print("Voulez-vous des règles personnalisées ? (true/false) : ");
            reglesPersonnalisees = scanner.nextBoolean();
        }

        // Créer une instance du plateau de jeu en utilisant les paramètres obtenus
        BoardGame plateauJeu = new BoardGame<>(plateauLargeur, plateauLongueur);

        // Créer des instances de joueur humain
        HumanPlayer joueur = new HumanPlayer<Number>("joueur_1", 3.0, 2.0);
        HumanPlayer joueur1 = new HumanPlayer<Number>("joueur_1", 3, 2);

        // Créer une instance de Game en utilisant les paramètres obtenus
        Game jeu;
        if (deplacementFluide) {
            jeu = Game.getInstance(joueur, plateauJeu, 3, aDeuxiemeJoueur, deplacementFluide, sansRebords, reglesPersonnalisees, !utiliserGUI);
        } else {
            jeu = Game.getInstance(joueur1, plateauJeu, 3, aDeuxiemeJoueur, deplacementFluide, sansRebords, reglesPersonnalisees, !utiliserGUI);
        }

        // Ajouter un deuxième joueur si nécessaire
        Player joueur2;
        if (aDeuxiemeJoueur) {
            if (!joueurIA) {
                joueur2 = new HumanPlayer<Number>("joueur_2", 0.0, 0.0);
            } else {
                joueur2 = new AiPlayer<Number>("joueur_2", 0.0, 0.0);
            }
            jeu.addPlayer(joueur2);
        }

        // Renvoyer l'instance Game créée
        return jeu;
    }
}
