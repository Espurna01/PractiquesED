package fase1.EstructuraDades;

import fase1.Excepcions.elementNoExisteix;
import fase1.Excepcions.operacioImpossible;

import java.util.Iterator;

public class DLL<T extends Comparable<T>> implements TADLlista<T>, Iterable<T> {

    private DLL<T> fwd;
    private DLL<T> bkw;
    protected T data;

    public DLL(){
        crear();
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
                DLL<T> poi = this;
                while(poi.getFwd() != null){
                    poi = poi.getFwd();
                }
                poi.setFwd(new DLL<>(data, poi));
            } else
                this.data = data;

        }
    }

    /**
     * Posició -> [1, longitud() + 1]
     * @param posicio posició a inserir element
     * @param data element a inserir.
     * @throws operacioImpossible posició fora del rang de la llista
     */
    @Override
    public void inserir(int posicio, T data) throws operacioImpossible {
        if(posicio > longitud() + 1 || posicio < 1) throw new operacioImpossible(posicio);
        DLL<T> poi = this;

        for(int i = 1; i < posicio;i++){
            if(poi.getFwd() != null)
                poi = poi.fwd;
        }

        if(poi.getBkw() == null) {
            DLL<T> newNode = new DLL<>(this.data, this);
            newNode.setFwd(fwd);
            fwd = newNode;
            this.data = data;
        } else {
            /* Al final */
            if(posicio == longitud() + 1){
                DLL<T> newNode = new DLL<>(data, poi);
                newNode.setFwd(poi.getFwd());
                if(poi.getFwd() != null)
                    poi.getFwd().setBkw(newNode);
                poi.setFwd(newNode);
            } else {
                DLL<T> newNode = new DLL<>(data, poi.getBkw());
                newNode.setFwd(poi);
                poi.getBkw().setFwd(newNode);
                poi.setBkw(newNode);
            }
        }
    }

    protected void setBkw(DLL<T> newNode) {
        bkw = newNode;
    }

    protected void setFwd(DLL<T> fwd) {
        this.fwd = fwd;
    }

    /**
     * Posició -> [1, longitud()]
     * @param posicio posició de l'element
     * @return
     * @throws operacioImpossible posició fora del rang de la llista
     */
    @Override
    public T obtenir(int posicio) throws operacioImpossible {
        if(posicio > longitud() || posicio < 1) throw new operacioImpossible(posicio);
        int i = 0;
        for(T data : this){
            i++;
            if(i == posicio) return data;
        }
        throw new operacioImpossible(posicio);
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
     * @throws operacioImpossible posició fora del rang de la llista
     */
    @Override
    public void esborrar(int posicio) throws operacioImpossible {
        if(posicio > longitud() || posicio < 1) throw new operacioImpossible(posicio);

        DLL<T> poi = this;
        for(int i = 1; i < posicio;i++){
            poi = poi.fwd;
        }
        if(poi.fwd == null){    /* Ultim */
            if(poi.getBkw() != null){
                poi.getBkw().setFwd(null);
                poi.setBkw(null);
            }
            poi.setData(null); /* Unic */
        } else{
            if(poi.getBkw() == null){   /* Primer */
                T data = poi.getFwd().getData();
                setData(data);
                if(poi.getFwd().getFwd() != null)
                    poi.getFwd().getFwd().setBkw(this);
                DLL<T> tmp = poi.getFwd();
                setFwd(poi.getFwd().getFwd());
                poi = tmp;
            }
            else{ /* Mig */
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
        StringBuilder r = new StringBuilder("[{null");
        for(T data : this){
            r.append("} ⇄ {").append(data);
        }
        return r + "} ⇄ {null}]";
    }
}
