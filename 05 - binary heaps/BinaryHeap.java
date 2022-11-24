package comp210.assn05;

public interface BinaryHeap<V,P extends Comparable<P>> {
    int size();

    void enqueue(V value, P priority);
    V dequeue();
    
    V getMin();
    Prioritized<V,P>[] getAsArray();
}
