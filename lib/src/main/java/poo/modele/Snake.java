package poo.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Snake représente un serpent dans le jeu.
 * Un serpent est composé d'une tête et d'un corps constitué de segments.
 * Chaque segment a une position sur le plateau de jeu et un type (SERPENT).
 */
public class Snake<U extends Number> {
    private List<Segment<U>> body = new ArrayList<Segment<U>>();   // Liste des segments formant le corps du serpent
    private int length;               // Longueur du serpent
    private DirectionKey direction = DirectionKey.DOWN;   // Direction actuelle du serpent
    private Segment<U> head;           // Segment de la tête du serpent
    private double speed = 1.0;        // Vitesse du serpent
    private boolean bouclier = false; // Indique si le serpent a un bouclier
    private long lastUpdateTime ;      // Temps du dernier pas

    /**
     * Construit un serpent avec une tête à la position spécifiée.
     *
     * @param x La position X de la tête du serpent.
     * @param y La position Y de la tête du serpent.
     */
    public Snake(U x, U y) {
        this.head = new Segment<>(new Position<>(x, y), SegmentType.SERPENT);
        this.body.add(head);
        this.length = 1;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    /**
     * Obtient la liste des segments formant le corps du serpent.
     *
     * @return Liste des segments du corps du serpent.
     */
    public List<Segment<U>> getBody() {
        return body;
    }

    /**
     * Obtient la longueur actuelle du serpent.
     *
     * @return Longueur du serpent.
     */
    public int getLength() {
        return length;
    }

    /**
     * Définit la liste des segments formant le corps du serpent.
     *
     * @param body Liste des segments du corps du serpent.
     */
    public void setBody(List<Segment<U>> body) {
        this.body = body;
    }

    /**
     * Définit la longueur du serpent.
     *
     * @param length Nouvelle longueur du serpent.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Obtient la direction actuelle du serpent.
     *
     * @return Direction actuelle du serpent.
     */
    public DirectionKey getDirection() {
        return direction;
    }

    /**
     * Définit la direction du serpent.
     *
     * @param direction Nouvelle direction du serpent.
     */
    public void setDirection(DirectionKey direction) {
        this.direction = direction;
    }

    /**
     * Déplace le serpent vers une nouvelle position.
     *
     * @param pos Nouvelle position de la tête du serpent.
     */
    public void seDeplacer(Position<U> pos) {
        for (int i = length - 1; i > 0; i--) {
            body.get(i).setPosition(body.get(i - 1).getPosition());
        }
        body.get(0).setPosition(pos);
    }

    /**
     * Obtient le segment de la tête du serpent.
     *
     * @return Segment de la tête du serpent.
     */
    public Segment<U> getHead() {
        return head;
    }

    /**
     * Définit le segment de la tête du serpent.
     *
     * @param head Nouveau segment de la tête du serpent.
     */
    public void setHead(Segment<U> head) {
        this.head = head;
    }

    /**
     * Obtient le dernier segment du serpent.
     *
     * @return Dernier segment du serpent.
     */
    public Segment<U> getLast() {
        return this.body.get(this.body.size() - 1);
    }

    /**
     * Ajoute une nouvelle longueur au serpent à la position spécifiée.
     *
     * @param position Nouvelle position pour la tête du serpent.
     */
    public void addLength(Position<U> position) {
        length++;
        body.add(0, new Segment<>(position, SegmentType.SERPENT));
        head = body.get(0);
    }

    /**
     * Détecte une auto-collision du serpent (collision avec son propre corps).
     *
     * @return true si une auto-collision est détectée, sinon false.
     */
    public boolean detecterAutoCollision() {
        Position<U> tetePosition = this.getHead().getPosition();
        int teteX = (int) Math.floor(tetePosition.getPositionX().doubleValue() * 100);
        int teteY = (int) Math.floor(tetePosition.getPositionY().doubleValue() * 100);
        for (int i = 1; i < this.getBody().size(); i++) {
            Position<U> segmentPosition = this.getBody().get(i).getPosition();
            int segmentX = (int) Math.floor(segmentPosition.getPositionX().doubleValue() * 100);
            int segmentY = (int) Math.floor(segmentPosition.getPositionY().doubleValue() * 100);
            if (teteX == segmentX && teteY == segmentY) {
                return true; // Auto-collision détectée
            }
        }
        return false;
    }

    /**
     * Obtient le temps écoulé depuis le dernier pas du serpent.
     *
     * @return Temps écoulé en secondes.
     */
    public double getDeltaTime() {
        long currentTime = System.currentTimeMillis();
        double deltaTime = (currentTime - lastUpdateTime) / 1000.0;
        lastUpdateTime = currentTime;
        return deltaTime;
    }

    /**
     * Détecte une collision entre deux serpents.
     *
     * @param serpent2 Deuxième serpent pour la détection de collision.
     * @return true si une collision est détectée, sinon false.
     */
    public boolean detecterCollisionEntreSerpents(Snake<U> serpent2) {
        List<Segment<U>> corpsSerpent1 = getBody();
        List<Segment<U>> corpsSerpent2 = serpent2.getBody();
        for (Segment<U> segment1 : corpsSerpent1) {
            int seg1X = (int) Math.floor(segment1.getPosition().getPositionX().doubleValue());
            int seg2Y = (int) Math.floor(segment1.getPosition().getPositionY().doubleValue());
            for (Segment<U> segment2 : corpsSerpent2) {
                int segmentX = (int) Math.floor(segment2.getPosition().getPositionX().doubleValue());
                int segmentY = (int) Math.floor(segment2.getPosition().getPositionY().doubleValue());
                if (seg1X == segmentX && seg2Y == segmentY) {
                    return true; // Collision détectée
                }
            }
        }
        return false; // Aucune collision détectée
    }

    /**
     * Obtient la vitesse actuelle du serpent.
     *
     * @return Vitesse du serpent.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Définit la vitesse du serpent.
     *
     * @param speed Nouvelle vitesse du serpent.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Vérifie si le serpent a un bouclier.
     *
     * @return true si le serpent a un bouclier, sinon false.
     */
    public boolean isBouclier() {
        return bouclier;
    }

    /**
     * Définit si le serpent a un bouclier.
     *
     * @param bouclier true pour activer le bouclier, false pour le désactiver.
     */
    public void setBouclier(boolean bouclier) {
        this.bouclier = bouclier;
    }
}

