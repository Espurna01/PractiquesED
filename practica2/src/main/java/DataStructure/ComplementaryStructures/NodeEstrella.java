package main.java.DataStructure.ComplementaryStructures;

import main.java.ZonaRecarrega;

public class NodeEstrella<V, E> implements Comparable<NodeEstrella<V, E>>{
    private NodeGraf<V, E> actual;
    private NodeGraf<V, E> anterior;
    boolean recarregar;
    boolean visitat;
    double heuristic;
    double cost;

    public NodeEstrella(NodeGraf<V, E> actual, double heuristic, double cost) {
        this.heuristic = heuristic;
        recarregar = false;
        anterior = null;
        visitat = false;
        this.cost = cost;
    }

    public NodeGraf<V, E> getActual(){return actual;}

    public double getCost() {
        return cost;
    }

    @Override
    public int compareTo(NodeEstrella<V, E> o) {
        return Double.compare(cost, o.getCost());
    }
}
