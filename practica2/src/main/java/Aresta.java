package main.java;

public class Aresta {
    private final ZonaRecarrega v1;
    private final ZonaRecarrega v2;
    private final float distancia;

    public Aresta(ZonaRecarrega v1, ZonaRecarrega v2, float distancia) {
        this.v1 = v1;
        this.v2 = v2;
        this.distancia = distancia;
    }

    public float getDistancia() {
        return distancia;
    }

    public ZonaRecarrega getV2() {
        return v2;
    }

    public ZonaRecarrega getV1() {
        return v1;
    }
}
