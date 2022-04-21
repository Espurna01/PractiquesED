package fase2.EstructuraDades;

import java.util.ArrayList;
import java.util.Iterator;

public class TaulaHashIterator<K extends Comparable<K>, T extends Comparable<T>> implements Iterator<T> {

    private TaulaHash<K, T> th;
    private NodeTaulaHash<K, T> nth;
    private int index;

    public TaulaHashIterator(TaulaHash<K, T> th){
        this.th = th;
        index = 0;
        for(NodeTaulaHash<K, T> node : th.getAl()){
            if(node == null){
                index++;
            } else break;
        }
        this.nth = th.getAl().get(index);
    }

    @Override
    public boolean hasNext() {
        return index != th.getCapacity();
    }

    @Override
    public T next() {
        ArrayList<NodeTaulaHash<K, T>> al = th.getAl();
        T data = nth.getValor();

        nth = nth.getSeg();
        if(nth == null){
            for(index =  index + 1; index < th.getCapacity(); index++){
                if(al.get(index) != null){
                    nth = al.get(index);
                    break;
                }
            }
        }

        return data;
    }
}
