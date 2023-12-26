package poo.modele;

public enum DirectionKey {
        UP("Z","z"),
        DOWN("S","s"),
        LEFT("Q","q"),
        RIGHT("D","d");

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
    }