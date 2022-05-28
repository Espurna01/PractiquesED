package main.java.DataStructure.ComplementaryStructures;

import main.java.DataStructure.MainStructures.ZonaRecarrega;

import java.util.ArrayList;
import java.util.Arrays;

public class MinHeap<T extends Comparable<T>> {
    private T[] elems = (T[]) new Comparable[10];
    private int size = 0;

    private int getLeftIndex(int parentIndex){return 2 * parentIndex + 1;}
    private int getRightIndex(int parentIndex){return 2 * parentIndex + 2;}
    private int getParentIndex(int childIndex){return (childIndex - 1)/2;}

    private boolean hasLeft(int index){return getLeftIndex(index) < size;}
    private boolean hasRight(int index){return getRightIndex(index) < size;}
    private boolean hasParent(int index){return getParentIndex(index) >= 0;}

    private T getLeft(int index){return elems[getLeftIndex(index)];}
    private T getRight(int index){return elems[getRightIndex(index)];}
    private T getParent(int index){return elems[getParentIndex(index)];}

    private void swap(int index1, int index2){
        T tmp = elems[index1];
        elems[index1] = elems[index2];
        elems[index2] = tmp;
    }

    private void ensureCapacity(){
        if(size == elems.length){
            elems = Arrays.copyOf(elems, elems.length * 2);
        }
    }

    public T peek(){
        if(size == 0) throw new IllegalStateException();
        return elems[0];
    }

    public T pull(){
        if(size == 0) throw new IllegalStateException();
        T e = elems[0];
        elems[0] = elems[size - 1];
        size--;
        bubbleDown();
        return e;
    }

    private void bubbleUp() {
        int index = size - 1;
        while(hasParent(index) && getParent(index).compareTo(elems[index]) > 0){
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void add(T elem){
        ensureCapacity();
        elems[size] = elem;
        size++;
        bubbleUp();
    }

    private void bubbleDown() {
        int index = 0;
        while(hasLeft(index)){
            int minChildIndex = getLeftIndex(index);
            if(hasRight(index) && getRight(index).compareTo(getLeft(index)) < 0){
                minChildIndex = getRightIndex(index);
            }
            if(elems[index].compareTo(elems[minChildIndex]) >= 0){
                swap(index, minChildIndex);
            }
            index = minChildIndex;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        boolean firstElem = true;
        for(T e : elems){
            if(e != null){
                if(!firstElem)
                    sb.append(", ");
                sb.append(e);
                firstElem = false;
            }
        }
        return sb + "]";
    }

    public int size() {
        return size;
    }

    public boolean belongs(T elem){
        for(T candidate : elems){
            if(candidate != null && elem.equals(candidate))
                return true;
        }
        return false;
    }

    public T get(T elem) {
        for(T candidate : elems){
            if(candidate != null && elem.equals(candidate))
                return candidate;
        }
        return null;
    }

    public void set(T elem) {
        for(int i = 0; i < elems.length; i++){
            if(elems[i].equals(elem)){
                elems[i] = elem;
                return;
            }
        }
    }
}
