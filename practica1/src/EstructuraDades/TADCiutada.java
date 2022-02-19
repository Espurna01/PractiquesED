package EstructuraDades;



public abstract class TADCiutada implements Comparable<TADCiutada> {
    protected String nom;
    protected String cognom;
    protected String dni;

    public TADCiutada(String nom, String cognom, String dni) {
        this.nom = nom;
        this.cognom = cognom;
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public int compareTo(TADCiutada o) {
        return dni.compareTo(o.getDni());
    }

    @Override
    public String toString() {
        return nom + "(" + dni + ")";
    }
}
