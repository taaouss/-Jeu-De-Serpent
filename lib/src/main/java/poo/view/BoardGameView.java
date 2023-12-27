package poo.view;
import  poo.modele.*;


public class BoardGameView <U extends Number>{
    


     public void AfficherPlateau(int largeur , int longueur, Segment<U>[][] strings) {
       
        for (int x = 0; x < largeur; x++) {
            System.out.println();
            for (int y = 0; y < longueur; y++) {
                System.out.print ("_ _ ");
            }
             System.out.println();
            for (int y = 0; y < longueur; y++) {
                switch (strings[x][y].getType()) {
                    case VIDE:
                        System.out.printf ("|   ");
                        break;
                     case NOURRITURE:
                        System.out.printf ("|  #");
                        break;    
                
                     default: //SERPENT
                        System.out.printf ("|  *");
                        break;
                }
                
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