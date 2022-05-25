package main.java;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class GrafPR implements TADPuntCarrega<ZonaRecarrega, Float>{

    private HashMap<Integer, ZonaRecarrega> hpv;

    public GrafPR(){
        CrearGraf();
        enllacarNodes();
    }

    @Override
    public void CrearGraf()  {
        hpv = new HashMap<>();
        try {
            JsonArray ja = JsonParser.parseReader(new FileReader("icaen.json")).getAsJsonArray();
            for(JsonElement je : ja){
                JsonObject jo = je.getAsJsonObject();
                int id_estacio;
                id_estacio = jo.get("id_estacio").getAsInt();
                if(!hpv.containsKey(id_estacio)){
                    ZonaRecarrega zr = new ZonaRecarrega(id_estacio, jo.get("latitud").getAsDouble(), jo.get("longitud").getAsDouble());
                    if(hpv.size() > 1) {
                        for (ZonaRecarrega zones : hpv.values()) {
                            double latlat = zr.getLatitud() - zones.getLatitud();
                            double lonlon = zr.getLongitud() - zones.getLongitud();
                            latlat = latlat * latlat;
                            lonlon = lonlon * lonlon;
                            if (Math.sqrt(latlat + lonlon) < 40) {
                                System.out.println(id_estacio + "<->" + zones.getId_estacio() + ": " + Math.sqrt(latlat + lonlon));
                            }
                        }
                    }
                    hpv.put(id_estacio, zr);
                }
                float potencia;
                try{
                    potencia = jo.get("potencia").getAsFloat();
                }catch (NumberFormatException e){
                    potencia = 0;
                }
                hpv.get(id_estacio).add(new Endoll(jo.get("id").getAsInt(), potencia));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void enllacarNodes() {

    }

    @Override
    public void afegirAresta(ZonaRecarrega v1, ZonaRecarrega v2, Float dist) {
        Aresta a = new Aresta(v1, v2, dist);
        v1.put(v2.getId(), a);
        v2.put(v1.getId(), a);
    }

    @Override
    public boolean existeixAresta(ZonaRecarrega v1, ZonaRecarrega v2) {
        return v1.getAresta(v2.getId()) != null;
    }

    @Override
    public Float valorAresta(ZonaRecarrega v1, ZonaRecarrega v2) {
        if(!existeixAresta(v1, v2))
            return null;
        return v1.getAresta(v2.getId()).getDistancia();
    }

    @Override
    public ArrayList<ZonaRecarrega> adjacents(ZonaRecarrega zonaRecarrega) {
        return zonaRecarrega.getAdjancents();
    }

    public String toString(){
        return hpv.toString();
    }
}
