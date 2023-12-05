package poo.modele;


public class Plateau {
    private int largeur, longueur ;
    private String[][] cells;

    public Plateau(int largeur , int longueur) {
        cells = new String[largeur][longueur];
        this.largeur = largeur ;
        this.longueur = longueur ;
    }

    public void setCellType(int x, int y, String string) {
        cells[x][y] = string;

    }

    public String[][] getCells() {
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
