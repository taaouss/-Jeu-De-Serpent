package poo.modele;
import java.util.ArrayList;
import java.util.List;

public class Game <U extends Number>{
    private List <Position<U>> nourritures ;
    private int nombreNourriture;
    private List <Player<U> >players = new ArrayList<Player<U> >() ; 
    private BoardGame<U> plateau ;
    
    public Game( Player <U>player, BoardGame<U> plateau,int nombreNourriture) {

        this.nombreNourriture = nombreNourriture;
        players.add(player);
        this.plateau = plateau;

    }

    public void addPlayer(Player <U>player){
        players.add(player);
    }

    public List<Position<U>> getNourritures() {
        return nourritures;
    }
    public void setNourritures(List<Position<U>> nourritures) {
        this.nourritures = nourritures;
    }
    public Player <U>getPlayer(int i) {
        return players.get(i);
    }
   public  List <Player <U>> getPlayers() {
        return players;
    }
 
    public BoardGame<U> getPlateau() {
        return plateau;
    }
    public void setPlateau(BoardGame<U> plateau) {
        this.plateau = plateau;
    }
    public int getNombreNourriture() {
        return nombreNourriture;
    }
    public void setNombreNourriture(int nombreNourriture) {
        this.nombreNourriture = nombreNourriture;
    }
    
    
         
}
