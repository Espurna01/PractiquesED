package main.java.DataStructure.MainStructures;

import main.java.ID;

import java.util.ArrayList;

public class ZonaRecarrega implements Comparable<ZonaRecarrega>, ID<Integer> {
    private int id_estacio;
    private String nom;
    private ArrayList<Endoll> endolls;
    private int maxEndoll;
    private final double latitud;
    private final double longitud;

    public ZonaRecarrega(int id_estacio, String nom, double latitud, double longitud) {
        this.id_estacio = id_estacio;
        this.nom = nom;
        this.endolls = new ArrayList<>();
        this.maxEndoll = -1;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public boolean add(Endoll endoll) {
        if(endolls.size() != 0 && endolls.get(maxEndoll).getPotencia() > endoll.getPotencia()){
            maxEndoll = endolls.size();
        }else if(endolls.size() == 0) maxEndoll = 0;
        return endolls.add(endoll);
    }


    public ArrayList<Endoll> getEndolls() {
        return endolls;
    }

    public float getPotencia() {
        return endolls.get(maxEndoll).getPotencia();
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String toString(){
        return id_estacio + "";
    }

    public String getNom() {
        return nom;
    }

    @Override
    public int compareTo(ZonaRecarrega o) {
        return Integer.compare(id_estacio, o.getId());
    }

    @Override
    public Integer getId() {
        return id_estacio;
    }
}
