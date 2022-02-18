package Excepcions;

public class elementNoExisteix extends Exception{
    public elementNoExisteix(int n){
        super("S'han recorregut " + n + " elements a la llista i no s'ha trobat l'element.");
    }
}
