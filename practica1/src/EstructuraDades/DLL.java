package EstructuraDades;

import Excepcions.elementNoExisteix;
import Excepcions.operacioImposible;

import java.util.Iterator;

public class DLL<T extends Comparable<T>> implements TADLlista<T>, Iterable<T> {

    private DLL<T> fwd;
    private DLL<T> bkw;
    private T data;

    public DLL(){
        this(null);
    }

    public DLL(T data){
        crear();
        this.data = data;
    }

    public DLL(T data, DLL<T> bkw){
        crear();
        this.data = data;
        this.bkw = bkw;
    }

    @Override
    public void crear() {
        fwd = null;
        bkw = null;
        data = null;
    }

    @Override
    public void inserir(T data) {
        if(data != null){
            if (this.data != null) {
                if(fwd != null)
                    fwd.inserir(data);
                else
                    fwd = new DLL<>(data, this);
            } else
                this.data = data;

        }
    }

    /**
     * Posició -> [1, longitud()]
     * @param posicio posició a inserir element
     * @param data element a inserir.
     * @throws operacioImposible posició fora del rang de la llista
     */
    @Override
    public void inserir(int posicio, T data) throws operacioImposible {
        if(posicio > longitud() || posicio < 1) throw new operacioImposible(posicio);
        DLL<T> poi = this;
        for(int i = 1; i < posicio;i++){
            poi = poi.fwd;
        }
        if(poi.bkw == null){
            DLL<T> newNode = new DLL<>(this.data, this);
            newNode.setFwd(fwd);
            setFwd(newNode);
            this.data = data;
        } else{
            DLL<T> newNode = new DLL<>(data, poi.getBkw());
            newNode.setFwd(poi);
            poi.getBkw().setFwd(newNode);
            poi.setBkw(newNode);
        }
    }

    private void setBkw(DLL<T> newNode) {
        bkw = newNode;
    }

    private void setFwd(DLL<T> fwd) {
        this.fwd = fwd;
    }

    /**
     * Posició -> [1, longitud()]
     * @param posicio posició de l'element
     * @return
     * @throws operacioImposible posició fora del rang de la llista
     */
    @Override
    public T obtenir(int posicio) throws operacioImposible {
        if(posicio > longitud() || posicio < 1) throw new operacioImposible(posicio);
        int i = 0;
        for(T data : this){
            i++;
            if(i == posicio) return data;
        }
        throw new operacioImposible(posicio);
    }

    @Override
    public int longitud() {
        if(data == null)
            return 0;
        int i = 0;
        for(T data : this)
            i++;
        return i;

    }

    /**
     * Posició -> [1, longitud()]
     * @param posicio posició de l'element a esborrar
     * @throws operacioImposible posició fora del rang de la llista
     */
    /* TODO */
    @Override
    public void esborrar(int posicio) throws operacioImposible {
        if(posicio > longitud() || posicio < 1) throw new operacioImposible(posicio);

        DLL<T> poi = this;
        for(int i = 1; i < posicio;i++){
            poi = poi.fwd;
        }
        if(poi.fwd == null){
            if(poi.getBkw() != null){
                poi.getBkw().setFwd(null);
                poi.setBkw(null);
            }
            poi.setData(null);
        } else{
            if(poi.getBkw() == null){
                T data = poi.getFwd().getData();
                setData(data);
                if(poi.getFwd().getFwd() != null)
                    poi.getFwd().getFwd().setBkw(this);
                DLL<T> tmp = poi.getFwd();
                setFwd(poi.getFwd().getFwd());
                poi = tmp;
            }
            else{
                poi.getBkw().setFwd(poi.getFwd());
                poi.getFwd().setBkw(poi.getBkw());
            }
            poi.setData(null);
            poi.setFwd(null);
            poi.setBkw(null);
        }

    }

    @Override
    public int buscar(T data) throws elementNoExisteix {
        int elem = 0;
        for(T d : this){
            elem++;
            if(d.compareTo(data) == 0){
                return elem;
            }
        }
        throw new elementNoExisteix(elem, longitud());
    }

    @Override
    public Iterator<T> iterator() {
        return new DLLiterator<>(this);
    }

    public T getData() {
        return data;
    }

    private void setData(T data) {
        this.data = data;
    }

    public DLL<T> getFwd() {
        return fwd;
    }

    public DLL<T> getBkw() {
        return bkw;
    }

    public String toString(){
        String r = "[{null";
        for(T data : this){
            r += "} ⇄ {" + data;
        }
        return r + "} ⇄ {null}]";
    }
}
