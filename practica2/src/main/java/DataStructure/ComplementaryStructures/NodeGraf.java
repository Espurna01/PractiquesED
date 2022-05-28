package main.java.DataStructure.ComplementaryStructures;

import main.java.ID;

public class NodeGraf<V extends ID<Integer>, E>  {
    private V info;
    private Aresta<V, E> prim_fil;
    private Aresta<V, E> prim_col;

    public NodeGraf(V info){
        this.info = info;
        prim_col = null;
        prim_fil = null;
    }

    public V getInfo() {
        return info;
    }

    public Aresta<V, E> getPrim_fil() {
        return prim_fil;
    }

    public void setPrim_fil(Aresta<V, E> prim_fil) {
        this.prim_fil = prim_fil;
    }

    public Aresta<V, E> getPrim_col() {
        return prim_col;
    }

    public void setPrim_col(Aresta<V, E> prim_col) {
        this.prim_col = prim_col;
    }

    public String toString(){
        return info.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return info.equals(((NodeGraf<?, ?>)obj).getInfo());
    }

}
