package fase2.EstructuraDades;

public class NodeTaulaHash<K extends Comparable<K>, T extends Comparable<T>> {
    public K clau;
    public T valor;
    public NodeTaulaHash<K, T> seg;

    public NodeTaulaHash(K key, T data) {
        seg = null;
        clau = key;
        valor = data;
    }

    public NodeTaulaHash() {
        clau = null;
        valor = null;
        seg = null;
    }

    public K getClau() {
        return clau;
    }

    public void setClau(K clau) {
        this.clau = clau;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NodeTaulaHash<K, T> getSeg() {
        return seg;
    }

    public void setSeg(NodeTaulaHash<K, T> seg) {
        this.seg = seg;
    }
}
