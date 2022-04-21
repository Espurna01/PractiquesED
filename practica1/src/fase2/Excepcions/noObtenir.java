package fase2.Excepcions;

public class noObtenir extends Exception{
    public noObtenir(Object n){
        super("No s'ha pogut obtenir l'element de la clau " + n + ".");
    }
}
