package poo.modele;

public enum DirectionKey {
        UP("Z","z","Up"),
        DOWN("S","s","Down"),
        LEFT("Q","q","Left"),
        RIGHT("D","d","Right");

        private final String[] inputs;

        DirectionKey(String... inputs) {
            this.inputs = inputs;
        }

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

        public String getLetter( DirectionKey key) {
            // Vous pouvez personnaliser cette logique selon vos besoins
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