package Excepcions;

public class operacioImposible extends Exception{
    public operacioImposible(int p){
        super("Error: no es posible fer aquesta operació. Posició " + p + " fora del rang.");

    }
}
