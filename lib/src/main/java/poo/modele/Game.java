package poo.modele;
import java.util.List;

public class Game <U extends Number>{
    private List <Position<U>> nourritures ;
    private int nombreNourriture;
    private Player<U> player ; 
    private Plateau<U> plateau ;
    
    public Game( Player<U> player, Plateau<U> plateau,int nombreNourriture) {
        this.nombreNourriture = nombreNourriture;
        this.player = player;
        this.plateau = plateau;
    }
    public List<Position<U>> getNourritures() {
        return nourritures;
    }
    public void setNourritures(List<Position<U>> nourritures) {
        this.nourritures = nourritures;
    }
    public Player<U> getPlayer() {
        return player;
    }
    public void setPlayer(Player<U> player) {
        this.player = player;
    }
    public Plateau<U> getPlateau() {
        return plateau;
    }
    public void setPlateau(Plateau<U> plateau) {
        this.plateau = plateau;
    }
    public int getNombreNourriture() {
        return nombreNourriture;
    }
    public void setNombreNourriture(int nombreNourriture) {
        this.nombreNourriture = nombreNourriture;
    }
    
     

         
}
