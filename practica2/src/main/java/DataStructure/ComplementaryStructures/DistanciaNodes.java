package main.java.DataStructure.ComplementaryStructures;

import main.java.DataStructure.MainStructures.ZonaRecarrega;

public class DistanciaNodes {

    public NodeGraf<ZonaRecarrega, Double> node;
    Estat estat = Estat.BLANC;
    public boolean accesible = false;
    public DistanciaNodes(NodeGraf<ZonaRecarrega, Double> node){
        this.node = node;
    }

    public void setGris() {estat = Estat.GRIS;}

    public void setNegre() {estat = Estat.NEGRE;}

    public Estat getEstat() {
        return estat;
    }

    @Override
    public boolean equals(Object obj) {
        return node.getInfo().getId() == ((NodeGraf<?, ?>) obj).getInfo().getId();
    }

}

