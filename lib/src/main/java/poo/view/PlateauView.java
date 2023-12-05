package poo.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class PlateauView {
    


     public void AfficherPlateau(int largeur , int longueur, String[][] strings) {
       
        for (int x = 0; x < largeur; x++) {
            System.out.println();
            for (int y = 0; y < longueur; y++) {
                System.out.print ("_ _ ");
            }
             System.out.println();
            for (int y = 0; y < longueur; y++) {
                System.out.printf ("|  "+strings[x][y]);
            }
            System.out.print(" |");
            
        }
          System.out.println();
        for (int y = 0; y < longueur; y++) {
                System.out.print ("_ _ ");
            }
            System.out.println();


    }

   /* 
   private void updateCellAppearance(int x, int y) {
        Rectangle cell = (Rectangle) getChildren().get(y * GRID_WIDTH + x);

        switch (cells[x][y]) {
            case EMPTY:
                cell.setFill(Color.WHITE);
                break;
            case SNAKE:
                cell.setFill(Color.GREEN);
                break;
            case FOOD:
                cell.setFill(Color.RED);
                break;
            // Ajoutez d'autres types de cellules au besoin
        }
}
   
   */ 

   
}