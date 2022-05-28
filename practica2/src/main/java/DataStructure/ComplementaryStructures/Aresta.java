package main.java.DataStructure.ComplementaryStructures;

import main.java.ID;

public class Aresta<V extends ID<Integer>, E> {
    protected Aresta<V, E> seg_fil;
    protected Aresta<V, E> seg_col;
    protected NodeGraf<V , E> node_fil;
    protected NodeGraf<V, E> node_col;

    protected E info;

    public Aresta(E info) {
        this.node_fil = null;
        this.node_col = null;
        seg_col = null;
        seg_fil = null;
        this.info = info;
    }

    public Aresta<V, E> getSeg_fil() {
        return seg_fil;
    }

    public void setSeg_fil(Aresta<V, E> seg_fil) {
        this.seg_fil = seg_fil;
    }

    public Aresta<V, E> getSeg_col() {
        return seg_col;
    }

    public void setSeg_col(Aresta<V, E> seg_col) {
        this.seg_col = seg_col;
    }

    public NodeGraf<V, E> getNode_fil() {
        return node_fil;
    }

    public void setNode_fil(NodeGraf<V, E> node_fil) {
        this.node_fil = node_fil;
    }

    public NodeGraf<V, E> getNode_col() {
        return node_col;
    }

    public void setNode_col(NodeGraf<V, E> node_col) {
        this.node_col = node_col;
    }

    public E getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return node_col + "(" + info +")";
    }
}
