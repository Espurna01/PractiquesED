package fase1.EstructuraDades;

public class CiutadaPeu extends TADCiutada implements Comparable<TADCiutada>{
    public CiutadaPeu(String nom, String cognom, String dni) {
        super(nom, cognom, dni);
    }

    @Override
    public int compareTo(TADCiutada o) {
        return super.compareTo(o);
    }

    public void caminar(){
        System.out.println("Estic caminant");
    }

}
