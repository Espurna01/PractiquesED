package fase2.Excepcions;

public class noTrobat extends Exception {
    public noTrobat(int n){
        super("No s'ha trobat l'element buscat, s'han buscat " + n + " elements.");
    }
}
