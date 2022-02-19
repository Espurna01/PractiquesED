package Excepcions;

public class elementNoExisteix extends Exception{
    public elementNoExisteix(int e, int s){
        super("S'han recorregut " + e + " elements a la llista de " + s + " elements i no s'ha trobat l'element.");
    }
}
