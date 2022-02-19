package fase2.Excepcions;

public class noInsercio extends Exception{
    public noInsercio(int n){
        super("L'element " + n + " no s'ha pogut inserir.");
    }
}
