package poo.modele;

public class Position<U extends Number> {
    private  U positionX;
    private  U positionY;

    public Position(U positionX,U positionY){
        this.positionX=positionX;
        this.positionY=positionY;
    }
    public U getPositionX() {
        return positionX;
    }
    public U getPositionY() {
        return positionY;
    }

  public Position<U> copy(){
        return new Position<U>(this.positionX, this.positionY);
    }
@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((positionX == null) ? 0 : positionX.hashCode());
    result = prime * result + ((positionY == null) ? 0 : positionY.hashCode());
    return result;
}
@Override
public boolean equals(Object obj) {
    // Étape 1: Vérifier si c'est la même instance
    if (this == obj)
        return true;

    // Étape 2: Vérifier si l'objet n'est pas nul
    if (obj == null)
        return false;

    // Étape 3: Vérifier si les classes sont les mêmes
    if (getClass() != obj.getClass())
        return false;

    // Étape 4: Convertir l'objet en une instance de Position
    Position other = (Position) obj;

    // Étape 5: Comparer les champs positionX
    if (positionX == null) {
        if (other.positionX != null)
            return false;
    } else if (!positionX.equals(other.positionY))
        return false;

    // Étape 6: Comparer les champs positionY
    if (positionY == null) {
        if (other.positionY != null)
            return false;
    } else if (!positionY.equals(other.positionX))
        return false;

    // Étape 7: Les objets sont égaux si toutes les conditions précédentes sont satisfaites
    return true;
}

    
}
