package poo.modele;


public class Plateau<U extends Number>{
    private int largeur, longueur ;
    private Segment<U>[][] cells;
    private boolean isInt=true ;

    public Plateau(int largeur, int longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.cells = new Segment[longueur][largeur];

        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < largeur; j++) {
                if(isInt){
                    this.cells[i][j] = new Segment<>(createPosition((U) Integer.valueOf(i), (U) Integer.valueOf(j)), SegmentType.VIDE);
                }else{
                    this.cells[i][j] = new Segment<>(createPosition((U) Double.valueOf(i), (U) Double.valueOf(j)), SegmentType.VIDE);

                }

            }
        }
    }

    private <T extends Number> Position<T> createPosition(T x, T y) {
        return new Position<>(x, y);
    }
   
    public void setCellType(int x, int y, SegmentType string) {
        cells[x][y].setType(string); 

    }
    public SegmentType getCellType(U x, U y){
        return cells[(Integer)x][(Integer)y].getType(); 

    }

    public Segment<U>[][] getCells() {
        return cells;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }




    
   /*public enum CellType {
        EMPTY, SNAKE, FOOD
        // Ajoutez d'autres types de cellules au besoin
    }
   */
}
