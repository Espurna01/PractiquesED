package EstructuraDades;

import org.jetbrains.annotations.NotNull;

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
    public int compareTo(@NotNull TADCiutada o) {
        return dni.compareTo(o.getDni());
    }
}
