package main.java.DataStructure.MainStructures;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.java.CamiImpossible;
import main.java.DataStructure.ComplementaryStructures.Aresta;
import main.java.DataStructure.ComplementaryStructures.NodeGraf;
import main.java.DataStructure.ComplementaryStructures.TADGraf;
import main.java.ZonaRecarrega;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GrafPR implements TADGraf<NodeGraf<ZonaRecarrega, Double>, Aresta<ZonaRecarrega, Double>> {

    private HashMap<Integer, NodeGraf<ZonaRecarrega, Double>> hmpc;

    public GrafPR(){
        hmpc = new HashMap<>();
        CrearGraf();
        enllacarNodes();
    }

    @Override
    public void CrearGraf() {
        try{
            JsonArray ja = JsonParser.parseReader(new FileReader("icaen.json")).getAsJsonArray();
            int totalUnidos = 0;
            for(JsonElement je : ja){
                JsonObject jo = je.getAsJsonObject();
                int id_estacio = jo.get("id_estacio").getAsInt();
                if(!hmpc.containsKey(id_estacio)){
                    ZonaRecarrega zr = new ZonaRecarrega(id_estacio, jo.get("nom").getAsString(), jo.get("latitud").getAsDouble(), jo.get("longitud").getAsDouble());
                    hmpc.put(zr.getId_estacio(), new NodeGraf<>(zr));
                }
                int id = jo.get("id").getAsInt();
                float potencia;
                try{
                    potencia = jo.get("potencia").getAsFloat();
                }catch (NumberFormatException e){
                    potencia = 0;
                }
                hmpc.get(id_estacio).getInfo().add(new Endoll(id, potencia));
            }
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    private void enllacarNodes() {
        ArrayList<NodeGraf<ZonaRecarrega, Double>> azr = new ArrayList<>(hmpc.values());
        for(int i = 0; i < azr.size() - 1; i++){
            NodeGraf<ZonaRecarrega, Double> minNode = null;
            double minDist = -1;
            for(int j = i + 1; j < azr.size(); j++){
                NodeGraf<ZonaRecarrega, Double> node = azr.get(j);
                double distancia = distancia(azr.get(i), node);
                if(distancia < 40){
                    afegirAresta(azr.get(i), node, new Aresta<>(distancia));
                }
                if(distancia < minDist || minDist == -1){
                    minNode = node;
                    minDist = distancia;
                }
            }
            if(azr.get(i).getPrim_col() == null && azr.get(i).getPrim_fil() == null){
                afegirAresta(azr.get(i), minNode, new Aresta<>(minDist));
            }
        }
    }

    @Override
    public void afegirAresta(NodeGraf<ZonaRecarrega, Double> v1, NodeGraf<ZonaRecarrega, Double> v2, Aresta<ZonaRecarrega, Double> aresta) {
        if(v1.getInfo().compareTo(v2.getInfo()) < 0){
            aresta.setNode_fil(v1);
            aresta.setNode_col(v2);
            Aresta<ZonaRecarrega, Double> a = v1.getPrim_fil();
            if(a == null){
                v1.setPrim_fil(aresta);
            }else{
                while(a.getSeg_fil() != null)
                    a = a.getSeg_fil();
                a.setSeg_fil(aresta);
            }
            Aresta<ZonaRecarrega, Double> b = v2.getPrim_col();
            if(b == null){
                v2.setPrim_col(aresta);
            }else{
                while(b.getSeg_col() != null)
                    b = b.getSeg_col();
                b.setSeg_col(aresta);
            }
        }else{
            aresta.setNode_col(v1);
            aresta.setNode_fil(v2);
            Aresta<ZonaRecarrega, Double> a = v1.getPrim_col();
            if(a == null){
                v1.setPrim_col(aresta);
            }else{
                while(a.getSeg_col() != null)
                    a = a.getSeg_col();
                a.setSeg_col(aresta);
            }
            Aresta<ZonaRecarrega, Double> b = v2.getPrim_fil();
            if(b == null){
                v2.setPrim_fil(aresta);
            }else{
                while(b.getSeg_fil() != null)
                    b = b.getSeg_fil();
                b.setSeg_fil(aresta);
            }
        }
    }

    @Override
    public boolean existeixAresta(NodeGraf<ZonaRecarrega, Double> v1, NodeGraf<ZonaRecarrega, Double> v2) {
        boolean aresta = false;

        if(v1.getInfo().compareTo(v2.getInfo()) < 0){
            Aresta<ZonaRecarrega, Double> a = v1.getPrim_fil();
            for(;!aresta && a != null; a = a.getSeg_fil()){
                aresta = a.getNode_col() == v2;
            }
        }else{
            Aresta<ZonaRecarrega, Double> a = v1.getPrim_col();
            for(;!aresta && a != null; a = a.getSeg_col()){
                aresta = a.getNode_fil() == v2;
            }
        }

        return aresta;
    }

    @Override
    public Aresta<ZonaRecarrega, Double> valorAresta(NodeGraf<ZonaRecarrega, Double> v1, NodeGraf<ZonaRecarrega, Double> v2) {
        boolean aresta = false;
        Aresta<ZonaRecarrega, Double> a;
        if(v1.getInfo().compareTo(v2.getInfo()) < 0){
            a = v1.getPrim_fil();
            for(;!aresta && a != null; a = a.getSeg_fil()){
                aresta = a.getNode_col() == v2;
            }
        }else{
            a = v1.getPrim_col();
            for(;!aresta && a != null; a = a.getSeg_col()){
                aresta = a.getNode_fil() == v2;
            }
        }

        return aresta?a:null;
    }

    @Override
    public ArrayList<NodeGraf<ZonaRecarrega, Double>> adjacents(NodeGraf<ZonaRecarrega, Double> nodeGraf) {
        ArrayList<NodeGraf<ZonaRecarrega, Double>> adj = new ArrayList<>();
        Aresta<ZonaRecarrega, Double> node = nodeGraf.getPrim_col();
        for(;node != null; node = node.getSeg_col()){
            adj.add(node.getNode_fil());
        }
        node = nodeGraf.getPrim_fil();
        for(;node != null; node = node.getSeg_fil()){
            adj.add(node.getNode_col());
        }
        return adj;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(NodeGraf<ZonaRecarrega, Double> n : hmpc.values()){
            sb.append(n.getInfo());
            for(Aresta<ZonaRecarrega, Double> a = n.getPrim_fil(); a != null; a = a.getSeg_fil()){
                sb.append("->").append(a);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private double distancia(NodeGraf<ZonaRecarrega, Double> n1, NodeGraf<ZonaRecarrega, Double> n2){
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        double lon1 = Math.toRadians(n1.getInfo().getLongitud());
        double lon2 = Math.toRadians(n2.getInfo().getLongitud());
        double lat1 = Math.toRadians(n1.getInfo().getLatitud());
        double lat2 = Math.toRadians(n2.getInfo().getLatitud());

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

    public NodeGraf<ZonaRecarrega, Double> getNode(int id_estacio){
        return hmpc.get(id_estacio);
    }

    public Collection<NodeGraf<ZonaRecarrega, Double>> getCollectionNodes(){
        return hmpc.values();
    }

    public ArrayList<NodeGraf<ZonaRecarrega, Double>> camiOptim(int id_origen, int id_desti, int autonomia) throws CamiImpossible {
        //L'algorisme aplicat es A*(a estrella)

        return null;
    }

}
