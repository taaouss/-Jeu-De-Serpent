package poo.modele;

/**
 * L'énumération DirectionKey représente les touches de direction possibles.
 */
public enum DirectionKey {
    UP("Z", "z", "Up"),      // Touche pour aller vers le haut
    DOWN("S", "s", "Down"),  // Touche pour aller vers le bas
    LEFT("Q", "q", "Left"),  // Touche pour aller vers la gauche
    RIGHT("D", "d", "Right"); // Touche pour aller vers la droite

    private final String[] inputs; // Tableau des différentes entrées associées à chaque direction

    /**
     * Constructeur de l'énumération DirectionKey.
     *
     * @param inputs Les différentes entrées associées à la direction.
     */
    DirectionKey(String... inputs) {
        this.inputs = inputs;
    }

    /**
     * Transforme une chaîne d'entrée en une valeur de l'énumération DirectionKey.
     *
     * @param input La chaîne d'entrée à transformer.
     * @return La valeur de l'énumération DirectionKey correspondante, ou null si aucune correspondance n'est trouvée.
     */
    public static DirectionKey transformer(String input) {
        for (DirectionKey direction : values()) {
            for (String key : direction.inputs) {
                if (input.equals(key)) {
                    return direction;
                }
            }
        }
        return null;
    }

    /**
     * Obtient la lettre associée à une direction.
     *
     * @param key La direction pour laquelle obtenir la lettre.
     * @return La lettre associée à la direction.
     */
    public String getLetter(DirectionKey key) {
        switch (key) {
            case UP:
                return "z";
            case DOWN:
                return "s";
            case LEFT:
                return "q";
            case RIGHT:
                return "d";
            default:
                break;
        }
        return null;
    }
}
