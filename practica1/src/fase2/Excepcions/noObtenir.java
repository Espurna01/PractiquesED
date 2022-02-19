package fase2.Excepcions;

public class noObtenir extends Exception{
    public noObtenir(int n){
        super("No s'ha pogut obtenir l'element de la clau " + n + ".");
    }
}
