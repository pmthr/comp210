package comp210.assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    public MinBinHeapER() {
        _heap = new ArrayList<>();
    }

    public MinBinHeapER(Prioritized<V, P>[] initialEntries) {
        _heap = new ArrayList<>();
        _heap.addAll(Arrays.asList(initialEntries));
        bubbleUp(_heap.size()-1);
    }

    @Override
    public int size() {
        return _heap.size();
    }

    @Override
    public void enqueue(V value, P priority) {
        Prioritized<V,P>  pat = new Patient<>(value, priority);
        _heap.add(_heap.size(), pat);
        bubbleUp(_heap.size()-1);
    }

    void bubbleUp(int index) {
        Prioritized<V,P> child = _heap.get(index);
        Prioritized<V,P> parent = _heap.get(parentInd(index));
            if (child.getPriority().compareTo(parent.getPriority()) < 0){
                _heap.set(parentInd(index), child);
                _heap.set(index, parent);
                bubbleUp(parentInd(index));
            }
    }

    public void enqueue(V value) {
        Prioritized<V, P> pat = new Patient<>(value);
        _heap.add(_heap.size(), pat);
        bubbleUp(_heap.size()-1);
    }

    @Override
    public V dequeue() {
        if (_heap.size() == 0) {
            return null;
        }
        else {
            V retValue = _heap.get(0).getValue();
            if (_heap.size() == 1) {
                _heap.remove(0);
            }
            else {
                _heap.set(0, _heap.get(_heap.size()-1));
                _heap.remove(_heap.size()-1);
                bubbleDown(0);
            }
            return retValue;
        }
    }

    void bubbleDown(int index){
        Prioritized<V,P> parent = _heap.get(index);
        if (!hasLeftChild(index) && !hasRightChild(index)){
            return;     
        }
        else if (!hasRightChild(index)) {   
            Prioritized<V,P> leftChild = _heap.get(leftChildInd(index));
            if (leftChild.getPriority().compareTo(parent.getPriority()) < 0) {
                _heap.set(index, leftChild);             
                _heap.set(leftChildInd(index), parent);
                bubbleDown(leftChildInd(index)); 
            }
            else {
                return;
            }
        }
        else {
            Prioritized<V,P> leftChild = _heap.get(leftChildInd(index));
            Prioritized<V,P> rightChild = _heap.get(rightChildInd(index));
            if ((leftChild.getPriority().compareTo(parent.getPriority()) < 0) || (rightChild.getPriority().compareTo(parent.getPriority()) < 0)) {
                if (leftChild.getPriority().compareTo(rightChild.getPriority()) < 0) {
                    _heap.set(index, leftChild);             
                    _heap.set(leftChildInd(index), parent);
                    bubbleDown(leftChildInd(index));            
                } else {
                    _heap.set(index, rightChild);             
                    _heap.set(rightChildInd(index), parent);
                    bubbleDown(rightChildInd(index));            
                }
            }
            else {
                return;
            }  
        }
    }

    boolean hasLeftChild(int index) {
        return(validIndex(leftChildInd(index)));}

    boolean hasRightChild(int index) {
        return(validIndex(rightChildInd(index)));}

    static int leftChildInd(int index) {
        return (2*index+1);}

    static int rightChildInd(int index) {
        return (2*index+2);}

    static int parentInd(int index) {
        return ((index-1)/2);}

    boolean validIndex(int index) {
        return (index >= 0) && (index <= _heap.size() - 1);
    }

    @Override
    public V getMin() {
        if(_heap.size() == 0) {
            return null;
        }
        else {
            return _heap.get(0).getValue();
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }






}
