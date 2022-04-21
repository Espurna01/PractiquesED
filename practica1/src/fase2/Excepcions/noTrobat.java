package fase2.Excepcions;

public class noTrobat extends Exception {
    private int n;

    public noTrobat(int n){
        super("No s'ha trobat l'element buscat, s'han buscat " + n + " elements.");
        this.n = n;
    }

    public int getN(){return n;}
}
