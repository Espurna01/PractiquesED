package main.java.DataStructure.ComplementaryStructures;

public class NodeEstrella<V extends ID<Integer>, E> implements Comparable<NodeEstrella<V, E>>{
    private NodeGraf<V, E> actual;
    private NodeEstrella<V, E> anterior;
    private boolean recarregar;
    double heuristic;
    double cost;

    public NodeEstrella(NodeGraf<V, E> actual, double heuristic, double cost) {
        recarregar = false;
        anterior = null;
        this.actual = actual;
        this.heuristic = heuristic;
        this.cost = cost;
    }

    public void sumCostPreviousNodes(){
        NodeEstrella<V, E> previous = anterior;
        while(previous != null){
            cost += previous.getCost();
            previous = previous.getAnterior();
        }
    }

    public NodeEstrella<V,E> getAnterior() {
        return anterior;
    }

    public double getCost() {
        return cost;
    }

    public NodeGraf<V, E> getActual(){return actual;}

    public void setAnterior(NodeEstrella<V, E> ant){
        anterior = ant;
    }

    public double getF() {
        return cost + heuristic;
    }

    @Override
    public int compareTo(NodeEstrella<V, E> o) {
        return Double.compare(getF(), o.getF());
    }

    public void setRecarregar(boolean tf){
        recarregar = tf;
    }

    public String toString(){
        String ret = actual.toString();
        if(recarregar)
            ret = ret.concat("(Parada per recarregar!!)");
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        return actual.equals(((NodeEstrella<?, ?>) obj).getActual());
    }
}
