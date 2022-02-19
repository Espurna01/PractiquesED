package EstructuraDades;

public class CiutadaCotxe extends TADCiutada{
    public CiutadaCotxe(String nom, String cognom, String dni) {
        super(nom, cognom, dni);
    }

    public void conduir(){
        System.out.println("Estic conduint");
    }
}
