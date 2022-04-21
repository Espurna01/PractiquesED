package fase1.EstructuraDades;

import java.util.Iterator;

public class DLLiterator<T extends Comparable<T>> implements Iterator<T> {

    private DLL<T> dll;

    public DLLiterator(DLL<T> dll){
        this.dll = dll;
    }

    @Override
    public boolean hasNext() {
        return dll != null;
    }

    @Override
    public T next() {
        T data = dll.getData();
        dll = dll.getFwd();
        return data;
    }
}
