package main.java;

import java.util.ArrayList;
import java.util.HashMap;

public class ZonaRecarrega {
    private int id_estacio;
    private ArrayList<Endoll> endolls;
    private HashMap<Integer, Aresta> arestes;
    private int maxEndoll;
    private final double latitud;
    private final double longitud;

    public ZonaRecarrega(int id_estacio, double latitud, double longitud) {
        this.id_estacio = id_estacio;
        this.endolls = new ArrayList<>();
        this.maxEndoll = -1;
        this.latitud = latitud;
        this.longitud = longitud;
        this.arestes = new HashMap<>();
    }

    public boolean add(Endoll endoll) {
        if(endolls.size() != 0 && endolls.get(maxEndoll).getPotencia() > endoll.getPotencia()){
            maxEndoll = endolls.size();
        }else if(endolls.size() == 0) maxEndoll = 0;
        return endolls.add(endoll);
    }

    public Aresta put(Integer key, Aresta value) {
        return arestes.put(key, value);
    }

    public int getId_estacio() {
        return id_estacio;
    }

    public ArrayList<Endoll> getEndolls() {
        return endolls;
    }

    public int getMaxEndoll() {
        return maxEndoll;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public ArrayList<ZonaRecarrega> getAdjancents(){
        ArrayList<ZonaRecarrega> azr = new ArrayList<>(arestes.size());
        for(Aresta a : arestes.values()){
            if(a.getV1().getId() != id_estacio)
                azr.add(a.getV1());
            else azr.add(a.getV2());
        }
        return azr;
    }

    public int getId(){
        return id_estacio;
    }

    public Aresta getAresta(int id_estacio){
        return arestes.get(id_estacio);
    }

    public String toString(){
        return endolls.get(maxEndoll).getPotencia() + "\\" + latitud + ", " + longitud;
    }
}
