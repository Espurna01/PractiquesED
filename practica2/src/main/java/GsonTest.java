package main.java;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import main.java.DataStructure.ComplementaryStructures.Aresta;
import main.java.DataStructure.ComplementaryStructures.MinHeap;
import main.java.DataStructure.MainStructures.GrafPR;
import main.java.DataStructure.ComplementaryStructures.NodeGraf;
import main.java.Excepcions.CamiImpossible;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GsonTest {

    public static void main(String[] args) throws IOException, CamiImpossible {
//        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .create();
//
//
//        //Prueba p = new Prueba(null, 21);
//
//        JsonReader jr = new JsonReader(new FileReader("icaen.json"));
//
//        JsonArray ja = JsonParser.parseReader(new FileReader("icaen.json")).getAsJsonArray();
//
//
//        GrafPrueba<String, Integer> grafPrueba = new GrafPrueba();
//
//        grafPrueba.afegirNode("A");
//        grafPrueba.afegirNode("B");
//        grafPrueba.afegirNode("C");
//        grafPrueba.afegirNode("D");
//
//        grafPrueba.afegirAresta(grafPrueba.getNode("A"), grafPrueba.getNode("B"), new Aresta<>(6));
//        grafPrueba.afegirAresta(grafPrueba.getNode("A"), grafPrueba.getNode("C"), new Aresta<>(2));
//        grafPrueba.afegirAresta(grafPrueba.getNode("C"), grafPrueba.getNode("B"), new Aresta<>(3));
//        grafPrueba.afegirAresta(grafPrueba.getNode("C"), grafPrueba.getNode("D"), new Aresta<>(15));
//        grafPrueba.afegirAresta(grafPrueba.getNode("B"), grafPrueba.getNode("D"), new Aresta<>(7));
//
//        System.out.println(grafPrueba);
//
//        System.out.println(grafPrueba.existeixAresta(grafPrueba.getNode("A"), grafPrueba.getNode("D")));
//
//        System.out.println(grafPrueba.adjacents(grafPrueba.getNode("C")));
//
        GrafPR grafPR = new GrafPR();
//
//        ArrayList<NodeGraf<ZonaRecarrega, Double>> azr = new ArrayList<>(grafPR.getCollectionNodes());
//
//        for(NodeGraf<ZonaRecarrega, Double> n : azr){
//            Aresta<ZonaRecarrega, Double> a = n.getPrim_fil();
//            while(a != null){
//                if(a.getInfo() >= 40){
//                    System.out.println(a.getNode_fil() + "<-distancia >= 40->" + a.getNode_col());
//                }
//                a = a.getSeg_fil();
//            }
//            a = n.getPrim_col();
//            while(a != null){
//                if(a.getInfo() >= 40){
//                    System.out.println(a.getNode_col() + "<-distancia >= 40->" + a.getNode_fil());
//                }
//                a = a.getSeg_col();
//            }
//        }
//
//        System.out.println(grafPR.adjacents(grafPR.getNode(33852300)));

//        System.out.println(grafPR.camiOptim(3887523, 7088600, 20));
//
        System.out.println(grafPR.adjacents(grafPR.getNode(33852299)));
//        System.out.println(grafPR.valorAresta(grafPR.getNode(13382168), grafPR.getNode(7088600)));


//        System.out.println(grafPR.zonesDistMaxNoGarantida(3887523, 20));


    }
}
