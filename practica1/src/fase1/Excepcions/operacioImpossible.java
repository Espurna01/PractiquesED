package fase1.Excepcions;

public class operacioImpossible extends Exception{
    public operacioImpossible(int p){
        super("Error: no es posible fer aquesta operació. Posició " + p + " fora del rang.");

    }
}
