package fase2.EstructuraDades;

import fase1.EstructuraDades.DLL;
import fase1.Excepcions.elementNoExisteix;
import fase2.Excepcions.noInsercio;
import fase2.Excepcions.noObtenir;
import fase2.Excepcions.noTrobat;

import java.util.ArrayList;
import java.util.Iterator;

public class TaulaHash<K extends Comparable<K>, T extends Comparable<T>> implements TADHash<K, T>, Iterable<T>{

    ArrayList<NodeTaulaHash<K, T>> taula;
    private int capacity;
    private static final float limit = 0.75f;
    private boolean repetits;

    public TaulaHash(){
        crear();
    }

    public TaulaHash(int icapacity, boolean permetreRepetits){
        capacity = icapacity;
        taula = new ArrayList<>(capacity);
        for(int i = 0; i < capacity; i++){
            taula.add(null);
        }
        repetits = permetreRepetits;
    }

    @Override
    public void crear() {
        capacity = 10;
        taula = new ArrayList<>(capacity);
        for(int i = 0; i < capacity; i++){
            taula.add(null);
        }
        repetits = false;
    }

    @Override
    public void inserir(K key, T data) throws noInsercio {
        int index = Hasher.getHash(key) % capacity;
        if(taula.get(index) != null){ /* Colisió */
            NodeTaulaHash<K, T> node = taula.get(index);
            while(node.seg != null){
                if(repetits || key.compareTo(node.getClau()) != 0)
                    node = node.seg;
                else break;
            }
            if(repetits || key.compareTo(node.getClau()) != 0){
                NodeTaulaHash<K, T> nnode = new NodeTaulaHash<>(key, data);
                node.setSeg(nnode);
            }else {
                if(node.getValor().compareTo(data) == 0)
                    throw new noInsercio(index);
                node.setValor(data);
            }
        } else { /* No colisió */
            NodeTaulaHash<K, T> nnode = new NodeTaulaHash<>(key, data);
            taula.set(index, nnode);
        }
        if(obtenirFactorDeCarrega() > limit){
            redimensionar();
        }
    }

    private void redimensionar() {
        capacity *= 1.5;
        ArrayList<NodeTaulaHash<K,T>> nouarray = new ArrayList<>(capacity);
        for(int i = 0; i < capacity; i++){
            nouarray.add(null);
        }
        for(NodeTaulaHash<K, T> elem : taula){
                while(elem != null){
                    int index = Hasher.getHash(elem.getClau()) % capacity;
                    NodeTaulaHash<K, T> nnode = new NodeTaulaHash<>(elem.getClau(), elem.getValor());
                    NodeTaulaHash<K, T> node = nouarray.get(index);
                    if(node == null){
                        nouarray.set(index, nnode);
                    } else {
                        while(node.getSeg() != null){
                            node = node.getSeg();
                        }
                        node.setSeg(nnode);
                    }
                    elem = elem.getSeg();
                }
        }
        taula = nouarray;
    }

    @Override
    public T obtenir(K key) throws noObtenir {
        int index = Hasher.getHash(key) % capacity;
        if(taula.get(index) == null) /* No existeix la posició */
            throw new noObtenir(key);
        NodeTaulaHash<K, T> node = taula.get(index);
        while(node != null && node.getClau().compareTo(key) != 0){
            node = node.getSeg();
        }
        /* La clau no es correspon amb qualsevol guardada */
        if(node == null) throw new noObtenir(key);
        return node.getValor();
    }

    @Override
    public int buscar(K key) throws noTrobat {
        int collisions = 0;
        int index = Hasher.getHash(key) % capacity;
        NodeTaulaHash<K, T> node = taula.get(index);
        while(node != null && node.getClau().compareTo(key) != 0){
            node = node.getSeg();
            collisions++;
        }
        if(node == null) throw new noTrobat(collisions + 1);
        return collisions + 1;
    }

    @Override
    public int mida() {
        int elem = 0;
        for(NodeTaulaHash<K, T> n : taula){
            if(n != null){
                while(n != null){
                    elem++;
                    n = n.getSeg();
                }
            }
        }
        return elem;
    }

    @Override
    public void esborrar(K key) throws elementNoExisteix {
        int index = Hasher.getHash(key) % capacity;
        if(taula.get(index) == null)
            throw new elementNoExisteix(0, mida());
        NodeTaulaHash<K, T> node = taula.get(index);
        if(node.seg == null){ /* No col·lisió */
            if(key.compareTo(node.getClau()) != 0) throw new elementNoExisteix(1, mida());
            taula.set(index, null);
        } else { /* Col·lisió */
            NodeTaulaHash<K, T> ant = taula.get(index);
            int i = 0;
            if(node.getClau().compareTo(key) != 0){
                node = node.getSeg();
                while(node != null && node.getClau().compareTo(key) != 0){
                    node = node.getSeg();
                    ant = ant.getSeg();
                    i++;
                }
                i++;
            }
            if(node == null)
                throw new elementNoExisteix(1 + i, mida());
            if(ant == node){
                taula.set(index, node.getSeg());
            } else {
                ant.setSeg(node.getSeg());
            }
        }
    }

    @Override
    public DLL<T> obtenirValors() {
        DLL<T> valors = new DLL<>();
        for(NodeTaulaHash<K, T> elem : taula){
            if(elem != null) {
                while (elem.getSeg() != null) {
                    valors.inserir(elem.getValor());
                    elem = elem.getSeg();
                }
                valors.inserir(elem.getValor());
            }
        }
        return valors;
    }

    @Override
    public DLL<K> obtenirClaus() {
        DLL<K> claus = new DLL<>();
        for(NodeTaulaHash<K, T> elem : taula){
            if(elem != null) {
                while (elem.getSeg() != null) {
                    claus.inserir(elem.getClau());
                    elem = elem.getSeg();
                }
                claus.inserir(elem.getClau());
            }
        }
        return claus;
    }

    @Override
    public float obtenirFactorDeCarrega() {
        return (float) mida()/capacity;
    }

    public int getCapacity(){return capacity;}

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(NodeTaulaHash<K, T> elem : taula){
            if(elem != null) {
                sb.append("Index(").append(Hasher.getHash(elem.getClau()) % capacity).append("): ");
                while (elem.getSeg() != null) {
                    sb.append("[").append(elem.getClau()).append(", ").append(elem.getValor()).append("] -> ");
                    elem = elem.getSeg();
                }
                sb.append("[").append(elem.getClau()).append(", ").append(elem.getValor()).append("] -> ");
                sb.append("null\n");
            }
        }
        sb.append("Factor carrega: ").append(obtenirFactorDeCarrega());
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new TaulaHashIterator<K, T>(this);
    }

    public ArrayList<NodeTaulaHash<K,T>> getAl() {
        return taula;
    }
}
