package fase1.Excepcions;

public class elementNoExisteix extends Exception{
    private int elements;
    public elementNoExisteix(int e, int s){
        super("S'han recorregut " + e + " elements a la llista de " + s + " elements i no s'ha trobat l'element.");
        elements = e;
    }

    public int getElements() {
        return elements;
    }
}
