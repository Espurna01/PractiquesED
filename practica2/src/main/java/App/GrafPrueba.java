package main.java.App;

import main.java.DataStructure.ComplementaryStructures.*;
import main.java.DataStructure.ComplementaryStructures.ArestaPruebaGraf;

import java.util.ArrayList;
import java.util.HashMap;

public class GrafPrueba implements TADGraf<NodePruebaGraf, ArestaPruebaGraf> {

    private HashMap<String, NodePruebaGraf> hmv;

    public GrafPrueba(){
        CrearGraf();
    }

    @Override
    public void CrearGraf() {
        hmv = new HashMap<>();
    }

    public void afegirNode(String info) {
        hmv.put(info.toString(), new NodePruebaGraf(info));
    }

    @Override
    public void afegirAresta(NodePruebaGraf v1, NodePruebaGraf v2, ArestaPruebaGraf aresta) {
        if(v1.getInfo().compareTo(v2.getInfo()) < 0){
            aresta.setNode_fil(v1);
            aresta.setNode_col(v2);
            ArestaPruebaGraf a = v1.getPrim_fil();
            if(a == null){
                v1.setPrim_fil(aresta);
            }else{
                while(a.getSeg_fil() != null)
                    a = a.getSeg_fil();
                a.setSeg_fil(aresta);
            }
            ArestaPruebaGraf b = v2.getPrim_col();
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
            ArestaPruebaGraf a = v1.getPrim_col();
            if(a == null){
                v1.setPrim_col(aresta);
            }else{
                while(a.getSeg_col() != null)
                    a = a.getSeg_col();
                a.setSeg_col(aresta);
            }
            ArestaPruebaGraf b = v2.getPrim_fil();
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
    public boolean existeixAresta(NodePruebaGraf v1, NodePruebaGraf v2) {
        boolean aresta = false;

        if(v1.getInfo().compareTo(v2.getInfo()) < 0){
            ArestaPruebaGraf a = v1.getPrim_fil();
            for(;!aresta && a != null; a = a.getSeg_fil()){
                aresta = a.getNode_col() == v2;
            }
        }else{
            ArestaPruebaGraf a = v1.getPrim_col();
            for(;!aresta && a != null; a = a.getSeg_col()){
                aresta = a.getNode_fil() == v2;
            }
        }

        return aresta;
    }

    @Override
    public ArestaPruebaGraf valorAresta(NodePruebaGraf v1, NodePruebaGraf v2) {
        boolean aresta = false;
        ArestaPruebaGraf a;
        if(v1.getInfo().compareTo(v2.getInfo()) < 0){
            a = v1.getPrim_fil();
            for(;!aresta && a != null; a = a.getSeg_fil()){
                aresta = a.getNode_col() == v2;
                if(aresta)
                    break;
            }
        }else{
            a = v1.getPrim_col();
            for(;!aresta && a != null; a = a.getSeg_col()){
                aresta = a.getNode_fil() == v2;
                if(aresta)
                    break;
            }
        }

        return aresta?a:null;
    }

    @Override
    public ArrayList<NodePruebaGraf> adjacents(NodePruebaGraf nodeGraf) {
        ArrayList<NodePruebaGraf> adj = new ArrayList<>(3);
        ArestaPruebaGraf node = nodeGraf.getPrim_col();
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
        for(NodePruebaGraf n : hmv.values()){
            sb.append(n.toString());
            for(ArestaPruebaGraf a = n.getPrim_fil(); a != null; a = a.getSeg_fil()){
                sb.append("\t").append(a.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public NodePruebaGraf getNode(String id){
        return hmv.get(id);
    }

}
