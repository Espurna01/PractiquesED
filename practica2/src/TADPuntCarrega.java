import java.util.ArrayList;

public interface TADPuntCarrega <V, E>{
    /**
     * Constructor per inicialitzar la taula.
     */
    void CrearGraf();

    /**
     * Funció per a afegir una aresta.
     * L’operació llença una excepció en cas que no es pugui afegir.
     * @param v1
     * @param v2
     * @param e
     */
    void afegirAresta(V v1, V v2, E e);

    /**
     * Funció que ens diu si una aresta existeix.
     * @param v1
     * @param v2
     * @return
     */
    boolean existeixAresta(V v1, V v2);

    /**
     * Funció que retorna el valor d'una aresta.
     * L’operació llença una excepció en cas que no existeixi.
     * @param v1
     * @param v2
     * @return
     */
    E valorAresta(V v1, V v2);

    /**
     * Funció que retorna una llista que conté tots els nodes adjacents al node passat per
     * paràmetre.
     * L’operació llença una excepció en cas que no es pugui crear aquesta llista.
     *
     * @param v
     * @return
     */
    ArrayList<V> adjacents(V v);
}
