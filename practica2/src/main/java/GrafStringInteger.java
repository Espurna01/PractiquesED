package main.java;

import main.java.DataStructure.ComplementaryStructures.MinHeap;
import main.java.DataStructure.ComplementaryStructures.NodeEstrella;
import main.java.DataStructure.ComplementaryStructures.NodeGraf;

import java.util.ArrayList;
import java.util.HashMap;

public class GrafStringInteger extends GrafPrueba<String, Integer>{
    public ArrayList<NodeGraf<String, Integer>> camiOptim(String origen, String desti){
        HashMap<String, Integer> heuristic = new HashMap<>(4);
        heuristic.put("A", 10);
        heuristic.put("B", 2);
        heuristic.put("C", 7);
        heuristic.put("D", 0);

        MinHeap<NodeEstrella<String, Integer>> mh = new MinHeap<>();
        ArrayList<NodeGraf<String, Integer>> closed = new ArrayList<>();
        NodeEstrella<String, Integer> current = new NodeEstrella<>(getNode(origen), heuristic.get(origen), 0);
        while(current.getActual() != hmv.get(desti)){
            for(NodeGraf<String, Integer> ng : adjacents(current.getActual())){
                if(){

                }
            }
        }

        return null;
    }
}
