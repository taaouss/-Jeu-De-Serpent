package poo.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Game représente l'état et la logique d'un jeu.
 *
 * @param <U> Type de données pour les coordonnées du plateau.
 */
public class Game<U extends Number> {

    private static Game instance;
    private List<Position<U>> nourritures = new ArrayList<>();
    private int nombreNourriture;
    private List<Player<U>> players = new ArrayList<>();
    private BoardGame<U> plateau;
    private boolean fin = false;

    // Paramètres de configuration du jeu
    private boolean sansRebords;
    private boolean deuxJoueursAuClavier;
    private boolean ajouterRegles;
    private boolean surTerminal;
    private boolean deplacementFluide;

    private Game(Player<U> player, BoardGame<U> plateau, int nombreNourriture, boolean deuxJoueursAuClavier,
                 boolean deplacementFluide, boolean sansRebords, boolean ajouterRegles, boolean surTerminal) {

        this.nombreNourriture = nombreNourriture;
        players.add(player);
        this.plateau = plateau;
        this.ajouterRegles = ajouterRegles;
        this.sansRebords = sansRebords;
        this.deuxJoueursAuClavier = deuxJoueursAuClavier;
        this.surTerminal = surTerminal;
        this.deplacementFluide = deplacementFluide;
    }

    /**
     * Obtient une instance unique de la classe Game en utilisant le modèle Singleton.
     *
     * @param player              Le premier joueur du jeu.
     * @param plateau             Le plateau de jeu.
     * @param nombreNourriture    Le nombre initial de nourritures sur le plateau.
     * @param deuxJoueursAuClavier Indique s'il y a deux joueurs utilisant le clavier.
     * @param deplacementFluide   Indique s'il y a un déplacement fluide des serpents.
     * @param sansRebords         Indique si le plateau de jeu est sans rebords.
     * @param ajouterRegles       Indique si des règles personnalisées doivent être ajoutées.
     * @param surTerminal         Indique si le jeu est exécuté sur un terminal.
     * @return L'instance de Game créée ou existante.
     */
    public static synchronized Game getInstance(Player player, BoardGame plateau, int nombreNourriture,
                                                boolean deuxJoueursAuClavier, boolean deplacementFluide,
                                                boolean sansRebords, boolean ajouterRegles, boolean surTerminal) {
        if (instance == null) {
            instance = new Game<>(player, plateau, nombreNourriture, deuxJoueursAuClavier,
                    deplacementFluide, sansRebords, ajouterRegles, surTerminal);
        }
        return instance;
    }

    // Méthodes d'accès aux paramètres de configuration du jeu

    public boolean isSansRebords() {
        return sansRebords;
    }

    public void setSansRebords(boolean sansRebords) {
        this.sansRebords = sansRebords;
    }

    public boolean isDeuxJoueursAuClavier() {
        return deuxJoueursAuClavier;
    }

    public void setDeuxJoueursAuClavier(boolean deuxJoueursAuClavier) {
        this.deuxJoueursAuClavier = deuxJoueursAuClavier;
    }

    public boolean isAjouterRegles() {
        return ajouterRegles;
    }

    public void setAjouterRegles(boolean ajouterRegles) {
        this.ajouterRegles = ajouterRegles;
    }

    public boolean isSurTerminal() {
        return surTerminal;
    }

    public void setSurTerminal(boolean surTerminal) {
        this.surTerminal = surTerminal;
    }

    public boolean isDeplacementFluide() {
        return deplacementFluide;
    }

    public void setDeplacementFluide(boolean deplacementFluide) {
        this.deplacementFluide = deplacementFluide;
    }

    // Méthodes pour gérer les joueurs

    public void addPlayer(Player<U> player) {
        players.add(player);
    }

    public Player<U> getPlayer(int i) {
        return players.get(i);
    }

    public List<Player<U>> getPlayers() {
        return players ;
    }

    // Méthodes pour gérer le plateau de jeu

    public BoardGame<U> getPlateau() {
        return plateau;
    }

    public void setPlateau(BoardGame<U> plateau) {
        this.plateau = plateau;
    }

    // Méthodes pour gérer le nombre de nourritures

    public int getNombreNourriture() {
        return nombreNourriture;
    }
 
    public void setNombreNourriture(int nombreNourriture) {
        this.nombreNourriture = nombreNourriture;
    }
    
    public List<Position<U>> getNourritures() {
        return nourritures;
    }

    public void setNourritures(List<Position<U>> nourritures) {
        this.nourritures = nourritures;
    }

    // Méthodes pour gérer la fin du jeu

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

}