package main.java.Excepcions;

public class CamiImpossible extends Exception {
    public CamiImpossible(int id_o, int id_d, int auto){
        super("No hi ha un cami possible amb una autonomia de " + auto + " entre " + id_o + " i " + id_d);
    }
}
