//package main.java;
//
//import main.java.DataStructure.ComplementaryStructures.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.prefs.NodeChangeEvent;
//
//public class GrafPrueba<V extends Comparable<V>, E> implements TADGraf<NodeGraf<V, E>, Aresta<V, E>> {
//
//    protected HashMap<String, NodeGraf<V, E>> hmv;
//
//    public GrafPrueba(){
//        CrearGraf();
//    }
//
//    @Override
//    public void CrearGraf() {
//        hmv = new HashMap<>();
//    }
//
//    public void afegirNode(V info){
//        hmv.put(info.toString(), new NodeGraf<>(info));
//    }
//
//    @Override
//    public void afegirAresta(NodeGraf<V, E> v1, NodeGraf<V, E> v2, Aresta<V, E> aresta) {
//        if(v1.getInfo().compareTo(v2.getInfo()) < 0){
//            aresta.setNode_fil(v1);
//            aresta.setNode_col(v2);
//            Aresta<V, E> a = v1.getPrim_fil();
//            if(a == null){
//                v1.setPrim_fil(aresta);
//            }else{
//                while(a.getSeg_fil() != null)
//                    a = a.getSeg_fil();
//                a.setSeg_fil(aresta);
//            }
//            Aresta<V, E> b = v2.getPrim_col();
//            if(b == null){
//                v2.setPrim_col(aresta);
//            }else{
//                while(b.getSeg_col() != null)
//                    b = b.getSeg_col();
//                b.setSeg_col(aresta);
//            }
//        }else{
//            aresta.setNode_col(v1);
//            aresta.setNode_fil(v2);
//            Aresta<V, E> a = v1.getPrim_col();
//            if(a == null){
//                v1.setPrim_col(aresta);
//            }else{
//                while(a.getSeg_col() != null)
//                    a = a.getSeg_col();
//                a.setSeg_col(aresta);
//            }
//            Aresta<V, E> b = v2.getPrim_fil();
//            if(b == null){
//                v2.setPrim_fil(aresta);
//            }else{
//                while(b.getSeg_fil() != null)
//                    b = b.getSeg_fil();
//                b.setSeg_fil(aresta);
//            }
//        }
//    }
//
//    @Override
//    public boolean existeixAresta(NodeGraf<V, E> v1, NodeGraf<V, E> v2) {
//        boolean aresta = false;
//
//        if(v1.getInfo().compareTo(v2.getInfo()) < 0){
//            Aresta<V, E> a = v1.getPrim_fil();
//            for(;!aresta && a != null; a = a.getSeg_fil()){
//                aresta = a.getNode_col() == v2;
//            }
//        }else{
//            Aresta<V, E> a = v1.getPrim_col();
//            for(;!aresta && a != null; a = a.getSeg_col()){
//                aresta = a.getNode_fil() == v2;
//            }
//        }
//
//        return aresta;
//    }
//
//    @Override
//    public Aresta<V, E> valorAresta(NodeGraf<V, E> v1, NodeGraf<V, E> v2) {
//        boolean aresta = false;
//        Aresta<V, E> a;
//        if(v1.getInfo().compareTo(v2.getInfo()) < 0){
//            a = v1.getPrim_fil();
//            for(;!aresta && a != null; a = a.getSeg_fil()){
//                aresta = a.getNode_col() == v2;
//            }
//        }else{
//            a = v1.getPrim_col();
//            for(;!aresta && a != null; a = a.getSeg_col()){
//                aresta = a.getNode_fil() == v2;
//            }
//        }
//
//        return aresta?a:null;
//    }
//
//    @Override
//    public ArrayList<NodeGraf<V, E>> adjacents(NodeGraf<V, E> nodeGraf) {
//        ArrayList<NodeGraf<V, E>> adj = new ArrayList<>(3);
//        Aresta<V, E> node = nodeGraf.getPrim_col();
//        for(;node != null; node = node.getSeg_col()){
//            adj.add(node.getNode_fil());
//        }
//        node = nodeGraf.getPrim_fil();
//        for(;node != null; node = node.getSeg_fil()){
//            adj.add(node.getNode_col());
//        }
//        return adj;
//    }
//
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        for(NodeGraf<V, E> n : hmv.values()){
//            sb.append(n.toString());
//            for(Aresta<V, E> a = n.getPrim_fil(); a != null; a = a.getSeg_fil()){
//                sb.append("\t").append(a.toString());
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//
//    public NodeGraf<V, E> getNode(String id){
//        return hmv.get(id);
//    }
//
//
//
//}
